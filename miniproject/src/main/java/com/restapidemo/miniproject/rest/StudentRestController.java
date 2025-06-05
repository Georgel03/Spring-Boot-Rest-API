package com.restapidemo.miniproject.rest;

import com.restapidemo.miniproject.entity.Student;
import com.restapidemo.miniproject.entity.StudentNotFoundException;
import com.restapidemo.miniproject.errorhandling.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    //define @PostConstruct to load the student data only once!


    @PostConstruct
    public void populateList() {
        students =  new ArrayList<>();
        students.add(new Student("Stance", "George"));
        students.add(new Student("Stance", "Amalia"));
        students.add(new Student("Stance", "Camelia"));
        students.add(new Student("Stance", "Mircea"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student with id: " + studentId + " not found!");
        }
        return students.get(studentId);
    }




}
