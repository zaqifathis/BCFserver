package de.openfabtwin.utils;

import de.openfabtwin.generated.documents.DocumentInfo;
import de.openfabtwin.generated.extensions.Extensions;
import de.openfabtwin.generated.markup.Markup;
import de.openfabtwin.generated.project.Project;
import de.openfabtwin.generated.project.ProjectInfo;
import de.openfabtwin.generated.version.Version;
import de.openfabtwin.generated.visinfo.VisualizationInfo;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.Data;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.util.logging.Logger;

public class BcfZipReader {

    private static final Logger log = Logger.getLogger(BcfZipReader.class.getName());

    public static final String SUPPORTED_VERSION = "3.0";

    private static final Schema VERSION_SCHEMA;
    private static final Schema PROJECT_SCHEMA;
    private static final Schema EXTENSIONS_SCHEMA;
    private static final Schema DOCUMENTS_SCHEMA;
    private static final Schema MARKUP_SCHEMA;
    private static final Schema VISINFO_SCHEMA;

    private static final JAXBContext JAXB_VERSION_CONTEXT;
    private static final JAXBContext JAXB_PROJECT_CONTEXT;
    private static final JAXBContext JAXB_MARKUP_CONTEXT;
    private static final JAXBContext JAXB_EXTENSIONS_CONTEXT;
    private static final JAXBContext JAXB_DOCUMENTS_CONTEXT;
    private static final JAXBContext JAXB_VISINFO_CONTEXT;

    static {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            sf.setResourceResolver((type, namespaceURI, publicId, systemId, baseURI) -> {
                String filename = systemId.replaceAll(".*/", "");
                InputStream is = BcfZipReader.class.getResourceAsStream("/schemas/" + filename);
                if (is == null) return null;
                return new ClasspathLSInput(is, systemId, publicId, baseURI);
            });

            VERSION_SCHEMA    = sf.newSchema(res("/schemas/version.xsd"));
            PROJECT_SCHEMA    = sf.newSchema(res("/schemas/project.xsd"));
            EXTENSIONS_SCHEMA = sf.newSchema(res("/schemas/extensions.xsd"));
            DOCUMENTS_SCHEMA  = sf.newSchema(res("/schemas/documents.xsd"));
            MARKUP_SCHEMA     = sf.newSchema(res("/schemas/markup.xsd"));
            VISINFO_SCHEMA    = sf.newSchema(res("/schemas/visinfo.xsd"));

            JAXB_VERSION_CONTEXT    = JAXBContext.newInstance(Version.class);
            JAXB_PROJECT_CONTEXT    = JAXBContext.newInstance(ProjectInfo.class);
            JAXB_MARKUP_CONTEXT     = JAXBContext.newInstance(Markup.class);
            JAXB_EXTENSIONS_CONTEXT = JAXBContext.newInstance(Extensions.class);
            JAXB_DOCUMENTS_CONTEXT  = JAXBContext.newInstance(DocumentInfo.class);
            JAXB_VISINFO_CONTEXT    = JAXBContext.newInstance(VisualizationInfo.class);

        } catch (SAXException | JAXBException e) {
            throw new ExceptionInInitializerError("Failed to init BcfZipReader: " + e.getMessage());
        }
    }


    //----------------- RESULT TYPE -----------------+

    public record RootFileResult(
            String projectName,
            String projectId,
            Extensions extensions,
            DocumentInfo documentInfo
    ) {}

    public record TopicFolder(
            String guid,
            Markup markup,
            Map<String, VisualizationInfo> viewpoints,
            Map<String, byte[]> snapshots,
            Map<String, byte[]> bitmaps
    ) {}

    public record BcfParseResult(
            RootFileResult  root,
            List<TopicFolder> topics,
            Map<String, byte[]> documents
    ) {}


    //----------------- ENTRY -----------------+

    public static BcfParseResult read(byte[] zipBytes, int projectCount) throws IOException {

        Map<String, byte[]> rootFiles = new HashMap<>();
        Map<String, Map<String, byte[]>> topicFiles = new LinkedHashMap<>();
        Map<String, byte[]> documentFiles = new LinkedHashMap<>();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zipBytes))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) { zis.closeEntry(); continue; }

                String name = entry.getName();
                String[] parts = name.split("/");

                if (parts.length == 1) {
                    rootFiles.put(parts[0], zis.readAllBytes());
                } else if (parts.length == 2) {
                    String folder   = parts[0];
                    String filename = parts[1];

                    if (folder.equalsIgnoreCase("documents")) {
                        documentFiles.put(filename, zis.readAllBytes());
                    } else {
                        topicFiles
                                .computeIfAbsent(folder, k -> new HashMap<>())
                                .put(filename, zis.readAllBytes());
                    }
                }
                zis.closeEntry();
            }
        }

        RootFileResult root = validateRootFiles(rootFiles, projectCount);
        List<TopicFolder> topics = validateAndParseTopics(topicFiles);
        return new BcfParseResult(root, topics, documentFiles);
    }

    //----------------- VALIDATE -----------------+

    private static RootFileResult validateRootFiles(Map<String, byte[]> rootFiles, int projectCount) throws IOException {
        byte[] versionBytes = rootFiles.get("bcf.version");
        if (versionBytes == null) {
            throw new IOException("Missing required file: bcf.version");
        }
        validate(versionBytes, VERSION_SCHEMA, "bcf.version");
        checkVersion(versionBytes);

        String projectName = "PROJECT_" + (projectCount + 1);
        String projectId = UUID.randomUUID().toString();

        byte[] projectBytes = rootFiles.get("project.bcfp");
        if (projectBytes != null) {
            validate(projectBytes, PROJECT_SCHEMA, "project.bcfp");
            Map<String, String> projectDetail = extractProjectName(projectBytes);
            if(projectDetail != null) {
                String name = projectDetail.get("name");
                String id = projectDetail.get("projectId");
                if (name != null && !name.isBlank()) projectName = name;
                if (id   != null) projectId = id;
            }
        }

        Extensions extensions = null;
        byte[] extensionsBytes = rootFiles.get("extensions.xml");
        if (extensionsBytes != null) {
            try {
                validate(extensionsBytes, EXTENSIONS_SCHEMA, "extensions.xml");
            } catch (IOException e) {
                log.warning("extensions.xml failed schema validation (non-standard order), parsing anyway: " + e.getMessage());
            }
            extensions = unmarshalExtensions(extensionsBytes);
        }

        DocumentInfo documentInfo = null;
        byte[] documentsBytes = rootFiles.get("documents.xml");
        if (documentsBytes != null) {
            validate(documentsBytes, DOCUMENTS_SCHEMA, "documents.xml");
            documentInfo = unmarshalDocumentInfo(documentsBytes);
        }

        return new RootFileResult(projectName, projectId, extensions, documentInfo);
    }

    private static List<TopicFolder> validateAndParseTopics(Map<String, Map<String, byte[]>> topicFiles) throws IOException {
        if (topicFiles.isEmpty()) {
            throw new IOException("No topic folders found in BCF archive");
        }

        List<TopicFolder> result = new ArrayList<>();

        for (Map.Entry<String, Map<String, byte[]>> entry : topicFiles.entrySet()) {
            String              guid  = entry.getKey();
            Map<String, byte[]> files = entry.getValue();

            validateGuid(guid);

            // Markup
            byte[] markupBytes = files.get("markup.bcf");
            if (markupBytes == null) {
                throw new IOException("Missing markup.bcf in topic: " + guid);
            }
            validate(markupBytes, MARKUP_SCHEMA, guid + "/markup.bcf");
            Markup markup = unmarshalMarkup(markupBytes);

            // VizInfo
            Map<String, VisualizationInfo> viewpoints = new LinkedHashMap<>();
            for (String name : files.keySet()) {
                if (name.toLowerCase().endsWith(".bcfv")) {
                    byte[] bytes = files.get(name);
                    validate(bytes, VISINFO_SCHEMA, guid + "/" + name);
                    viewpoints.put(name, unmarshalVisualizationInfo(bytes));
                }
            }

            // Snapshot
            Map<String, byte[]> snapshots = new LinkedHashMap<>();
            for (String name : files.keySet()) {
                if (isImageExtension(name) && !name.toLowerCase().startsWith("bitmap")) {
                    snapshots.put(name, files.get(name));
                }
            }

            // Bitmap
            Map<String, byte[]> bitmaps = new LinkedHashMap<>();
            for (String name : files.keySet()) {
                if (isImageExtension(name) && name.toLowerCase().startsWith("bitmap")) {
                    bitmaps.put(name, files.get(name));
                }
            }

            result.add(new TopicFolder(guid, markup, viewpoints, snapshots, bitmaps));
        }
        return result;
    }


    //----------------- HELPER -----------------+


    private static void checkVersion(byte[] versionBytes) throws IOException {
        try {
            Unmarshaller u = JAXB_VERSION_CONTEXT.createUnmarshaller();
            Version version = (Version) u.unmarshal(new ByteArrayInputStream(versionBytes));

            if (!SUPPORTED_VERSION.equals(version.getVersionId())) {
                throw new UnsupportedOperationException(
                        "Unsupported BCF version: '" + version.getVersionId() + "'. Only 3.0 is supported."
                );
            }
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (JAXBException e) {
            throw new IOException("Failed to parse bcf.version", e);
        }
    }

    private static Map<String, String> extractProjectName(byte[] projectBytes) throws IOException {
        try {
            Unmarshaller u = JAXB_PROJECT_CONTEXT.createUnmarshaller();
            ProjectInfo projectInfo = (ProjectInfo) u.unmarshal(new ByteArrayInputStream(projectBytes));
            Project project = projectInfo.getProject();
            if (project == null) return null;
            Map<String, String> projectDetails = new HashMap<>();
            projectDetails.put("name", project.getName());
            projectDetails.put("projectId", project.getProjectId());
            return projectDetails;
        } catch (JAXBException e) {
            throw new IOException("Failed to parse project.bcfp", e);
        }
    }

    private static void validate(byte[] bytes, Schema schema, String entryName) throws IOException {
        try {
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(bytes)));
        } catch (SAXException e) {
            throw new IOException("Schema validation failed [" + entryName + "]: " + e.getMessage(), e);
        }
    }

    private static Markup unmarshalMarkup(byte[] bytes) throws IOException {
        try {
            Unmarshaller u = JAXB_MARKUP_CONTEXT.createUnmarshaller();
            return (Markup) u.unmarshal(new ByteArrayInputStream(bytes));
        } catch (JAXBException e) {
            throw new IOException("Failed to parse markup.bcf: " + e.getMessage(), e);
        }
    }

    private static VisualizationInfo unmarshalVisualizationInfo(byte[] bytes) throws IOException {
        try {
            Unmarshaller u = JAXB_VISINFO_CONTEXT.createUnmarshaller();
            return (VisualizationInfo) u.unmarshal(new ByteArrayInputStream(bytes));
        } catch (JAXBException e) {
            throw new IOException("Failed to parse viewpoint.bcfv: " + e.getMessage(), e);
        }
    }

    static Extensions unmarshalExtensions(byte[] bytes) throws IOException {
        try {
            Unmarshaller u = JAXB_EXTENSIONS_CONTEXT.createUnmarshaller();
            return (Extensions) u.unmarshal(new ByteArrayInputStream(bytes));
        } catch (JAXBException e) {
            throw new IOException("Failed to parse extensions.xml: " + e.getMessage(), e);
        }
    }

    private static DocumentInfo unmarshalDocumentInfo(byte[] bytes) throws IOException {
        try {
            Unmarshaller u = JAXB_DOCUMENTS_CONTEXT.createUnmarshaller();
            return (DocumentInfo) u.unmarshal(new ByteArrayInputStream(bytes));
        } catch (JAXBException e) {
            throw new IOException("Failed to parse documents.xml: " + e.getMessage(), e);
        }
    }

    private static void validateGuid(String guid) throws IOException {
        if (!guid.matches("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}")) {
            throw new IOException("Invalid topic GUID folder name: '" + guid + "'");
        }
    }

    private static boolean isImageExtension(String name) {
        String lower = name.toLowerCase();
        return lower.endsWith(".png") || lower.endsWith(".jpg");
    }

    private static java.net.URL res(String path) {
        return BcfZipReader.class.getResource(path);
    }

    @Data
    private static class ClasspathLSInput implements org.w3c.dom.ls.LSInput {
        private final InputStream byteStream;
        private String systemId, publicId, baseURI;

        ClasspathLSInput(InputStream byteStream, String systemId, String publicId, String baseURI) {
            this.byteStream = byteStream;
            this.systemId = systemId;
            this.publicId = publicId;
            this.baseURI = baseURI;
        }

        public Reader  getCharacterStream(){ return null;}
        public String  getStringData(){ return null;}
        public String  getEncoding(){ return null;}
        public boolean getCertifiedText(){ return false;}
        public void setCharacterStream(Reader r){}
        public void setByteStream(InputStream s){}
        public void setStringData(String s){}
        public void setEncoding(String s){}
        public void setCertifiedText(boolean b){}
    }
}