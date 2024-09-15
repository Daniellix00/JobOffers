 # Job Offers | Web Application

Job Offers is a Spring Boot-based web application designed to collect job listings from various sources specifically for Junior Java Developers. The application's main functionality revolves around retrieving up-to-date job offers from websites and other web applications, providing users with a consolidated view of current openings.

The project integrates several technologies and tools to ensure efficient data gathering, processing, and security. It supports JWT-based authentication, scheduled job fetching, and offers a well-structured testing approach using both unit and integration tests. All services are containerized and easily deployable with Docker.

This project helped me deepen my knowledge of modern Java and Spring Boot technologies, while also improving my experience with testing frameworks, DevOps practices, and teamwork. I have also implemented SCRUM-based workflows, participated in code reviews, and worked collaboratively with a teammate to ensure code quality and maintainability.

## Specification

- **Java 17**: Modern language features and performance optimizations
- **Spring Boot**:
  - Web (RestControllers)
  - Security (JWT)
  - Data (MongoDB)
  - Validation
  - Testing (JUnit5, Mockito, AssertJ)
  - Integration Testing (SpringBootTest, MockMvc, SpringSecurityTest)
  - Scheduled job fetching
- **MongoDB** with MongoExpress for database management
- **Redis** & **Jedis** for caching job offers, with Redis-Commander for easy management
- **Testing**:
  - Unit tests with JUnit5
  - Integration tests using Testcontainers, MockMvc, Wiremock, Awaitility
- **Logging**: Log4j2 for structured logging
- **Docker**: Full containerization of the application with Docker & Docker-Compose
- **CI/CD**: Jenkins pipeline for continuous integration and deployment
- **Swagger**: API documentation and testing interface
- **Version Control**: Git & GitHub/GitLab for repository management
- **Agile Methodology**: SCRUM, Jira for project management
- **Additional Tools**:
  - IntelliJ Ultimate for development
  - Maven for build automation
  - Code review and pair programming experience

## Deployment

The application is fully containerized and can be easily deployed using Docker-Compose. The following services are included:
- **MongoDB** with MongoExpress for data storage
- **Redis** for caching
- **Spring Boot** for the web application
- **Swagger UI** for API testing and interaction

