package de.openfabtwin.services;

import de.openfabtwin.entities.BimSnippetEntity;
import de.openfabtwin.entities.TopicEntity;
import de.openfabtwin.repositories.BimSnippetRepository;
import de.openfabtwin.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class SnippetService {

    private final EntityResolver entityResolver;
    private final BimSnippetRepository bimSnippetRepository;
    private final TopicRepository topicRepository;

    public byte[] getSnippetData(String projectId, String topicId) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        BimSnippetEntity snippet = topic.getBimSnippet();
        if (snippet == null || snippet.getSnippetData() == null) {
            throw new EntityNotFoundException("No snippet file found for topic: " + topicId);
        }
        return snippet.getSnippetData();
    }

    public void updateSnippetData(String projectId, String topicId, Resource body) {
        TopicEntity topic = entityResolver.resolveTopic(projectId, topicId);
        BimSnippetEntity snippet = topic.getBimSnippet();

        if (snippet == null) {
            throw new IllegalStateException("Topic has no BimSnippet metadata set yet");
        }
        if (snippet.getIsExternal()) {
            throw new IllegalArgumentException("is_external must be false to store snippet locally");
        }

        try (InputStream in = body.getInputStream()) {
            byte[] data = in.readAllBytes();
            if (data.length == 0) throw new IllegalArgumentException("Snippet data is empty");

            String filename = body.getFilename() != null ? body.getFilename() : "snippet.ifc";
            snippet.setSnippetData(data);
            snippet.setFilename(filename);
            snippet.setReference(filename);
            bimSnippetRepository.save(snippet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read snippet data", e);
        }
    }

}
