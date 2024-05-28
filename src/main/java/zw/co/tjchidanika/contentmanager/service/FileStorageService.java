package zw.co.tjchidanika.contentmanager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zw.co.tjchidanika.contentmanager.config.FileStorageProperties;
import zw.co.tjchidanika.contentmanager.dto.ContentResponse;
import zw.co.tjchidanika.contentmanager.dto.ContentSaveRequest;
import zw.co.tjchidanika.contentmanager.dto.ContentUpdateRequest;
import zw.co.tjchidanika.contentmanager.exception.FileNotFoundException;
import zw.co.tjchidanika.contentmanager.exception.FileStorageException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */

@Service

public class FileStorageService {

    private final Path fileStorageLocation;
    private final ContentStorageService contentStorageService;
    @Value("${file.base-url}")
    private String baseUrl;


    public FileStorageService(FileStorageProperties fileStorageProperties, ContentStorageService contentStorageService) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.contentStorageService = contentStorageService;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public ContentResponse uploadFile(MultipartFile file) {
        return contentStorageService.save(toContentSaveRequest(file));
    }

    public List<ContentResponse> uploadFiles(MultipartFile[] files) {
        return contentStorageService.saveAll(Arrays.stream(files).map(
                this::toContentSaveRequest
        ).toList());
    }

    public ContentResponse getFileById(String fileId) {
        return contentStorageService.getById(fileId);
    }

    public List<ContentResponse> getAllFiles() {
        return contentStorageService.getAll();
    }

    public ContentResponse updateFile(MultipartFile file, String id) {
        ContentResponse content = contentStorageService.getById(id);
        deleteFileFromDir(content.getFilaName());

        return contentStorageService.update(toContentUpdateRequest(file, id));
    }

    public void deleteFile(String id) {
        ContentResponse content = contentStorageService.getById(id);
        deleteFileFromDir(content.getFilaName());
        contentStorageService.deleteById(id);
    }

    public void deleteFiles() {
        List<ContentResponse> contentResponses = contentStorageService.getAll();

        for(ContentResponse contentResponse : contentResponses) {
            deleteFileFromDir(contentResponse.getFilaName());
        }

        contentStorageService.deleteAll();
    }

    private String storeFile(MultipartFile file) {
        // Normalize file name
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Generate a unique file name using UUID
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        try {
            if (uniqueFileName.contains("..")) {
                throw new FileStorageException("Sorry ! Filename contains invalid path sequence " + originalFileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + originalFileName + ". Please try again!", ex);
        }
    }

    private ContentSaveRequest toContentSaveRequest(MultipartFile file) {
        String fileName = storeFile(file);
        String fileDownloadUri = baseUrl + "/" + fileName;

        return new ContentSaveRequest(
                fileName,
                fileDownloadUri,
                file.getContentType(),
                file.getSize()
        );
    }

    private ContentUpdateRequest toContentUpdateRequest(MultipartFile file, String id) {
        String fileName = storeFile(file);
        String fileDownloadUri = baseUrl + "/" + fileName;

        return new ContentUpdateRequest(
                id,
                fileName,
                fileDownloadUri,
                file.getContentType(),
                file.getSize()
        );
    }

    private void deleteFileFromDir(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            System.out.println("TJC" + filePath);
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new FileStorageException("Could not delete file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

}
