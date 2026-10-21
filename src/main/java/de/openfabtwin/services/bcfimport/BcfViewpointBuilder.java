package de.openfabtwin.services.bcfimport;

import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.entities.ViewpointEntity;
import de.openfabtwin.generated.visinfo.VisualizationInfo;
import de.openfabtwin.services.ViewpointService;
import de.openfabtwin.services.ViewpointService.ImageType;
import de.openfabtwin.utils.BcfZipReader.TopicFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BcfViewpointBuilder {

    private final ViewpointService viewpointService;

    public ViewpointEntity build(VisualizationInfo vi, byte[] snapshotData, ImageType snapshotType,
                                 int index, TopicEntity topic, TopicFolder folder) {
        return viewpointService.createFromVisualizationInfo(vi, snapshotData, snapshotType, index, topic, folder.bitmaps());
    }
}
