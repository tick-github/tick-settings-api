# Settings API

A Java API used to manage user settings

- [Settings API](#settings-api)
  - [Running the API](#running-the-api)
    - [Setting up a `docker compose`](#setting-up-a-docker-compose)
    - [Pulling the necessary services](#pulling-the-necessary-services)
    - [Running the docker instances with compose](#running-the-docker-instances-with-compose)
    - [Eureka](#eureka)
  - [Endpoints](#endpoints)
  - [Insecure endpoints](#insecure-endpoints)
    - [Ping](#ping)
  - [Secure endpoints](#secure-endpoints)
    - [Ping](#ping-1)


## Running the API

When pulling the repository, you have two ways of running the API:

1. Run the project on its own.
2. Run the project in tandem with the other services:
     * [Eureka Service Discovery](https://github.com/tick-github/tick-service-discovery)
     * [API Gateway](https://github.com/tick-github/tick-gateway)

\* *Be aware that the authorization is handled on the gateway level. If you try to run this service without the gateway, authorization is not supported and all endpoints are insecure.*

### Setting up a `docker compose`

To run the service with `docker compose`, please make sure to create a `docker-compose.yml` file in an empty folder, with the following contents:

```yml
name: tick
services:
  eureka-server:
    container_name: eureka-server
    build:
      context: tick-service-discovery
      dockerfile: Dockerfile
    ports:
      - "8761:8761"
    networks:
      - eureka
    restart: unless-stopped

  gateway:
    depends_on:
      - eureka-server
    container_name: gateway
    build:
      context: tick-gateway
      dockerfile: Dockerfile
    ports:
      - "8000:8000"
    networks:
      - eureka
    restart: unless-stopped

  settings-api:
    depends_on:
      - eureka-server
      - gateway
    container_name: settings-api
    build:
      context: tick-settings-api
      dockerfile: Dockerfile
    networks:
      - eureka
    restart: unless-stopped

networks:
  eureka:
    name: eureka
    driver: bridge

```

### Pulling the necessary services

Pull the [Eureka Service Discovery service](https://github.com/tick-github/tick-service-discovery) and the [API Gateway service](https://github.com/tick-github/tick-gateway) and place them in their own folder.

Your folder structure should look like this now:

```
root-folder
    └ tick-settings-api
        └ src
        └ (...)
    └ tick-gateway
        └ src
        └ (...)
    └ tick-service-discovery
        └ src
        └ (...)
    └ docker-compose.yml
```

### Running the docker instances with compose

If everything is in place, make sure you have Docker and Docker Compose installed. Open up a terminal application in the root folder, and run `docker compose up --build`. Give it a moment to start up.

### Eureka

When the services have started up, you will be able to verify the statuses on `http://localhost:8761`

## Endpoints

When you run the API, all endpoints can be access from the gateway url. By default, the Gateway is located at http://localhost:8000/

To see the API documentation, open `./docs/index.html` in your web browser.

This API is meant to be run through the gateway. All endpoints, excluding the insecure `Ping` endpoint, require an authorization header with a valid Google ID token (JWT). The gateway requires a stable internet connection for validating the ID token.