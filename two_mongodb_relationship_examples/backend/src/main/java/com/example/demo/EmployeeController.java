package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    @Autowired private EmployeeRepository repo;

    @GetMapping
    public List<Employee> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable String id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        if (employee.getTotalLeaves() < 0 || employee.getLeavesTaken() < 0
                || employee.getLeavesTaken() > employee.getTotalLeaves()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid leave balance");
        }
        return repo.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        if (employee.getTotalLeaves() < 0 || employee.getLeavesTaken() < 0
                || employee.getLeavesTaken() > employee.getTotalLeaves()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid leave balance");
        }
        employee.setId(id);
        return repo.save(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }
}
