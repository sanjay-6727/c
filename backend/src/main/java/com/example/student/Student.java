package com.example.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document maps this Java class to the MongoDB "students" collection.
// @Data generates getters, setters, toString, equals and hashCode.
// @NoArgsConstructor generates Student().
// @AllArgsConstructor generates a constructor with every field.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {
    @Id
    private String id; // MongoDB's unique document ID
    private String name;
    private String department;
    private int year;
}
