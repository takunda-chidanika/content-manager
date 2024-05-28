package zw.co.tjchidanika.contentmanager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */

@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private final String uploadDir;
    public FileStorageProperties(String uploadDir) {
        this.uploadDir = uploadDir;
    }

}