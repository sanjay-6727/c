package org.example.demo.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection="students")
public class Student {
 @Id private String id;
 private String name;
 private String email;
 private Integer year;
 // Stores the MongoDB ID from the departments collection.
 private String departmentId;
}
