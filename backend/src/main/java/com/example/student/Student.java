package com.example.student;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document tells MongoDB which collection stores this object.
@Document(collection = "students")
public class Student {
    @Id
    private String id; // MongoDB document ID

    private String name;
    private String department;
    private int year;

    public Student() {} // Needed for JSON/object conversion.

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}
