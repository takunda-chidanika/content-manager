package zw.co.musika.contentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */
@Getter
@Setter
@AllArgsConstructor
public class ContentUpdateRequest {
    private String id;
    private String filename;
    private String url;
    private String type;
    private Long size;
}
