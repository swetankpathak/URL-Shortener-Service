# URL Shortener

A simple URL shortener web application built using **Spring Boot**, **MySQL**, and **JavaScript**. This project allows you to shorten URLs, track their access counts, and set expiration times with an optional feature to extend expiration time.

## Features

- **Shorten URLs**: Convert long URLs into shortened versions.
- **Expiration Handling**: URLs are valid for 10 minutes by default.
- **Access Count Tracking**: Keeps a record of how many times a shortened URL was accessed.
- **Validation**: Validates URLs before shortening.
- **Auto Expiration Cleanup**: Automatically deletes expired URLs.
- **Frontend Integration**: Simple HTML, CSS, and JavaScript UI for interacting with the service.


## Getting Started

### Prerequisites
- Java 17+
- MySQL Server
- Maven

### Database Setup
Ensure you have MySQL installed and running. Create a database named `shortyurl`.

```sql
CREATE DATABASE shortyurl;
```

### Project Setup
1. Clone the repository:
   ```bash
   git clone <https://github.com/swetankpathak/URL-Shortener-Service>
   cd urlshortener
   ```
2. Update the `application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shortyurl
spring.datasource.username=root
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
3. A shortened URL will be generated with a **10-minute** validity.

### REST Endpoints

**Shorten URL:**
```http
POST /shorty/shorten
Body: { "longUrl": "https://example.com" }
```

**Redirect to Original URL:**
```http
GET /shorty/{shortUrl}
```


### Error Handling
- If an invalid URL is submitted, a popup will display: `Invalid URL`.
- If the URL is expired, it will automatically be deleted from the database.

### Code Highlights

#### Model (`UrlMapping.java`)
- Represents the URL mapping with fields for access count and expiration.

#### Service (`UrlService.java`)
- Handles URL shortening, validation, access count, and expiration logic.

#### Controller (`UrlController.java`)
- Provides REST endpoints for shortening and accessing URLs.

#### Frontend (`app.js`)
- Makes API calls and handles UI interactions like showing shortened URLs and expiration countdown.

## Technologies Used
- **Java 17**
- **Spring Boot 3.4.1**
- **MySQL**
- **HTML, CSS, JavaScript**

## License
This project is licensed under the MIT License.

