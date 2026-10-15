package org.example.demo.repository;
import java.util.List;
import org.example.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface StudentRepository extends MongoRepository<Student, String> {
 List<Student> findByDepartmentId(String departmentId);
}
