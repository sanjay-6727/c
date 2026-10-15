# Two MongoDB Relationship Examples (Spring Boot + Vue)

Contains two independent backend examples:
1. Hotel Management: Rooms and Bookings
2. Employee Leave Management: Employees and Leaves, including frontend filters by leave reason and status.

## Backend setup
Create a Spring Boot project with:
- Spring Web
- Spring Data MongoDB
- Lombok

Use Java 17+ and Maven. Copy the relevant Java files into your project's source tree, keeping package names consistent. The examples use package `com.example.demo`.

In `src/main/resources/application.properties`:
```properties
spring.application.name=relationship-examples
spring.data.mongodb.uri=mongodb://localhost:27017/relationship_examples
server.port=8080
```

The Java examples below are grouped by domain. If using both in one Spring Boot app, they can share the same package and MongoDB database.

## Run
```bash
mvn spring-boot:run
```

## Employee leave frontend
The `frontend/` folder is a minimal Vue 3 + Vite app. It lists employees and leaves, and filters leave records by employee, reason, and status.

```bash
cd frontend
npm install
npm run dev
```

Make sure the Spring Boot API is running on `http://localhost:8080`.

## API examples
### Rooms
- GET `/api/rooms`
- POST `/api/rooms`
- PUT `/api/rooms/{id}`
- DELETE `/api/rooms/{id}`

### Bookings
- GET `/api/bookings`
- GET `/api/bookings/room/{roomId}`
- POST `/api/bookings`
- PUT `/api/bookings/{id}`
- DELETE `/api/bookings/{id}`

### Employees
- GET `/api/employees`
- GET `/api/employees/{id}`
- POST `/api/employees`
- PUT `/api/employees/{id}`
- DELETE `/api/employees/{id}`

### Leaves
- GET `/api/leaves`
- GET `/api/leaves/employee/{employeeId}`
- POST `/api/leaves`
- PUT `/api/leaves/{id}`
- DELETE `/api/leaves/{id}`

## Relationship design
- `Booking.roomId` stores the ID of a room document.
- `Leave.employeeId` stores the ID of an employee document.
- `Leave.reason` is a string and can be filtered on the frontend without a special backend query.

## Notes
This is a practice CRUD template, not production-ready. Before production, add validation, authentication, overlap checks for bookings, and leave approval logic that updates leave balances.
