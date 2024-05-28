package zw.co.musika.contentmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.musika.contentmanager.model.Content;

/**************************************
 ** @project content-manager           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/05/28                   
 **************************************
 */

@Repository
public interface ContentRepository extends JpaRepository<Content, String> {
}
