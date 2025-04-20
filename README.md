# EshopExpress

EshopExpress is a web-based gaming e-commerce platform built with Java, leveraging the power of Spring Boot for backend services and the flexibility of Java for data models and frontend templating. This project is designed to provide a robust, scalable foundation for selling video games, gaming accessories, and digital content with RESTful APIs, server-side rendering, and a layered architecture.

---

## Features

- **Game Catalog Management:** Add, update, and manage physical and digital games across platforms.
- **Gaming Peripherals & Accessories:** Manage gaming hardware, accessories, and merchandise.
- **User Authentication & Profiles:** Secure login, registration, and gamer profiles with wishlists.
- **Digital Content Delivery:** Support for digital game keys and download management.
- **Order Processing:** Track orders, inventory, and manage transactions.
- **Review & Rating System:** Allow users to rate and review games.
- **Database Integration:** Uses PostgreSQL (or MySQL) with Spring Data JPA.
- **Testing Suite:** Full unit and integration tests with JUnit 5 and Mockito.

## Tech Stack

| Component              | Technology                       |
|------------------------|----------------------------------|
| Backend                | Java (Spring Boot)               |
| Data Layer             | Java (POJOs, Spring Data JPA)    |
| Frontend Templating    | Thymeleaf / JSP                  |
| Database               | PostgreSQL / MySQL               |
| Testing                | JUnit 5, Mockito                 |

## Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/DanielHinbest/eshop-express.git
   cd eshop-express
   ```

2. **Set up the database:**
    - Ensure PostgreSQL (or MySQL) is installed and running.
    - Create a new database for the project.
    - Update the database connection settings in `application.yml` or `application.properties`.

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   or with Gradle:
   ```bash
   ./gradlew bootRun
   ```

4. **Access the application:**
    - Visit `http://localhost:8080` in your browser.

## Documentation

For detailed specifications, architecture, and examples, see the [project documentation](docs/SPECS.md).

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## Contact

For questions or feedback, please feel free to reach out to me on [GitHub](https://github.com/DanielHinbest) or via email.