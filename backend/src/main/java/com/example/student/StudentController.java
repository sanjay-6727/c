package com.example.student;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController returns Java objects as JSON responses.
// @RequestMapping sets the common URL prefix.
// @RequiredArgsConstructor generates a constructor for final fields,
// allowing Spring to inject StudentRepository.
@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentController {
    private final StudentRepository repository;

    // READ all: GET http://localhost:8080/students
    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    // CREATE: POST /students with a JSON Student in the request body.
    @PostMapping
    public Student create(@RequestBody Student student) {
        return repository.save(student);
    }

    // UPDATE: PUT /students/{id}. This simple example saves the supplied data.
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
