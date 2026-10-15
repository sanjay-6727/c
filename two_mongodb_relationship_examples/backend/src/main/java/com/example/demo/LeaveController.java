package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:5173")
public class LeaveController {
    @Autowired private LeaveRepository repo;
    @Autowired private EmployeeRepository employeeRepo;

    @GetMapping
    public List<Leave> all() {
        return repo.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public List<Leave> byEmployee(@PathVariable String employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    @PostMapping
    public Leave apply(@RequestBody Leave leave) {
        validate(leave);
        if (!employeeRepo.existsById(leave.getEmployeeId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        leave.setStatus("PENDING");
        return repo.save(leave);
    }

    @PutMapping("/{id}")
    public Leave update(@PathVariable String id, @RequestBody Leave leave) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Leave not found");
        }
        validate(leave);
        if (!employeeRepo.existsById(leave.getEmployeeId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        leave.setId(id);
        return repo.save(leave);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.deleteById(id);
    }

    private void validate(Leave leave) {
        if (leave.getEmployeeId() == null || leave.getEmployeeId().isBlank()
                || leave.getFromDate() == null || leave.getToDate() == null
                || leave.getToDate().isBefore(leave.getFromDate())
                || leave.getReason() == null || leave.getReason().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Employee, valid dates, and reason are required");
        }
    }
}
