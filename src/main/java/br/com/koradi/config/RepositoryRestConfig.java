package br.com.koradi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.Set;
import java.util.regex.Pattern;

@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

    final ClassPathScanningCandidateComponentProvider provider =
        new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

    final Set<BeanDefinition> beans = provider.findCandidateComponents("br.com.koradi.model");

    for (BeanDefinition bean : beans) {
      Class<?> idExposedClasses = null;
      try {
        idExposedClasses = Class.forName(bean.getBeanClassName());
        config.exposeIdsFor(Class.forName(idExposedClasses.getName()));
      } catch (ClassNotFoundException e) {
        // Can't throw ClassNotFoundException due to the method signature. Need to cast it
        throw new RuntimeException("Failed to expose `id` field due to", e);
      }
    }
  }
}
