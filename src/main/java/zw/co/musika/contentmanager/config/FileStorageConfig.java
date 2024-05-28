package zw.co.musika.contentmanager.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */
@Configuration
@EnableConfigurationProperties(FileStorageProperties.class)
public class FileStorageConfig {
    // No additional code needed here, just enabling configuration properties
}
