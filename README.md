# URL Shortener

A simple URL shortener web application built using **Java Spring Boot**, **MySQL**, and **JavaScript**. This project allows you to shorten URLs, and track their access counts.

## Features

- **Shorten URLs**: Convert long URLs into shortened versions.
- **Expiration Handling**: URLs are valid for 24 hours by default.
- **Access Count Tracking**: Keeps a record of how many times a shortened URL was accessed.
- **Validation**: Validates URLs before shortening.
- **Auto Expiration Cleanup**: Automatically deletes expired URLs every 24 hours.
- **Frontend Integration**: Simple HTML, CSS, and JavaScript UI for interacting with the service.

---

## Getting Started

### Prerequisites
- Java 17+
- MySQL Server
- Maven

### Database Setup
Ensure you have MySQL installed and running. Create a database named `shortyurl` and create the required table using the schema below:

```sql
CREATE DATABASE shortyurl;
USE shortyurl;

CREATE TABLE url_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    long_url TEXT NOT NULL,
    short_url VARCHAR(8) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    access_count INT DEFAULT 0,
    expiration_date TIMESTAMP NULL
);
```

### Project Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/swetankpathak/URL-Shortener-Service
   cd urlshortener
   ```
2. Update the `application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shortyurl
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Running the Frontend
The frontend files (`index.html`, `style.css`, and `app.js`) are located in the `src/main/resources/static` folder. To run the frontend:

1. Open `src/main/resources/static/index.html` in your browser.
2. Enter a long URL and click **Shorten**.
3. A shortened URL will be generated with a **24-hour** validity.

---


### Error Handling
- If an invalid URL is submitted, a popup will display: `Invalid URL`.
- If the URL is expired, it will automatically be deleted from the database.

### Technologies Used
- **Java 17**
- **Spring Boot 3.4.1**
- **MySQL**
- **HTML, CSS, JavaScript**

## License
This project is licensed under the MIT License.

