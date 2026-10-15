package com.example.student;

import org.springframework.data.mongodb.repository.MongoRepository;

// MongoRepository already provides save, findAll, findById, deleteById, etc.
public interface StudentRepository extends MongoRepository<Student, String> {
}
