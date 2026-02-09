# User Authentication System

A full-stack authentication system with Spring Boot backend and React frontend.

## Project Structure

```
.
├── backend/          # Spring Boot REST API
├── web/             # React web application
├── mobile/          # Mobile app (placeholder)
├── docs/            # Documentation
└── README.md
```

## Backend Setup

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Configuration

1. Create MySQL database:
```sql
CREATE DATABASE userauth;
```

2. Update `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userauth
spring.datasource.username=root
spring.datasource.password=your_password
jwt.secret=your_jwt_secret_key
```

3. Build and run:
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will run on `http://localhost:8080`

## Frontend Setup

### Prerequisites
- Node.js 16+
- npm 7+

### Installation and Running

```bash
cd web
npm install
npm run dev
```

The frontend will run on `http://localhost:5173`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### User
- `GET /api/user/me` - Get current user (protected)

## Features

- User registration with email validation
- Secure login with JWT tokens
- Password encryption using BCrypt
- Protected routes and API endpoints
- Responsive web interface
- User profile/dashboard

## Technologies

### Backend
- Spring Boot 3.2.2
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Tokens)
- BCrypt password encoding

### Frontend
- React 18
- React Router DOM
- Axios
- Vite

## Security

- Passwords are encrypted using BCrypt
- JWT tokens for stateless authentication
- CORS configuration for frontend origin
- Protected API endpoints
