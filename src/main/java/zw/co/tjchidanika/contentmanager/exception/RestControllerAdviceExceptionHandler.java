package zw.co.tjchidanika.contentmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**************************************
 ** @project back-end           
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/02/01                   
 **************************************
 */
@RestControllerAdvice
public class RestControllerAdviceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExceptionHandler.class)
    public ProblemDetail handleObjectNotFoundException(ObjectNotFoundExceptionHandler exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ObjectAlreadyExistsExceptionHandler.class)
    public ProblemDetail handleObjectAlreadyExistsExceptionHandler(ObjectAlreadyExistsExceptionHandler exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
    }
}

