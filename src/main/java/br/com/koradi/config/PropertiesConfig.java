package br.com.koradi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/** @author Cláudio Margulhano */
@Component
@PropertySource("classpath:custom.properties")
public class PropertiesConfig {
  @Autowired private Environment env;

  public String getConfigValue(String configKey) {
    return env.getProperty(configKey);
  }
}
