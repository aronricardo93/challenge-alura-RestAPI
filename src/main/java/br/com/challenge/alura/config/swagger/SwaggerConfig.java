package br.com.challenge.alura.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	  public Docket getBean() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    	.select()
	    	.apis(RequestHandlerSelectors.any())
	        .paths(PathSelectors.any())
	        .build()
	        .apiInfo(getInfo());
	  }
	  
	  private ApiInfo getInfo() {
	    return new ApiInfoBuilder()
	        .title("Controle Familiar API")
	        .description("Sistema de controle familiar de receitas e despesas")
	        .build();
	  }
}
