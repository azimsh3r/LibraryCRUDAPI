**Library Management System API**

This API provides endpoints for managing entities such as Person and Book within a library system. It is built using Spring MVC framework, with Thymeleaf for rendering views. Data persistence is handled by Spring Data JPA, with PostgreSQL as the underlying database. Additionally, security measures are implemented to protect endpoints with role-based access control.

### Endpoints:

Authentication Endpoints:
   GET /auth/login: Display the login page.
   GET /auth/registration: Display the registration page.
   POST /auth/registration: Process user registration.

1. **Person Endpoints:**
   - `GET /api/persons`: Retrieve a list of all persons.
   - `GET /api/persons/{id}`: Retrieve details of a specific person by ID.
   - `POST /api/persons`: Create a new person.
   - `PUT /api/persons/{id}`: Update details of an existing person.
   - `DELETE /api/persons/{id}`: Delete a person by ID.

2. **Book Endpoints:**
   - `GET /api/books`: Retrieve a list of all books.
   - `GET /api/books/{id}`: Retrieve details of a specific book by ID.
   - `POST /api/books`: Create a new book.
   - `PUT /api/books/{id}`: Update details of an existing book.
   - `DELETE /api/books/{id}`: Delete a book by ID.

### Security:

Access to the API endpoints is secured using Spring Security, which supports various authentication mechanisms such as JWT (JSON Web Tokens), OAuth, or Basic Authentication. Additionally, endpoints are protected with role-based access control to ensure that only authorized users can perform certain operations. 

### Views:

Thymeleaf is used for server-side rendering of views, providing a seamless integration with Spring MVC. Views are designed to provide a user-friendly interface for interacting with the library system, allowing users to manage persons and books efficiently.

### Database:

The API utilizes PostgreSQL as the database for storing both Person and Book entities. Spring Data JPA simplifies the data access layer by providing convenient CRUD operations and query methods, making it easier to interact with the database.

### Getting Started:

To run the API locally, follow these steps:
1. Clone the repository from [GitHub link].
2. Configure the PostgreSQL database connection properties in `application.properties`.
3. Run the application using your preferred IDE or build tool (e.g., Maven or Gradle).
4. Access the API endpoints through the specified base URL.
5. Authenticate with appropriate credentials and roles to access secured endpoints.

### Dependencies:

- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- PostgreSQL JDBC Driver

### License:

This project is licensed under the MIT License. See the LICENSE file for details.

### Support:

For any inquiries or issues, please contact azimjon.works@gmail.com. Bug reports and feature requests are welcome.
