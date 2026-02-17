# Task Checklist

## DONE

### Backend Implementation
- [x] Backend setup with Spring Boot 3.2.2
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] User model creation with JPA
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] User repository with custom queries
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] POST /api/auth/register endpoint
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] POST /api/auth/login endpoint
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] GET /api/user/me endpoint (protected)
  - Commit: 4f1939d
- [x] JWT token generation and validation
  - Commit: 6959ea7
- [x] BCrypt password encryption
  - Commit: 2a83905
- [x] Security configuration with CORS
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Database connection (MySQL)
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Fix Spring Security bean conflicts
  - Commit: 2a83905
- [x] Add createdAt to user profile response
  - Commit: 4f1939d
- [x] Add logout endpoint (/api/auth/logout) with token blacklist
  - Commit: feat:0286124
- [x] Implement error handling with standardized API responses
  - Commit: feat:0286124
- [x] Improve token validation and security configuration
  - Commit: feat:0286124 (fix:b8e0845)

### Frontend Implementation
- [x] React project setup with Vite
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Register page with form validation
  - Commit: e03c6ff
- [x] Login page with form validation
  - Commit: e03c6ff
- [x] Dashboard with welcome screen
  - Commit: 9871f36
- [x] Separate Profile page (protected)
  - Commit: e022abf
- [x] Logout functionality with confirmation modal
  - Commit: 9871f36
- [x] API integration with Axios
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Authentication context and state management
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Protected routes implementation
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Custom color scheme (hunter green, sage green, vanilla cream)
  - Commit: 2b97a9c
- [x] Success messages for registration
  - Commit: e03c6ff
- [x] Improved error handling and validation
  - Commit: e03c6ff
- [x] Responsive UI styling
  - Commit: 2b97a9c
- [x] Logout endpoint integration with token blacklist
  - Commit: feat:dcfa424

### Project Structure & Configuration
- [x] Clean up duplicate project files
  - Commit: 02b6d3e
- [x] Update .gitignore for node_modules and artifacts
  - Commit: c4449e9
- [x] Remove obsolete documentation files
  - Commit: ad3135a

### Mobile Application - Android Kotlin
- [x] Register screen with form validation
  - Commit: feat:cbff204
- [x] Login screen with credential validation
  - Commit: feat:cbff204
- [x] Dashboard/Profile screen (protected)
  - Commit: feat:cbff204
- [x] Logout functionality with confirmation dialog
  - Commit: feat:cbff204
- [x] API client and interceptor for backend integration
  - Commit: feat:cbff204
- [x] AuthViewModel for state management
  - Commit: feat:cbff204
- [x] Session and Token management for persistent authentication
  - Commit: feat:cbff204
- [x] Proper navigation flow between screens
  - Commit: feat:cbff204

### Documentation
- [x] README.md documentation
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Software Requirements Specification (SRS) document
  - Commit: ad24bb4
- [x] Application screenshots captured
  - Register, Login, Dashboard, Profile, Logout modal
- [x] Database screenshots captured
  - MySQL users table structure and sample data
- [x] ERD diagram included in documentation
- [x] Final documentation review completed
- [x] Git repository initialization and commits
  - Commits: 3d71cb5, 4f1939d, 2a83905, 6959ea7, 02b6d3e, 2b97a9c, e03c6ff, 9871f36, e022abf, c4449e9, ad24bb4, ad3135a, 882cc05
- [x] Pushed to GitHub public repository
  - Repository: IT342_G2_Aquino_Lab1

## Notes

### Completed Features:
✅ Full backend REST API with Spring Boot
✅ Backend logout endpoint with token blacklist support
✅ Standardized API responses with error handling
✅ Complete web application with React
✅ Complete mobile application with Android Kotlin
✅ User registration and login with validation (Web + Mobile)
✅ Protected routes and endpoints (Web + Mobile)
✅ JWT-based authentication
✅ BCrypt password encryption
✅ MySQL database integration
✅ CORS configuration for frontend
✅ Responsive UI with modern styling (Web + Mobile)
✅ Complete documentation

### Repository Structure:
```
IT342_G2_<Lastname>_Lab1
├── /web              (React frontend - READY)
├── /backend          (Spring Boot API - READY)
├── /mobile           (Android Kotlin app)
├── /docs             (documentation folder)
├── README.md         (Project overview)
└── TASK_CHECKLIST.md (This file)
```

