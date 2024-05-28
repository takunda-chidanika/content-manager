package zw.co.tjchidanika.contentmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import zw.co.tjchidanika.contentmanager.dto.ContentResponse;

import java.time.LocalDateTime;
import java.util.List;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String filename;
    private String url;
    private String type;
    private Long size;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static ContentResponse toResponse(Content content) {
        return new ContentResponse(
                content.getId(),
                content.filename,
                content.getUrl(),
                content.getType(),
                content.getSize()
        );
    }

    public static List<ContentResponse> toResponseList(List<Content> contents) {
        return contents.stream().map(Content::toResponse).toList();
    }

}
