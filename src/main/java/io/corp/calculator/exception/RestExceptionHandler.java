package io.corp.calculator.exception;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.nio.file.AccessDeniedException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

  

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNulPointer(NullPointerException ex) {
        printException(ex);
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, ex.getMessage(), ex);

        return buildResponseEntity(apiError);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        printException(ex);
        ApiError apiError = new ApiError(FORBIDDEN, ex.getMessage(), ex);

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        printException(ex);
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, ex);

        return buildResponseEntity(apiError);
    }
    
   
    @ExceptionHandler(CalculatorException.class)
    protected ResponseEntity<Object> handleStatementServiceException(CalculatorException ex) {
        printException(ex);
        
        String message="";
        
        if (ex.getCause()!=null) {
    		message="Unexpected Error Ocurred with message: "+ex.getCause().getMessage()+ " -> CLASS : "+ex.getCause().getClass().getName();
    	}
    	else {
    		message="Unexpected Error Ocurred with message: "+ex.getMessage()+ " -> CLASS : "+ex.getClassName()+" METHOD : "+ex.getMethodName();
    	}
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, message, ex);
        return buildResponseEntityForServiceException(apiError,ex);
    }
    
    
    private ResponseEntity<Object> buildResponseEntityForServiceException(ApiError apiError,CalculatorException ex) {
    	if (ex.getCause()!=null) {
    		log.error("EXCEPTION RUNTIME IN SERVICE: " + apiError.getMessage()+ " WITH CAUSE: "+ex.getCause().getMessage()+ " IN CLASS : "+ex.getClass().getName());
    	}
    	else {
    		log.error("EXCEPTION RUNTIME IN SERVICE: " + apiError.getMessage()+" IN CLASS:"+ex.getClassName()+ " IN METHOD: "+ex.getMethodName());
    	}
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

  
    protected ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        log.error("EXCEPTION: " + apiError.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    protected void printException(Throwable throwable) {
    	if (throwable!=null)
    		log.error(ExceptionUtils.getStackTrace(throwable));
    }
}