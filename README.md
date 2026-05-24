# User Service

The User Service is a microservice that stores information about all users in the system. This service is built using **Java (Spring Boot)** and stores its data in **SQLite**.

This service runs on port **8081** by default.

## Prerequisites
- Java 17 or later
- Maven

## How to Run

1. Open your terminal and navigate to the `user-service` directory:
    ```bash
    cd user-service
    ```

2. Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
    The application will start at `http://localhost:8081`. The SQLite database (`users.db`) will be automatically created in this directory if it doesn't exist.

## API Endpoints

### 1. Health Check
Checks if the service is running.
```
GET /users/health
```
**Response:**
```
User service is live!
```
**curl example:**
```bash
curl http://localhost:8081/users/health
```

### 2. Get All Users
Retrieves all users with pagination, sorted by the latest creation time in descending order.
```
GET /users
```
**Parameters:**
- `page_num` (int, default: 1)
- `page_size` (int, default: 10)

**Response:**
```json
{
    "result": true,
    "users": [
        {
            "id": 1,
            "name": "Suresh Subramaniam",
            "created_at": 1475820997000000,
            "updated_at": 1475820997000000
        }
    ]
}
```
**curl example:**
```bash
curl "http://localhost:8081/users?page_num=1&page_size=10"
```

### 3. Get Specific User
Retrieves a specific user by their ID.
```
GET /users/{id}
```
**Response:**
```json
{
    "result": true,
    "user": {
        "id": 1,
        "name": "Suresh Subramaniam",
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000
    }
}
```
**curl example:**
```bash
curl http://localhost:8081/users/1
```

### 4. Create User
Creates a new user.
```
POST /users
Content-Type: application/x-www-form-urlencoded
```
**Parameters:**
- `name` (string, required)

**Response:**
```json
{
    "result": true,
    "user": {
        "id": 1,
        "name": "Suresh Subramaniam",
        "created_at": 1475820997000000,
        "updated_at": 1475820997000000
    }
}
```
**curl example:**
```bash
curl -X POST http://localhost:8081/users -d "name=John%20Doe"
```