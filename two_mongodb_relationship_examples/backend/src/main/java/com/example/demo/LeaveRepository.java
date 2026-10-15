package com.example.demo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaveRepository extends MongoRepository<Leave, String> {
    List<Leave> findByEmployeeId(String employeeId);
    List<Leave> findByStatus(String status);
}
