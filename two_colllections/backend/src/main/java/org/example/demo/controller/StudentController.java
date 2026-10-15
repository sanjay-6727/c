package org.example.demo.controller;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.model.Student;
import org.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/students") @CrossOrigin(origins="http://localhost:5173") @RequiredArgsConstructor
public class StudentController {
 private final StudentService service;
 @GetMapping public List<Student> all(){ return service.all(); }
 @GetMapping("/{id}") public Student one(@PathVariable String id){ return service.one(id); }
 @GetMapping("/by-department/{departmentId}") public List<Student> byDepartment(@PathVariable String departmentId){ return service.byDepartment(departmentId); }
 @PostMapping public Student create(@RequestBody Student s){ return service.create(s); }
 @PutMapping("/{id}") public Student update(@PathVariable String id,@RequestBody Student s){ return service.update(id,s); }
 @DeleteMapping("/{id}") public void delete(@PathVariable String id){ service.delete(id); }
}
