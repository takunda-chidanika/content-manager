package zw.co.tjchidanika.contentmanager.exception;

/**************************************
 ** @project back-end           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/02/01                   
 **************************************
 */
public class ObjectAlreadyExistsExceptionHandler extends RuntimeException{
    public ObjectAlreadyExistsExceptionHandler(String object, String fieldName, String id) {
        super(String.format("%s already exists with %s : '%s'", object, fieldName, id));
    }
}
