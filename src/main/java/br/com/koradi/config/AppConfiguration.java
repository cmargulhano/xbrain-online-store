package br.com.koradi.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.modelmapper.Conditions.isNotNull;
import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;
import static org.modelmapper.convention.MatchingStrategies.STRICT;
import static org.modelmapper.convention.NamingConventions.JAVABEANS_MUTATOR;

/**
 * Configure Application
 *
 * @author Cláudio Margulhano
 */
@Configuration
@EnableSwagger2
public class AppConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration()
        .setPropertyCondition(isNotNull())
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(PRIVATE)
        .setMatchingStrategy(STRICT)
        .setSourceNamingConvention(JAVABEANS_MUTATOR);
    return modelMapper;
    // https://github.com/modelmapper/modelmapper/issues/212
  }

  @Bean
  public RestTemplate rest() {
    return new RestTemplate();
  }

  @Bean
  public Docket swaggerVirtualStoreApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("XBrain")
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.koradi.controller.v1.api"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Test XBrain - REST APIs")
        .description("Test XBrain")
        .termsOfServiceUrl("")
        .contact(
            new Contact(
                "Cláudio Margulhano", "https://github.com/cmargulhano", "cmargulhano@gmail.com"))
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
        .version("1.0.0")
        .build();
  }
}
