/*package com.anurag.springbootapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

*//**
 * The Class SwaggerConfig is the SpringBoot Configuration class for
 * springfox.swagger.ui. This supports generation of swagger JSON for all the
 * REST end-points in the application.
 *//*
@Profile("!prd")
@Configuration
@EnableSwagger2
public class SwaggerConfig
{

	@Bean
	public Docket mainConfig()
	{
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any()).build()
				.genericModelSubstitutes(ResponseEntity.class);
	}
}
*/