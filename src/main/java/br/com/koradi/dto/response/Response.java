package br.com.koradi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static br.com.koradi.dto.response.Response.Status.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.time.LocalDateTime.now;

/**
 * Response
 *
 * @author Cláudio Margulhano
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

  private Status status;
  private T payload;
  private Object errors;
  private Object metadata;

  public static <T> Response<T> badRequest() {
    Response<T> response = new Response<>();
    response.setStatus(BAD_REQUEST);
    return response;
  }

  public static <T> Response<T> ok() {
    Response<T> response = new Response<>();
    response.setStatus(OK);
    return response;
  }

  public static <T> Response<T> unauthorized() {
    Response<T> response = new Response<>();
    response.setStatus(UNAUTHORIZED);
    return response;
  }

  public static <T> Response<T> validationException() {
    Response<T> response = new Response<>();
    response.setStatus(VALIDATION_EXCEPTION);
    return response;
  }

  public static <T> Response<T> wrongCredentials() {
    Response<T> response = new Response<>();
    response.setStatus(WRONG_CREDENTIALS);
    return response;
  }

  public static <T> Response<T> accessDenied() {
    Response<T> response = new Response<>();
    response.setStatus(ACCESS_DENIED);
    return response;
  }

  public static <T> Response<T> exception() {
    Response<T> response = new Response<>();
    response.setStatus(EXCEPTION);
    return response;
  }

  public static <T> Response<T> notFound() {
    Response<T> response = new Response<>();
    response.setStatus(NOT_FOUND);
    return response;
  }

  public static <T> Response<T> duplicateEntity() {
    Response<T> response = new Response<>();
    response.setStatus(DUPLICATE_ENTITY);
    return response;
  }

  public void addErrorMsgToResponse(String errorMsg, Exception ex) {
    ResponseError error =
        new ResponseError().setDetails(errorMsg).setMessage(ex.getMessage()).setTimestamp(now());
    setErrors(error);
  }

  public enum Status {
    OK,
    BAD_REQUEST,
    UNAUTHORIZED,
    VALIDATION_EXCEPTION,
    EXCEPTION,
    WRONG_CREDENTIALS,
    ACCESS_DENIED,
    NOT_FOUND,
    DUPLICATE_ENTITY
  }

  @Getter
  @Accessors(chain = true)
  @JsonInclude(NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PageMetadata {
    private int size;
    private long totalElements;
    private int totalPages;
    private int number;

    public PageMetadata(int size, long totalElements, int totalPages, int number) {
      this.size = size;
      this.totalElements = totalElements;
      this.totalPages = totalPages;
      this.number = number;
    }
  }
}
