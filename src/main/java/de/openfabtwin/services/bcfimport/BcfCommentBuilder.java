package de.openfabtwin.services.bcfimport;

import de.openfabtwin.entities.CommentEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.entities.ViewpointEntity;
import de.openfabtwin.generated.markup.Comment;
import de.openfabtwin.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BcfCommentBuilder {

    public CommentEntity build(Comment c, TopicEntity topic, List<ViewpointEntity> viewpoints) {
        CommentEntity entity = new CommentEntity();
        entity.setTopic(topic);
        entity.setGuid(c.getGuid());
        entity.setAuthor(c.getAuthor());
        entity.setComment(c.getComment());
        entity.setDate(DateUtils.toInstant(c.getDate()));
        entity.setModifiedDate(DateUtils.toInstant(c.getModifiedDate()));
        entity.setModifiedAuthor(c.getModifiedAuthor());

        if (c.getViewpoint() != null && c.getViewpoint().getGuid() != null) {
            viewpoints.stream()
                    .filter(vp -> vp.getGuid().equals(c.getViewpoint().getGuid()))
                    .findFirst()
                    .ifPresent(entity::setViewpoint);
        }

        return entity;
    }
}