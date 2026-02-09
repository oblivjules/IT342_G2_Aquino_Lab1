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

### Project Structure & Configuration
- [x] Clean up duplicate project files
  - Commit: 02b6d3e
- [x] Update .gitignore for node_modules and artifacts
  - Commit: c4449e9
- [x] Remove obsolete documentation files
  - Commit: ad3135a

### Documentation
- [x] README.md documentation
  - Commit: 3d71cb5b6888a67e59d8e98ad8b1ca8b7f51e4f1
- [x] Software Requirements Specification (SRS) document
  - Commit: ad24bb4
- [x] Git repository initialization and commits
  - Commits: 3d71cb5, 4f1939d, 2a83905, 6959ea7, 02b6d3e, 2b97a9c, e03c6ff, 9871f36, e022abf, c4449e9, ad24bb4, ad3135a

## IN-PROGRESS
- [ ] None (all implementation tasks complete)

## TODO
- [ ] Capture application screenshots
  - Register page screenshot
  - Login page screenshot  
  - Dashboard screenshot
  - Profile page screenshot
  - Logout modal screenshot
- [ ] Capture database screenshots
  - MySQL users table structure
  - Sample user data in database
- [ ] Create/update ERD diagram
- [ ] Final documentation review
- [ ] Push to GitHub public repository
- [ ] MS Teams submission

## Notes

### Completed Features:
✅ Full backend REST API with Spring Boot
✅ Complete web application with React
✅ User registration and login with validation
✅ Protected routes and endpoints
✅ JWT-based authentication
✅ BCrypt password encryption
✅ MySQL database integration
✅ CORS configuration for frontend
✅ Responsive UI with modern styling
✅ Complete documentation

### Repository Structure:
```
IT342_G5_<Lastname>_Lab1
├── /web              (React frontend - READY)
├── /backend          (Spring Boot API - READY)
├── /mobile           (placeholder for future)
├── /docs             (documentation folder)
├── README.md         (Project overview)
├── QUICK_START.md    (Setup instructions)
├── IMPLEMENTATION_SUMMARY.md (Detailed documentation)
└── TASK_CHECKLIST.md (This file)
```

### How to Complete Remaining Tasks:

1. **Capture Screenshots:**
   - Start both backend and frontend
   - Take screenshots of Register, Login, Dashboard pages
   - Save to /docs folder

2. **Create FRS PDF:**
   - Include ERD from provided diagrams
   - Include UML diagrams from previous activity
   - Add captured web UI screenshots
   - Create professional PDF document

3. **Push to GitHub:**
   - Create new public GitHub repository named `IT342_G5_<Lastname>_Lab1`
   - Push code: `git push origin master`
   - Ensure all files are visible

4. **Submit in MS Teams:**
   - GitHub repository link
   - FRS PDF from /docs
   - Updated TASK_CHECKLIST.md
   - Brief description of implementation

