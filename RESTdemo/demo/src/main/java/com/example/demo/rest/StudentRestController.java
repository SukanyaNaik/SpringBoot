package com.example.demo.rest;

import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> students;

    @PostConstruct
    public void populateStudents() {
        students = new ArrayList<Student>();
        students.add(new Student(1, "John", "Doe"));
        students.add(new Student(2, "Paul", "Smith"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Object getStudent(@PathVariable int studentId) {
        for(Student s : students) {
            if(s.getId() == studentId) {
                return s;
            }
        }

        throw new StudentNotFoundException("Student not found!");
        //return "Student not found!";
    }
    @PostMapping()
    public void addStudent() {

    }
}
