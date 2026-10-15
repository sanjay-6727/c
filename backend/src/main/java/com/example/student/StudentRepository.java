package com.example.student;

import org.springframework.data.mongodb.repository.MongoRepository;

// Spring Data generates the implementation automatically.
// The second generic type is String because Student.id is a String.
public interface StudentRepository extends MongoRepository<Student, String> {
}
