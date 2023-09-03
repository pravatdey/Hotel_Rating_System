# Hotel_Rating_System
  The Hotel Rating System, built on Spring Boot with a microservices architecture, facilitates hotel rating and reviews. Scalable, modular, and resilient, it empowers hotel businesses to gather customer feedback efficiently. This GitHub repo hosts the source code, offering customization options for diverse hotel enterprises.
## About

The Hotel Rating System is a modern, microservices-based application built using Spring Boot. It allows users to rate and review hotels, providing valuable feedback for travelers.

## Features

- User registration and authentication
- Hotel listing and details
- User reviews and ratings
- Multiple database support
- JWT-based security with Okta integration
- ...

## Architecture

Our application follows a microservices architecture with three databases: PostgreSQL, MySQL, and MongoDB. Each microservice handles specific functionalities such as user management, hotel data, and reviews.

## Technologies Used

- Java
- Spring Boot
- Spring Cloud
- Microservices
- PostgreSQL, MySQL, MongoDB
- Okta (for JWT token-based security)
- Docker

## Getting Started

To run this project locally, follow these steps:

```shell
# Clone the repository
git clone https://github.com/yourusername/hotel-rating-system.git

# Navigate to the project directory
cd hotel-rating-system

# Build and run the microservices
docker-compose up -d

# Visit http://localhost:3434 in your browser
