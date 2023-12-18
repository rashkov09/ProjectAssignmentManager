package com.sirma.staffprojectmanager.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
	info =@Info(
		title = "Project management",
		contact = @Contact(
			name = "Dobrin Rashkov"
		),
		license = @License(
		),
		description = "In Progress"
	),
	servers = @Server(
		url = "http://localhost:8080"
	)
)
public class SwaggerConfig {

}
