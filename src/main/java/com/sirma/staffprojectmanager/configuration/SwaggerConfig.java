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
		description = "Considering the description of the project requirements the application takes priority of the data " +
		              "provided via CSV file,\n" +
		              "and process that information into database. The main functionality of the project is to find a pair" +
		              " of employee ids with maximum\n" +
		              "total time they worked on the same project. Below that line it displays the total overlap time for " +
		              "each project they worked on.\n" +
		              "In addition of the above the application has functionalities to add, remove, update and filter data" +
		              " stored in the database.\n" +
		              "\n" +
		              "IMPORTANT: The application is blocked from reading the file if there is information in the database" +
		              " and that is why there is an endpoint \n" +
		              "to reprocess the file and replace the information in the database. On other hand if you " +
		              "add/edit/remove information through the API and stop the program,\n" +
		              "it will be added/updated/removed in the file."
	),
	servers = @Server(
		url = "http://localhost:8080"
	)
)
public class SwaggerConfig {

}
