package zw.co.musika.contentmanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zw.co.musika.contentmanager.dto.ContentResponse;
import zw.co.musika.contentmanager.service.FileStorageService;

import java.io.IOException;
import java.util.List;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */
@RequestMapping("/api/v1/file")
@RestController
public record FileController(
        FileStorageService fileStorageService
) {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    //    Upload Files
    @PostMapping("/upload-file")
    public ContentResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStorageService.uploadFile(file);
    }

    //    Upload Multiple File
    @PostMapping("/upload-files")
    public List<ContentResponse> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        return fileStorageService.uploadFiles(files);
    }

    //    Update Multiple File
    @PatchMapping("/update-file")
    public ContentResponse uploadFiles(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        return fileStorageService.updateFile(file, id);
    }

    //    Get By Id
    @GetMapping("/get-file/{id}")
    public ContentResponse getFile(@PathVariable String id) {
        return fileStorageService.getFileById(id);
    }

    //    Get All
    @GetMapping("/get-files")
    public List<ContentResponse> getFiles() {
        return fileStorageService.getAllFiles();
    }

    //    Delete By Id
    @DeleteMapping("/delete-file/{id}")
    public void deleteFile(@PathVariable String id) {
        fileStorageService.deleteFile(id);
    }

    //    Delete By Id
    @DeleteMapping("/delete-files")
    public void deleteFiles() {
        fileStorageService.deleteFiles();
    }

    // Download File
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
