# Spring Boot + MongoDB + Vue 3: Two Collections

Example: `departments` and `students`. Each student stores a `departmentId`.
Backend uses Java 17, Spring Boot, MongoRepository and Lombok. Frontend uses Vue 3, Axios, Vue Router and Pinia.

## Run on Windows
1. Start your local MongoDB server.
2. In PowerShell, open `backend` and run `.\mvnw.cmd spring-boot:run` if the Maven Wrapper is present, or `mvn spring-boot:run` if Maven is installed.
3. Open another PowerShell in `frontend`, run `npm install`, then `npm run dev`.
4. Visit the URL printed by Vite, usually http://localhost:5173.

MongoDB URI: `mongodb://localhost:27017/two_collections_db`
Backend: `http://localhost:8080`

If you don't have Maven Wrapper files, use installed Maven (`mvn spring-boot:run`) or generate the wrapper with `mvn wrapper:wrapper`.

## APIs
Departments: GET/POST `/api/departments`, GET/PUT/DELETE `/api/departments/{id}`
Students: GET/POST `/api/students`, GET/PUT/DELETE `/api/students/{id}`, GET `/api/students/by-department/{departmentId}`

Create a department first, then add students and select that department.
