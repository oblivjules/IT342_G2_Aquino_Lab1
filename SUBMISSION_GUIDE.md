# SUBMISSION GUIDE

This document explains what has been completed and how to submit the project.

## What Has Been Completed ✅

### 1. Backend - Spring Boot REST API
**Location:** `/backend`

Complete authentication system with:
- ✅ User registration endpoint (`POST /api/auth/register`)
- ✅ User login endpoint (`POST /api/auth/login`)
- ✅ Protected user profile endpoint (`GET /api/user/me`)
- ✅ JWT-based authentication (60-minute tokens)
- ✅ BCrypt password encryption
- ✅ MySQL database integration
- ✅ CORS configuration for React frontend
- ✅ Input validation and error handling

**Key Files:**
- `AuthController.java` - Registration and login endpoints
- `UserController.java` - Protected user profile endpoint
- `AuthService.java` - Business logic for authentication
- `TokenProvider.java` - JWT token generation/validation
- `SecurityConfig.java` - Spring Security setup
- `User.java` - Database model

### 2. Web Application - React Frontend
**Location:** `/web`

Complete user interface with:
- ✅ Registration page with form validation
- ✅ Login page with email/username support
- ✅ Protected dashboard/profile page
- ✅ Logout functionality
- ✅ JWT token management
- ✅ Authentication state management (Context API)
- ✅ Responsive design with gradient UI
- ✅ Protected route component

**Key Files:**
- `Register.jsx` - Registration form page
- `Login.jsx` - Login form page
- `Dashboard.jsx` - User profile/dashboard
- `AuthContext.jsx` - Global auth state
- `ProtectedRoute.jsx` - Route protection component
- `authAPI.js` - API client with JWT headers

### 3. Documentation
- ✅ `README.md` - Project overview and setup
- ✅ `QUICK_START.md` - Step-by-step setup guide
- ✅ `IMPLEMENTATION_SUMMARY.md` - Detailed implementation details
- ✅ `TASK_CHECKLIST.md` - Task tracking with commit hashes

### 4. Repository Structure
- ✅ Organized into `/backend`, `/web`, `/mobile` (placeholder), `/docs` directories
- ✅ Root `README.md` with project information
- ✅ `.gitignore` properly configured
- ✅ All source code committed with meaningful commit messages

## What Still Needs to Be Done (Before Final Submission)

### 1. Capture Web UI Screenshots
**Required for FRS Documentation:**
- Register page screenshot
- Login page screenshot
- Dashboard/Profile page screenshot
- Logout flow (optional)

**Steps:**
1. Start MySQL server
2. Run backend: `mvn spring-boot:run` (from `/backend`)
3. Run frontend: `npm run dev` (from `/web`)
4. Open http://localhost:5173 in browser
5. Capture screenshots of each page
6. Save to `/docs` folder

### 2. Create FRS PDF
**File:** `/docs/FRS.pdf`

**Contents:**
- Title page with project information
- Entity Relationship Diagram (ERD)
- UML diagrams:
  - Class Diagram (from provided diagrams)
  - Sequence Diagram (from provided diagrams)
  - Activity Diagram (from provided diagrams)
  - Use Case Diagram (from provided diagrams)
- Web UI Screenshots:
  - Register Page
  - Login Page
  - Dashboard/Profile Page
- System Architecture Overview

**Tools to use:**
- Microsoft Word, Google Docs, or LibreOffice for PDF creation
- Paint, Snagit, or screenshot tool for UI captures
- Use provided UML diagrams as reference

### 3. Push to GitHub
**Steps:**
1. Create new public GitHub repository named: `IT342_G5_<Lastname>_Lab1`
2. Configure remote: `git remote add origin https://github.com/yourusername/IT342_G5_<Lastname>_Lab1.git`
3. Push code: `git branch -M main` then `git push -u origin main`
4. Verify all files are visible on GitHub
5. Copy repository URL for submission

### 4. MS Teams Submission
**Submit:**
1. GitHub repository link (public)
2. `/docs/FRS.pdf` file
3. Updated `TASK_CHECKLIST.md` with commit hashes
4. Brief description of implementation:
   - Technologies used
   - Features implemented
   - Any challenges/solutions
   - Testing performed

## Git Commit History

Current commits:
```
2ed79fc - Finalize task checklist with all completion details
e9feb46 - Add quick start guide for developers
b272509 - Add implementation summary documentation
957666b - Add mobile and docs directories with README files
cd8d06a - Update task checklist with commit hashes
3d71cb5 - Initial project structure with backend and web setup
```

## Directory Structure

```
IT342_G5_<Lastname>_Lab1/
├── backend/                    # Spring Boot API
│   ├── src/
│   │   └── main/java/com/aquino/userauth/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── security/
│   │       └── service/
│   └── pom.xml
├── web/                        # React Frontend
│   ├── src/
│   │   ├── api/
│   │   ├── components/
│   │   ├── context/
│   │   └── pages/
│   ├── package.json
│   └── vite.config.js
├── mobile/                     # Placeholder (empty)
├── docs/                       # Documentation
│   ├── README.md
│   └── FRS.pdf                (TO BE CREATED)
├── README.md
├── QUICK_START.md
├── IMPLEMENTATION_SUMMARY.md
├── TASK_CHECKLIST.md
└── .git/                       # Git repository
```

## Testing Checklist

Before final submission, verify:
- [ ] Backend starts without errors: `mvn spring-boot:run`
- [ ] Frontend starts without errors: `npm run dev`
- [ ] Can navigate to http://localhost:5173
- [ ] Can register new user
- [ ] Can login with registered credentials
- [ ] Can view profile on dashboard
- [ ] Can logout successfully
- [ ] JWT tokens are properly generated
- [ ] Passwords are encrypted (check DB)
- [ ] CORS works without errors
- [ ] Protected routes redirect to login when not authenticated

## Important Notes

### Security Considerations:
- ✅ Passwords are never stored in plain text (BCrypt encryption)
- ✅ JWT tokens are stored in browser localStorage
- ✅ Tokens are sent in Authorization header for protected endpoints
- ✅ Token expiration is set to 60 minutes
- ✅ CORS is restricted to frontend origin

### Development Notes:
- API base URL: `http://localhost:8080/api`
- Frontend URL: `http://localhost:5173`
- Database: MySQL on localhost:3306
- Default database: `userauth`

### Common Issues & Solutions:

**Issue:** Backend won't start
- Solution: Ensure MySQL is running and database exists

**Issue:** Frontend can't reach backend
- Solution: Check CORS configuration and ensure backend is running

**Issue:** Port 8080 already in use
- Solution: Kill process or change port in `server.port` property

**Issue:** Node modules not installing
- Solution: Delete `node_modules` and `package-lock.json`, then `npm install`

## Contact & Support

Refer to documentation files for detailed information:
- Setup: See `QUICK_START.md`
- Implementation details: See `IMPLEMENTATION_SUMMARY.md`
- General info: See `README.md`

## Final Checklist Before Submission

- [ ] All backend code implemented and compiles
- [ ] All frontend code implemented and runs
- [ ] Git repository initialized with commits
- [ ] Web UI screenshots captured
- [ ] FRS PDF created with diagrams and screenshots
- [ ] GitHub repository created and code pushed
- [ ] TASK_CHECKLIST.md updated with commit hashes
- [ ] All documentation reviewed
- [ ] MS Teams submission ready with all required files

## Success Criteria Met

✅ Backend with Spring Boot
- POST /api/auth/register
- POST /api/auth/login
- GET /api/user/me (protected)
- MySQL connection
- BCrypt password encryption

✅ Web Application with React
- Register page
- Login page
- Dashboard/Profile page (protected)
- Logout functionality

✅ Documentation
- FRS with ERD and UML diagrams
- Web UI screenshots
- README and setup guides

✅ Repository
- Organized structure (/web, /backend, /mobile, /docs)
- Git commits with hashes
- Ready for GitHub push

## Ready for Submission!

The core implementation is complete. Follow the "What Still Needs to Be Done" section above to finalize the submission.
