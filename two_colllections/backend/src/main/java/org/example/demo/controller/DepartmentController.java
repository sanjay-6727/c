package org.example.demo.controller;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.model.Department;
import org.example.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/departments") @CrossOrigin(origins="http://localhost:5173") @RequiredArgsConstructor
public class DepartmentController {
 private final DepartmentService service;
 @GetMapping public List<Department> all(){ return service.all(); }
 @GetMapping("/{id}") public Department one(@PathVariable String id){ return service.one(id); }
 @PostMapping public Department create(@RequestBody Department d){ return service.create(d); }
 @PutMapping("/{id}") public Department update(@PathVariable String id,@RequestBody Department d){ return service.update(id,d); }
 @DeleteMapping("/{id}") public void delete(@PathVariable String id){ service.delete(id); }
}
