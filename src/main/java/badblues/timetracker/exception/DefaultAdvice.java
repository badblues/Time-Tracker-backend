package badblues.timetracker.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@ControllerAdvice
public class DefaultAdvice {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleException(CustomException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    } 


}