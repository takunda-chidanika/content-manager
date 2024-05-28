package zw.co.musika.contentmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.musika.contentmanager.dto.ContentResponse;
import zw.co.musika.contentmanager.dto.ContentSaveRequest;
import zw.co.musika.contentmanager.dto.ContentUpdateRequest;
import zw.co.musika.contentmanager.model.Content;
import zw.co.musika.contentmanager.repository.ContentRepository;

import java.util.ArrayList;
import java.util.List;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */
@Service
@RequiredArgsConstructor
public class ContentStorageService {
    private final ContentRepository contentRepository;

    // Save
    public ContentResponse save(ContentSaveRequest request) {
        Content content = Content.builder()
                .filename(request.getFilename())
                .url(request.getUrl())
                .size(request.getSize())
                .type(request.getType())
                .build();

        Content saved = contentRepository.save(content);
        return Content.toResponse(saved);
    }

    // Save All
    public List<ContentResponse> saveAll(List<ContentSaveRequest> requests) {
        List<Content> contents = new ArrayList<>();
        for (ContentSaveRequest request : requests) {
            Content content = Content.builder()
                    .filename(request.getFilename())
                    .url(request.getUrl())
                    .size(request.getSize())
                    .type(request.getType())
                    .build();
            contents.add(content);
        }

        return Content.toResponseList(contentRepository.saveAll(contents));
    }

    // Get All
    public List<ContentResponse> getAll() {
        return Content.toResponseList(contentRepository.findAll());
    }

    // Get By Id
    public ContentResponse getById(String id) {
        return Content.toResponse(contentRepository.findById(id).orElseThrow());
    }

    // Update
    public ContentResponse update(ContentUpdateRequest request) {
        Content content = contentRepository.findById(request.getId()).orElseThrow();

        content.setFilename(request.getFilename());
        content.setUrl(request.getUrl());
        content.setSize(request.getSize());
        content.setType(request.getType());

        return Content.toResponse(contentRepository.save(content));
    }

    // Delete By Id
    public void deleteById(String id) {
        contentRepository.deleteById(id);
    }

    // Delete All
    public void deleteAll() {
        contentRepository.deleteAll();
    }
}
