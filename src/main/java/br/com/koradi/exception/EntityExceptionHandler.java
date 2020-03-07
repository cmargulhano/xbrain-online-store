package br.com.koradi.exception;

import br.com.koradi.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** @author Cláudio Margulhano */
@ControllerAdvice
@RestController
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EnterpriseException.EntityNotFoundException.class)
  public final ResponseEntity handleNotFountExceptions(Exception ex, WebRequest request) {
    Response response = Response.notFound();
    response.addErrorMsgToResponse(ex.getMessage(), ex);
    return new ResponseEntity(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EnterpriseException.DuplicateEntityException.class)
  public final ResponseEntity handleNotFountExceptions1(Exception ex, WebRequest request) {
    Response response = Response.duplicateEntity();
    response.addErrorMsgToResponse(ex.getMessage(), ex);
    return new ResponseEntity(response, HttpStatus.CONFLICT);
  }
}
