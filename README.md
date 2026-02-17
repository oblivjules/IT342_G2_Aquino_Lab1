# User Authentication System

A full-stack authentication system with Spring Boot backend, React web frontend, and Android Kotlin mobile app.

## Project Structure

```
.
├── backend/          # Spring Boot REST API
├── web/              # React web application
├── mobile/           # Android Kotlin mobile app
├── docs/             # Documentation
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

## Mobile App Setup

### Prerequisites
- Android Studio Koala+
- Android SDK 24+
- Gradle 8.0+
- JDK 17+

### Installation and Running

1. Open Android Studio and select "Open an Existing Project"
2. Navigate to `mobile/miniapp` directory
3. Let Gradle sync and download dependencies
4. Configure emulator or connect Android device
5. Run the app by clicking "Run" or pressing `Shift + F10`

The mobile app will connect to the backend at `http://localhost:8080` (configure API URL in `AuthApi.kt` if needed)

### Key Features
- Register with validation
- Login with credentials
- Persistent session using TokenStore
- Protected profile/dashboard screen
- Logout with confirmation dialog
- Real-time sync with Spring Boot backend

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `POST /api/auth/logout` - Logout user (requires Bearer token)

### User
- `GET /api/user/me` - Get current user (protected)

## Features

- User registration with email validation (Web & Mobile)
- Secure login with JWT tokens (Web & Mobile)
- Password encryption using BCrypt
- Protected routes and API endpoints
- Logout endpoint with token blacklist support
- Responsive web interface (React)
- Native mobile interface (Android Kotlin)
- User profile/dashboard (Web & Mobile)
- Persistent authentication with secure token storage (Mobile)
- Real-time API integration with standardized error responses

## Technologies

### Backend
- Spring Boot 3.2.2
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Tokens)
- BCrypt password encoding
- Global exception handling with standardized API responses

### Frontend (Web)
- React 18
- React Router DOM
- Axios
- Vite

### Mobile (Android)
- Kotlin
- Android Jetpack (ViewModel, Navigation)
- Retrofit 2
- OkHttp
- Jetpack Compose (for UI)
- Shared Preferences (for token storage)

## Security

- Passwords are encrypted using BCrypt
- JWT tokens for stateless authentication
- Token blacklist for logout functionality
- CORS configuration for frontend origin
- Protected API endpoints with Bearer token validation
- Secure token storage on mobile (encrypted shared preferences)
- OkHttp interceptors for automatic token injection in mobile requests
