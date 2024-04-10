# W2M Technical Test: Spaceships CRUD REST API

Welcome to the W2M Technical Test project! This is a Spring Boot application that provides CRUD (Create, Read, Update, Delete) operations for Spaceships from various movies. 

This application use an in-memory H2 database and leverages Redis for caching.

## Technologies Used

- Java
- Spring Boot
- H2 Database
- Redis
- Maven

## Features

- **CRUD Operations**: Create, Read, Update, and Delete Spaceships.
  
- **In-Memory Database**: Utilizes H2 database for quick and easy data access.
  
- **Caching**: Implements Redis to cache data for certain methods, improving performance.
  
- **RESTful API**: Exposes endpoints for interacting with the application.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Maven
- Redis

### Installing

1. Clone the repository

  git clone https://github.com/rckpz/W2M-TT.git

2. Navigate to the project directory

  cd W2M-TT

3. Build the project with Maven

  mvn clean install

4. Run the Spring Boot application

  mvn spring-boot:run


## Usage

Once the application is running, you can interact with it using the exposed RESTful API endpoints. Here are the available endpoints:

- **Create a new ship**: Send a POST request to `http://localhost:8080/api/ships` with the ship data in the request body. The ship data should be in the following format:

  {
    "name": "Ship Name",
    "movie": "Movie Name",
  }

- **List all ships**: Send a GET request to `http://localhost:8080/api/ships`. This will return a paginated list of all ships, with 5 ships per page.

  To navigate between pages, add a `page` parameter to the URL, like so: `http://localhost:8080/api/ships?page=1`.

- **View a specific ship**: Send a GET request to `http://localhost:8080/api/ships/{id}`, replacing `{id}` with the ID of the ship you want to view.

- **Search for ships by name**: Send a GET request to `http://localhost:8080/api/ships/name/{name}`, replacing `{name}` with the name or part of the name of the ships you want to find.

- **Update a ship**: Send a PUT request to `http://localhost:8080/api/ships/{id}`, replacing `{id}` with the ID of the ship you want to update. Include the updated ship data in the request body.

- **Delete a ship**: Send a DELETE request to `http://localhost:8080/api/ships/{id}`, replacing `{id}` with the ID of the ship you want to delete.

Remember that all requests should be sent to the base URL of your application.

## API Documentation

This project uses Swagger to generate API documentation. Swagger provides a user-friendly, interactive interface for users to try out the API's endpoints without having to create a client.

Once the application is running, you can access the Swagger UI by navigating to `http://localhost:8080/swagger-ui.html` in your web browser.

Here, you'll find a list of all the controllers, models, and their related endpoints. You can expand each endpoint to view additional details such as the expected input format, required parameters, and the response format. You can also try out each endpoint directly from the Swagger UI.

For more detailed information about an endpoint, click on it. This will open a panel with more information, including a description of what the endpoint does, its parameters, and the response messages it may return. You can also send a request to the endpoint from this panel.

Remember to replace `localhost:8080` with the root URL of your deployed application if you're not running the application locally.
## License

This project is licensed under the MIT License.
