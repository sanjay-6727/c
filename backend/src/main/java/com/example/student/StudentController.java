package com.example.student;

import org.springframework.web.bind.annotation.*;
import java.util.List;

// REST controller: receives HTTP requests from Vue.
@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // READ all: GET http://localhost:8080/students
    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    // CREATE: POST /students with JSON request body
    @PostMapping
    public Student create(@RequestBody Student student) {
        return repository.save(student);
    }

    // UPDATE: PUT /students/{id}
    // This beginner example saves the supplied object with the URL ID.
    @PutMapping("/{id}")
    public Student update(@PathVariable String id, @RequestBody Student student) {
        student.setId(id);
        return repository.save(student);
    }

    // DELETE: DELETE /students/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}
