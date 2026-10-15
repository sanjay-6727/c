# Spring Boot + Vue 3 + MongoDB — Complete Beginner Reference

This is a small working Student CRUD project plus separate `.txt` reference templates.
It includes Vue Router, Pinia, props, emits, and comments/examples for learning.

## Requirements
- Java 17
- IntelliJ IDEA
- MongoDB running locally at `mongodb://localhost:27017`
- Node.js + npm

## Run the backend
1. Open this folder in IntelliJ IDEA.
2. Wait for Maven to load `pom.xml`.
3. Run `backend/src/main/java/com/example/student/StudentApplication.java`.
4. Backend API is `http://localhost:8080/students`.

## Run the frontend
In a terminal:
```bash
cd frontend
npm install
npm run dev
```
Open the Vite URL shown in the terminal (usually `http://localhost:5173`).

## What to practise
- CRUD: Students page.
- Router: Students and About pages, navigation links.
- Pinia: `src/stores/studentStore.js` owns student state and CRUD calls.
- Props: parent sends a student to `StudentForm.vue`.
- Emits: `StudentForm.vue` emits `save` and `cancel` events to the parent.
- API service: `src/services/studentApi.js` contains fetch calls.
- TXT references: see `reference_templates/`. Each file is a commented copy/example for quick revision.

## Important architecture
Vue page/component -> Pinia store -> API service -> Spring Boot controller -> MongoRepository -> MongoDB.

## Adapt to another lab question
For Books, Employees, Products, etc.:
1. Rename Student in Java classes and API paths.
2. Change fields in `backend/.../Student.java`.
3. Update the form and table in `frontend/src/components/StudentForm.vue` and `frontend/src/views/StudentsView.vue`.
4. Update `reference_templates/` examples as notes for your new entity.
