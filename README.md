# Resource Allocation System (Estuate Task - 22/12/2024)

A Spring Boot application for managing resource allocation based on skillsets and experience. The system allows searching for resources based on required skills and experience levels for projects.

Name: Hemanth Sai Chinthalapudi, Email: hemanthsai1566@gmail.com


## Features

- Search resources by multiple skills
- Filter resources by experience level
- Create new resources with skills
- MySQL database integration
- RESTful API endpoints
- Unit tests for service layer

## Technologies Used

- Java 17
- Spring Boot 2.7.0
- MySQL 8.0
- Maven
- JUnit 5
- Spring Data JPA

## Database Setup

1. Login to MySQL:
```bash
mysql -u root -p
```

2. Create database and tables:
```sql
CREATE DATABASE IF NOT EXISTS resource_allocation;
USE resource_allocation;

CREATE TABLE IF NOT EXISTS resources (
    resource_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    resource_name VARCHAR(255) NOT NULL,
    experience INT NOT NULL
);

CREATE TABLE IF NOT EXISTS resource_skills (
    resource_id BIGINT,
    skill VARCHAR(255) NOT NULL,
    FOREIGN KEY (resource_id) REFERENCES resources(resource_id) ON DELETE CASCADE
);
```

3. Insert sample data:
```sql
-- Resource 1: Dennis
INSERT INTO resources (resource_name, experience) VALUES ('Dennis', 4);
SET @dennis_id = LAST_INSERT_ID();
INSERT INTO resource_skills (resource_id, skill) VALUES 
(@dennis_id, 'Java'),
(@dennis_id, 'Spring'),
(@dennis_id, 'JMS'),
(@dennis_id, 'mysql'),
(@dennis_id, 'Angular'),
(@dennis_id, 'React'),
(@dennis_id, 'Web services'),
(@dennis_id, 'Nodejs');

-- Add more resources as needed
```

## Application Setup

1. Clone the repository:
```bash
git clone https://github.com/hemanthsaich/ResourceAllocationTask.git
cd ResourceAllocationTask
```

2. Configure database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resource_allocation
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

3. Build the application:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

## API Endpoints




### 1. Search Resources by Skills
- **URL**: `/api/resources/search`
- **Method**: POST
- **Request Body**: Array of skills
```json
["Java", "Redis", "Javascript"]
```
- **Response**: Array of matching resources


### 2. Search Resources by Skills and Experience
- **URL**: `/api/resources/search/experience`
- **Method**: POST
- **Query Param**: maxExperience
- **Request Body**: Array of skills
```json
["Mysql", "Docker", "Spring", "React"]

```
- **Response**: Array of matching resources with experience filter



### 3. Create New Resource
- **URL**: `/api/resources`
- **Method**: POST
- **Request Body**: Resource details
```json
{
    "resourceName": "Hemanth",
    "experience": 5,
    "skills": ["Java", "Spring", "React", "Docker"]
}
```

- **Response**: Created resource details

## Testing with Postman

1. Import the Postman collection
2. Test search by skills:
   ```bash
   POST http://localhost:8080/api/resources/search
   Body: ["Java", "Redis", "Javascript"]
   ```
![image](https://res.cloudinary.com/daafpg7r5/image/upload/v1734840349/search_by_skils.png)

3. Test search by skills and experience:
   ```bash
   POST http://localhost:8080/api/resources/search/experience?maxExperience=10
   Body: ["Mysql", "Docker", "Spring", "React"]
   ```

![image](https://res.cloudinary.com/daafpg7r5/image/upload/v1734840349/search_by_skills_and_experience.png)


4. Test creating new resource:
   ```bash
   POST http://localhost:8080/api/resources
   Body: {
       "resourceName": "Hemanth",
       "experience": 5,
       "skills": ["Java", "Spring", "React", "Docker"]
   }
   ```

![image](https://res.cloudinary.com/daafpg7r5/image/upload/v1734840350/Create_New_Resource.png)



## Running Tests

```bash
mvn test
```

![image](https://res-console.cloudinary.com/daafpg7r5/media_explorer_thumbnails/e9e06e839c52d587ac98b9f3114b8873/detailed)


## Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── estuate
│   │           └── resourceallocation
│   │               ├── controller
│   │               ├── model
│   │               ├── repository
│   │               └── service
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com
            └── estuate
                └── resourceallocation
                    └── service
                    └── controller

```
