package de.openfabtwin;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;
import de.openfabtwin.generated.extensions.Extensions;

import java.io.StringReader;

@Component
public class ExtensionXmlParser {

    private final JAXBContext jaxbContext;

    public ExtensionXmlParser() {
        try{
            this.jaxbContext = JAXBContext.newInstance(Extensions.class);
        } catch (JAXBException e){
            throw new IllegalStateException("Failed to initialize JAXBContext", e);
        }
    }

    public Extensions parse(String xml) {
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Extensions) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new IllegalStateException("Failed to unmarshal extension XML", e);
        }
    }
}
