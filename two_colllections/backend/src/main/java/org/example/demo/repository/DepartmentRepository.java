package org.example.demo.repository;
import org.example.demo.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface DepartmentRepository extends MongoRepository<Department, String> {}
