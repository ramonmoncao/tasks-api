package com.ramonmoncao.tasks_api.adapter.exception.handler;

import com.ramonmoncao.tasks_api.adapter.exception.handler.response.TaskErrorResponse;
import com.ramonmoncao.tasks_api.domain.exception.CreateTaskException;
import com.ramonmoncao.tasks_api.domain.exception.NotFoundException;
import com.ramonmoncao.tasks_api.utils.DataUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException ex){
        TaskErrorResponse error = TaskErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .timestamp(DataUtils.getFormatterDateTimeNow())
                .build();
        return buildResponseEntity(error, ex);
    }

    @ExceptionHandler(CreateTaskException.class)
    public ResponseEntity<Object> handleException(CreateTaskException ex){
        TaskErrorResponse error = TaskErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .timestamp(DataUtils.getFormatterDateTimeNow())
                .build();
        return buildResponseEntity(error, ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException ex){
        TaskErrorResponse error = TaskErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .timestamp(DataUtils.getFormatterDateTimeNow())
                .build();
        return buildResponseEntity(error, ex);
    }

    private ResponseEntity<Object> buildResponseEntity(TaskErrorResponse error, Exception ex) {
        return new ResponseEntity<>(error,error.getStatus());
    }
}
