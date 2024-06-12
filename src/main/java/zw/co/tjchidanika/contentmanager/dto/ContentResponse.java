package zw.co.tjchidanika.contentmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */

@Setter
@Getter
@AllArgsConstructor
public class ContentResponse {
    private String id;
    private String filaName;
    private String url;
    private long size;
    private String fileType;
}
