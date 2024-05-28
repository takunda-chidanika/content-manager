package zw.co.tjchidanika.contentmanager.exception;

/**************************************
 ** @project back-end           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/02/01                   
 **************************************
 */
public class ObjectNotFoundExceptionHandler extends RuntimeException{
    public ObjectNotFoundExceptionHandler(String object, String fieldName, String id) {
        super(String.format("%s not found with %s : '%s'", object, fieldName, id));
    }
}
