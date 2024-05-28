Here's an updated README file that includes the information about your project and its endpoints as shown in the Swagger UI screenshot.

```markdown
# Content Manager System/Service

## Overview
This project is a Content Manager System/Service developed using Spring Boot. It utilizes Java version 21 and leverages Docker for managing the database and an Nginx Docker image for rendering files.

## Features
- **Spring Boot** for application development.
- **Java 21** as the programming language.
- **Docker** for containerized database management.
- **Nginx Docker Image** for serving static files.

## Getting Started

### Prerequisites
- Java 21
- Docker
- Docker Compose

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/content-manager-system.git
    cd content-manager-system
    ```

2. **Build the project:**
    ```bash
    ./mvnw clean install
    ```

3. **Setup and run Docker containers:**
    - Ensure Docker and Docker Compose are installed and running on your machine.
    - Create a `docker-compose.yml` file to manage the database and Nginx services:
      ```yaml
      version: '3.8'

      services:
        db:
          image: postgres:latest
          container_name: postgres
          environment:
            POSTGRES_USER: user
            POSTGRES_PASSWORD: password
            POSTGRES_DB: contentdb
          ports:
            - "5432:5432"
          volumes:
            - db-data:/var/lib/postgresql/data

        nginx:
          image: nginx:latest
          container_name: nginx
          ports:
            - "8080:80"
          volumes:
            - ./nginx.conf:/etc/nginx/nginx.conf:ro
            - ./static:/usr/share/nginx/html:ro

      volumes:
        db-data:
      ```

4. **Run Docker Compose:**
    ```bash
    docker-compose up -d
    ```

5. **Run the Spring Boot application:**
    ```bash
    ./mvnw spring-boot:run
    ```

6. **Access the application:**
    - The Spring Boot application will run on `http://localhost:9090`.
    - The Nginx server will serve static files on `http://localhost:8080`.

## API Endpoints

### File Controller

- **Upload Multiple Files**
  - **POST** `/api/v1/file/upload-files`
  - Description: Upload multiple files.
  - Request: Multipart form data containing the files.

- **Upload Single File**
  - **POST** `/api/v1/file/upload-file`
  - Description: Upload a single file.
  - Request: Multipart form data containing the file.

- **Update File**
  - **PATCH** `/api/v1/file/update-file`
  - Description: Update an existing file.
  - Request: Multipart form data containing the file and old filename.

- **Get Files**
  - **GET** `/api/v1/file/get-files`
  - Description: Retrieve a list of all files.

- **Get File by ID**
  - **GET** `/api/v1/file/get-file/{id}`
  - Description: Retrieve a file by its ID.
  - Parameters: `id` (path parameter) - The ID of the file.

- **Download File**
  - **GET** `/api/v1/file/downloadFile/{fileName}`
  - Description: Download a file by its filename.
  - Parameters: `fileName` (path parameter) - The name of the file to download.

- **Delete Multiple Files**
  - **DELETE** `/api/v1/file/delete-files`
  - Description: Delete multiple files.
  - Request: JSON body containing the list of file IDs.

- **Delete File by ID**
  - **DELETE** `/api/v1/file/delete-file/{id}`
  - Description: Delete a file by its ID.
  - Parameters: `id` (path parameter) - The ID of the file to delete.

## Configuration
- **Database Configuration:** Update the `application.properties` or `application.yml` file in `src/main/resources` with your database settings:
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/contentdb
  spring.datasource.username=user
  spring.datasource.password=password
  spring.jpa.hibernate.ddl-auto=update
  ```

- **Nginx Configuration:** Customize the `nginx.conf` file to configure how Nginx serves your files.

## Usage

1. **Creating Content:**
    - Access the `/api/v1/file/upload-file` endpoint to upload new content.

2. **Managing Content:**
    - Access the `/api/v1/file/update-file` endpoint to edit existing content.
    - Access the `/api/v1/file/delete-file/{id}` endpoint to delete content.

3. **Viewing Content:**
    - Access the `/api/v1/file/get-files` endpoint to view all files.
    - Access the `/api/v1/file/get-file/{id}` endpoint to view a specific file.
    - Access the `/api/v1/file/downloadFile/{fileName}` endpoint to download a file.

## Contributing
Contributions are welcome! Please fork this repository and create a pull request with your changes. Ensure your code adheres to the project's coding standards and includes relevant tests.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact
For any inquiries or support, please contact [your-email@example.com].
```

### Explanation

- **Overview**: Briefly describes the project and its main technologies.
- **Features**: Lists key features of the project.
- **Getting Started**: Provides detailed steps to set up and run the project.
- **API Endpoints**: Lists all API endpoints as shown in the Swagger UI with descriptions and request parameters.
- **Configuration**: Guides on configuring the database and Nginx.
- **Usage**: Explains how to use the system endpoints.
- **Contributing**: Encourages contributions with a brief guide.
- **License**: Specifies the license under which the project is released.
- **Contact**: Provides a way to get in touch for support or inquiries.

Feel free to customize any section to better suit your project's specific details and requirements.
