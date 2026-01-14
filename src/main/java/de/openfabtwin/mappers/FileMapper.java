package de.openfabtwin.mappers;

import de.openfabtwin.entities.FileEntity;
import de.openfabtwin.entities.TopicFileReferenceEntity;
import de.openfabtwin.generated.dto.FileGET;
import de.openfabtwin.generated.dto.ProjectFileDisplayInformation;
import de.openfabtwin.generated.dto.ProjectFileInformation;
import de.openfabtwin.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileMapper {

    public ProjectFileInformation toProjectFileDto(FileEntity file) {
        ProjectFileInformation projectFileInformation = new ProjectFileInformation();
        List<ProjectFileDisplayInformation> displayInformationItems = getProjectFileDisplayInformations(file);
        projectFileInformation.setDisplayInformation(displayInformationItems);

        FileGET fl = new FileGET();
        fl.setFilename(file.getFilename());
        fl.setDate(file.getDate());
        fl.setReference(file.getReference());

        projectFileInformation.setFile(fl);
        return projectFileInformation;
    }

    private List<ProjectFileDisplayInformation> getProjectFileDisplayInformations(FileEntity file) {
        List<ProjectFileDisplayInformation> displayInformationItems = new ArrayList<>();

        ProjectFileDisplayInformation displayInformation = new ProjectFileDisplayInformation();
        displayInformation.setFieldDisplayName("Model Name");
        displayInformation.setFieldValue(stripExtension(file.getFilename()));
        displayInformationItems.add(displayInformation);

        ProjectFileDisplayInformation displayInformation2 = new ProjectFileDisplayInformation();
        displayInformation2.setFieldDisplayName("Revision Date");
        displayInformation2.setFieldValue(DateUtils.toReadableDate(file.getDate()));
        displayInformationItems.add(displayInformation2);
        return displayInformationItems;
    }

    private static String stripExtension(String filename) {
        if (filename == null) return "";
        int idx = filename.lastIndexOf('.');
        return idx > 0 ? filename.substring(0, idx) : filename;
    }

    public FileGET toFileGetDto(TopicFileReferenceEntity ref) {
        FileEntity file = ref.getFile();

        FileGET dto = new FileGET();
        dto.setFilename(file.getFilename());
        dto.setReference(file.getReference());
        dto.setDate(file.getDate());
        dto.setIfcProject(ref.getIfcProjectGuid());
        dto.setIfcSpatialStructureElement(ref.getIfcSpatialStructureElementGuid());
        return dto;
    }
}
