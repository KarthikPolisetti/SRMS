# EduMetrics -- RBAC Analytics SaaS (Student Result Management System (SRMS))

A full-stack web application designed to efficiently manage, track, and publish student academic performance. Built using a robust Spring Boot backend and a modern responsive frontend, this application provides role-based features for administrators to manage student records and for students to seamlessly access their results.

## 🚀 Key Features

- **Admin Dashboard:** Full CRUD operations to manage student info, courses, grades, and semesters.
- **Student Portal:** Secure portal for students to search and retrieve their individual result cards.
- **Modular Monorepo Structure:** Clean separation between the business-logic backend and user-interface frontend.
- **RESTful Architecture:** Clear decoupled interaction handling all data transactions through JSON API payloads.

---

## 🛠️ Tech Stack

- **Backend:** Java 17+, Spring Boot, Spring Data JPA, Maven
- **Database:** H2 Database (In-Memory for development) / MySQL
- **Frontend:** HTML5, CSS3, JavaScript (or specify React / Angular if applicable)
- **IDEs Supported:** IntelliJ IDEA, VS Code, Eclipse STS

---

## 💻 Local Setup & Installation

To run this project on your machine, follow these steps sequentially.

### Prerequisites
Make sure you have the following installed:
- **Java Development Kit (JDK 17 or higher)**
- **Node.js** (If your frontend relies on npm/yarn dependencies)
- **Maven** (Optional: Bundled wrapper `.mvnw` is included in the project)

---

### Step 1: Set Up the Backend (Spring Boot)

1. Navigate to the backend directory:
```bash
   cd backend-folder-name
Build and run the Spring Boot application using the Maven wrapper:

Bash
   # On Windows (Command Prompt or PowerShell)
   mvnw.cmd spring-boot:run

   # On macOS / Linux
   ./mvnw spring-boot:run
The server will spin up and become available at http://localhost:8080.

### Step 2: Set Up the Frontend
Open a separate terminal window and navigate to the frontend directory:

Bash
   cd frontend-folder-name
Launch your frontend setup:

If standard Static HTML/JS: Simply serve the files locally or open the primary entrance page (e.g., index.html) using a local live server extension.

If using Node.js Framework (React/Vue/Angular): Install dependencies and launch the dev server:

Bash
     npm install
     npm run dev # or 'npm start'
     ```
3. Open `http://localhost:3000` (or the respective frontend dev URL) in your browser.

---

## 🐳 Future Deployment Plans

This project is configured to transition smoothly into production environments:
- **Dockerization:** Dockerfiles are being introduced to containerize both services independently.
- **Environment Agnostic:** API endpoints default to `http://localhost:8080` for local execution but will leverage global environment parameters (`API_BASE_URL`) when shifting to the cloud.

---

## 👥 Contributors

- **Karthik Polisetti** - *Initial Work & Architecture*
