# Coding Assignment Solution

This repository contains the solution to the coding assignment. Due to time constraints, the solution primarily relies on Spring Boot’s default configurations. 
Given the lack of precise information on expected system usage, user base, and macro environment, these defaults were used as a reasonable choice.

For the same time constraint reason, the test coverage is limited.

## Table of Contents
- [Live Demo & API Testing](#live-demo--api-testing)
- [Project Structure](#project-structure)
- [Running the Services](#running-the-services)
    - [Running Services Using Gradle](#running-services-using-gradle)
    - [Running Using Kubernetes](#running-using-kubernetes)
- [Development Database](#development-database)
- [Building the Docker Images](#building-the-docker-images)

## Live Demo & API Testing
A live demo of the deployed services is available at: https://assignment.orthae.xyz/ running inside a Kubernetes with nginx ingress controller.

For API testing, a Postman collection is included in this repository. There is not OpenAPI documentation available.
You can use it to quickly test endpoints without setting up the project locally.

## Project Structure
```
├── build              # Docker files for image definitions
│   └── ...
├── database           # Database schemas
│   └── ...
├── infrastructure     # Kubernetes deployment files 
│   └── ...
├── task-service       # Task Service acting as a resource server
│   └── ...
├── user-service       # User Service acting as an authorization server
│   └── ...
├── docker-compose.yml # Docker Compose file for building services
├── postman.json       # Postman collection for API testing
└── ...
```

## Running the Services

Each service can be run independently using Gradle. However, if you run multiple services simultaneously, you may encounter port conflicts.  
To avoid this, you will need to modify the port configuration for one of the services.

Alternatively, you can deploy the services using Kubernetes with the provided deployment files.

> **Database Requirement:**  
> A PostgreSQL instance is required, either running locally or hosted by a provider.
> - If running locally, update the `application-local.yml` file in each service with the correct database connection details.
> - If deploying with Kubernetes, update the environment variables in the deployment files.

Database schemas are available in the `database` folder.

### Running Services Using Gradle

To run a service using Gradle, navigate to the service directory and execute:

```shell
./gradlew run
```

This runs the service with the local profile. By default, it will use the following database configuration:

```yaml
datasource:
  url: jdbc:postgresql://localhost:5432/postgres?currentSchema=public
  username: postgres
  password: root
```

Adjust these values as needed.

### Running Using Kubernetes

To deploy the services using Kubernetes, use the deployment files located in the `infrastructure` folder.

If you are not using the provided development database and Minikube, update the database connection details in the deployment files.

To deploy the services, run:

```shell
kubectl apply -f user-service-deployment.yml
kubectl apply -f task-service-deployment.yml
```

#### Accessing the Services

To expose the services externally, you need an ingress controller.

- **For local development:** Use Nginx Ingress Controller
- **For AWS deployment:** Use AWS ALB Ingress Controller

Example command for deploying Nginx Ingress Controller:

```shell
kubectl apply -f ingress-nginx.yml
```

### Development Database

A preconfigured PostgreSQL development database is available as a Docker image.  
To run the database container:

```shell
docker run --name development-database -p 5432:5432 -e POSTGRES_PASSWORD=root -d orthae/dev-db
```

### Building the Docker Images
You can build the Docker images for the services using the provided `docker-compose.yml`.
To build the images, run:

```shell
docker-compose build`
```