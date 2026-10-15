package com.example.demo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document("leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    @Id
    private String id;
    private String employeeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status;
}
