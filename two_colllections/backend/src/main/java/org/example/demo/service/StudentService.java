package org.example.demo.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.model.Student;
import org.example.demo.repository.StudentRepository;
import org.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class StudentService {
 private final StudentRepository repo;
 private final DepartmentRepository departmentRepo;
 public List<Student> all(){ return repo.findAll(); }
 public Student one(String id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found")); }
 public List<Student> byDepartment(String id){ return repo.findByDepartmentId(id); }
 public Student create(Student s){ s.setId(null); checkDepartment(s.getDepartmentId()); return repo.save(s); }
 public Student update(String id, Student s){ Student old=one(id); checkDepartment(s.getDepartmentId()); old.setName(s.getName()); old.setEmail(s.getEmail()); old.setYear(s.getYear()); old.setDepartmentId(s.getDepartmentId()); return repo.save(old); }
 public void delete(String id){ repo.deleteById(id); }
 private void checkDepartment(String id){ if(id==null || !departmentRepo.existsById(id)) throw new IllegalArgumentException("Choose an existing department"); }
}
