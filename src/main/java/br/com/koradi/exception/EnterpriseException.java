package br.com.koradi.exception;

import br.com.koradi.config.PropertiesConfig;
import java.text.MessageFormat;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A helper class to generate RuntimeExceptions
 *
 * <p>@author Cláudio Margulhano
 */
@Component
public class EnterpriseException {

  private static PropertiesConfig propertiesConfig;

  @Autowired
  public EnterpriseException(PropertiesConfig propertiesConfig) {
    EnterpriseException.propertiesConfig = propertiesConfig;
  }

  /**
   * Returns new RuntimeException based on template and args
   *
   * @param messageTemplate Message template
   * @param args arguments
   * @return {@link RuntimeException}
   */
  public static RuntimeException throwException(String messageTemplate, String... args) {
    return new RuntimeException(format(messageTemplate, args));
  }

  /**
   * Returns new RuntimeException based on EntityType, ExceptionType and args
   *
   * @param entityType Entity type
   * @param exceptionType Exception type
   * @param args arguments
   * @return {@link RuntimeException}
   */
  public static RuntimeException throwException(
      EntityType entityType, ExceptionType exceptionType, String... args) {
    String messageTemplate = getMessageTemplate(entityType, exceptionType);
    return throwException(exceptionType, messageTemplate, args);
  }

  /**
   * Returns new RuntimeException based on EntityType, ExceptionType and args
   *
   * @param entityType Entity type
   * @param exceptionType Exception type
   * @param args arguments
   * @return {@link RuntimeException}
   */
  public static RuntimeException throwExceptionWithId(
      EntityType entityType, ExceptionType exceptionType, String id, String... args) {
    String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id);
    return throwException(exceptionType, messageTemplate, args);
  }

  /**
   * Returns new RuntimeException based on EntityType, ExceptionType, messageTemplate and args
   *
   * @param entityType Entity type
   * @param exceptionType Exception type
   * @param messageTemplate Message template
   * @param args arguments
   * @return {@link RuntimeException}
   */
  public static RuntimeException throwExceptionWithTemplate(
      EntityType entityType, ExceptionType exceptionType, String messageTemplate, String... args) {
    return throwException(exceptionType, messageTemplate, args);
  }

  /** Helper EntityNotFoundException */
  static class EntityNotFoundException extends RuntimeException {
    EntityNotFoundException(String message) {
      super(message);
    }
  }

  /** Helper DuplicateEntityException */
  static class DuplicateEntityException extends RuntimeException {
    DuplicateEntityException(String message) {
      super(message);
    }
  }

  /**
   * Returns new RuntimeException based on template and args
   *
   * @param messageTemplate Message template
   * @param args arguments
   * @return {@link RuntimeException}
   */
  private static RuntimeException throwException(
      ExceptionType exceptionType, String messageTemplate, String... args) {
    if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
      return new EntityNotFoundException(format(messageTemplate, args));
    } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
      return new DuplicateEntityException(format(messageTemplate, args));
    }
    return new RuntimeException(format(messageTemplate, args));
  }

  /**
   * Gets message template
   *
   * @param entityType Entity type
   * @param exceptionType Exception type
   * @return Message template
   */
  private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
    return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
  }

  /**
   * Formats template based on properties
   *
   * @param template Template
   * @param args arguments
   * @return Formatted template
   */
  private static String format(String template, String... args) {
    Optional<String> templateContent =
        Optional.ofNullable(propertiesConfig.getConfigValue(template));
    if (templateContent.isPresent()) {
      return MessageFormat.format(templateContent.get(), args);
    }
    return String.format(template, args);
  }
}
