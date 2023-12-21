# Staff project assignment manager project

## Table of Contents
- [Project Description](#project-description)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Documentation](#api-documentation)

## Project Description

Considering the description of the project requirements the application takes priority of the data provided via CSV file,
and process that information into database. The main functionality of the project is to find a pair of employee ids with maximum
total time they worked on the same project. Below that line it displays the total overlap time for each project they worked on.
In addition of the above the application has functionalities to add, remove, update and filter data stored in the database.

IMPORTANT: The application is blocked from reading the file if there is information in the database and that is why there is an endpoint 
to reprocess the file and replace the information in the database. On other hand if you add/edit/remove information through the API and stop the program,
it will be added/updated/removed in the file.

## Installation

For running the application clone the repo and run the following command to build the project and download the required dependencies:
> *mvn clean install*

## Configuration
In order for the application the run as expected PostgresSql database is required. The default database name is set to "pas",
but can be changed in 'src/main/resources/application.properties' . The username and password for the database can be configured 
as well to successfully connect.

Default config:
> server.port=8080
> 
> spring.datasource.url=jdbc:postgresql://localhost:5432/pas
>
>spring.datasource.username=postgres
>
> spring.datasource.password=admin

## Usage
You can run the program after successful installation and configuration. <br>
* Run ⇨ Run 'StaffProjectManagerApplication'

The application can be either used with Postman requests or using the Swagger UI.

## API Documentation
The API documentation can be found at 'http://localhost:8080/swagger-ui/index.html' after starting the application.

