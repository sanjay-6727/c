package org.example.demo.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection="departments")
public class Department {
 @Id private String id;
 private String name;
 private String code;
}
