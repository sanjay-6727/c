package org.example.demo.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.model.Department;
import org.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
@Service 
@RequiredArgsConstructor
public class DepartmentService {
 private final DepartmentRepository repo;
 public List<Department> all(){ return repo.findAll(); }
 public Department one(String id){ return repo.findById(id).orElseThrow(() -> new RuntimeException("Department not found")); }
 public Department create(Department d){ d.setId(null); return repo.save(d); }
 public Department update(String id, Department d){ Department old=one(id); old.setName(d.getName()); old.setCode(d.getCode()); return repo.save(old); }
 public void delete(String id){ repo.deleteById(id); }
}
