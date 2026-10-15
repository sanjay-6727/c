# Spring Boot + Vue 3 + MongoDB + Lombok
## Beginner CRUD project with Router, Pinia, Props, Emits and detailed TXT references

This is a small working Student Management CRUD application. It also contains detailed, commented `.txt`
reference templates explaining the main ideas and how to adapt them for other lab questions.

## Requirements
- Java 17
- IntelliJ IDEA
- Lombok plugin/annotation processing enabled in IntelliJ if the IDE does not recognise Lombok
- MongoDB running at `mongodb://localhost:27017`
- Node.js and npm

## Run the backend
1. Open the `backend` folder (or the project root and then select backend as a Maven project) in IntelliJ.
2. Wait for Maven to download dependencies.
3. Run `backend/src/main/java/com/example/student/StudentApplication.java`.
4. API: `http://localhost:8080/students`.

## Run the frontend
Open a terminal in `frontend/` and run:
```bash
npm install
npm run dev
```
Open the Vite URL shown in the terminal (usually `http://localhost:5173`).

## What each part does
- `Student.java`: data model; Lombok generates getters/setters/constructor methods.
- `StudentRepository.java`: MongoDB operations provided by Spring Data.
- `StudentController.java`: HTTP endpoints for CRUD.
- `studentApi.js`: frontend HTTP requests.
- `studentStore.js`: Pinia shared state and actions.
- `router/index.js`: URL-to-page mapping.
- `StudentForm.vue`: child component; receives props and emits events.
- `StudentsView.vue`: parent page; listens for emitted events and uses Pinia.
- `reference_templates/`: 14 detailed `.txt` explanations/examples.

## Important Lombok note
The project includes Lombok in Maven. IntelliJ may need the Lombok plugin and annotation processing:
Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing.
Then reload Maven and rebuild. Lombok is a compile-time tool: it generates boilerplate methods while compiling.

## Architecture
Vue page -> Pinia store -> API service -> Spring Boot controller -> MongoRepository -> MongoDB.

## Adapt to another question
For Books, Employees, Products, etc.:
1. Rename `Student` classes and the API route consistently.
2. Change fields and `@Document(collection = "...")` in the model.
3. Update the child form inputs and list columns.
4. Update the TXT reference notes to match your chosen entity.
5. Keep the same Router, Pinia, Props and Emits pattern.
