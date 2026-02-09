# Quick Start Guide

## Prerequisites

Before running the application, ensure you have:

1. **Java Development Kit (JDK)**
   - Java 17 or higher
   - Download: https://adoptopenjdk.net/ or https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

2. **Apache Maven**
   - Version 3.6.0 or higher
   - Download: https://maven.apache.org/download.cgi
   - Add Maven to system PATH

3. **Node.js**
   - Version 16 or higher
   - Download: https://nodejs.org/

4. **MySQL Server**
   - Version 8.0 or higher
   - Download: https://dev.mysql.com/downloads/mysql/

## Step-by-Step Setup

### 1. Database Setup

```bash
# Connect to MySQL
mysql -u root -p

# Create database
CREATE DATABASE userauth;
```

### 2. Backend Setup and Run

```bash
# Navigate to backend directory
cd backend

# Update application.properties with your MySQL credentials
# File: backend/src/main/resources/application.properties
# Update: spring.datasource.username and spring.datasource.password

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The backend will start on: `http://localhost:8080`

### 3. Frontend Setup and Run

```bash
# Navigate to web directory
cd web

# Install dependencies
npm install

# Start development server
npm run dev
```

The frontend will start on: `http://localhost:5173`

## Testing the Application

### 1. Register New User
- Open: http://localhost:5173/register
- Fill in the form:
  - Username: `testuser`
  - Email: `test@example.com`
  - Password: `password123`
  - First Name: `Test`
  - Last Name: `User`
- Click "Register"
- You should be redirected to login page

### 2. Login
- Open: http://localhost:5173/login
- Enter credentials:
  - Username/Email: `testuser` or `test@example.com`
  - Password: `password123`
- Click "Login"
- You should be redirected to dashboard

### 3. View Profile
- On dashboard, you'll see your profile information
- Click "Logout" to clear session and return to login

## Troubleshooting

### Backend won't start
- Ensure MySQL is running
- Check database credentials in application.properties
- Verify Java version: `java -version`
- Verify Maven installation: `mvn -v`

### Frontend won't connect to backend
- Check that backend is running on port 8080
- Ensure CORS is properly configured
- Clear browser cache and localStorage
- Check browser console for error messages

### Database connection fails
- Verify MySQL service is running
- Check username and password are correct
- Ensure database `userauth` exists
- Try connecting manually: `mysql -u root -p userauth`

### Port already in use
- Backend port 8080: Kill process on port 8080
- Frontend port 5173: Use `npm run dev -- --port 5174`

## API Endpoints Reference

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|----------------|
| POST | /api/auth/register | Register new user | No |
| POST | /api/auth/login | Login user | No |
| GET | /api/user/me | Get current user profile | Yes (JWT) |

## File Structure

```
backend/
├── pom.xml                    # Maven dependencies
└── src/main/java/com/aquino/userauth/
    ├── UserauthApplication.java
    ├── controller/            # REST endpoints
    ├── dto/                   # Data transfer objects
    ├── model/                 # Database entities
    ├── repository/            # Data access
    ├── security/              # JWT and auth config
    └── service/               # Business logic

web/
├── package.json               # NPM dependencies
├── vite.config.js            # Vite configuration
├── index.html                # HTML entry point
└── src/
    ├── api/                   # API client
    ├── components/            # React components
    ├── context/               # Auth context
    ├── pages/                 # Page components
    ├── App.jsx
    └── main.jsx
```

## Development Tips

### Environment Variables

For backend, create `.env` file in backend directory:
```
DB_USERNAME=root
DB_PASSWORD=your_password
JWT_SECRET=your_secret_key
```

### Building for Production

**Backend:**
```bash
cd backend
mvn clean package
java -jar target/userauth-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd web
npm run build
# Output in web/dist directory
```

### Debug Mode

**Backend:**
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx512m -Xms512m"
```

## Additional Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- React Documentation: https://react.dev/
- JWT Specification: https://tools.ietf.org/html/rfc7519
- MySQL Documentation: https://dev.mysql.com/doc/

## Support

For issues or questions, refer to:
- Backend logs: Console output from `mvn spring-boot:run`
- Frontend logs: Browser Developer Console (F12)
- Database logs: MySQL error log
