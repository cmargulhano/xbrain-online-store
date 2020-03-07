package br.com.koradi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Load custom properties
 *
 * @author Cláudio Margulhano
 */
@Component
@PropertySource("classpath:custom.properties")
public class PropertiesConfig {
  @Autowired private Environment env;

  /**
   * Gets config value
   *
   * @param configKey key
   * @return value
   */
  public String getConfigValue(String configKey) {
    return env.getProperty(configKey);
  }
}
