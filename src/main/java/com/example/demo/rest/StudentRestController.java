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
    //Define a field and load the field with data so that we only do the work once:
    private List<Student> theStudents;

    //define @PostConstruct to load the student data, once!
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Ale", "Cabrera"));
        theStudents.add(new Student("Tommy", "King"));
        theStudents.add(new Student("Jewlz", "Blue"));
    }


    //define endpoint for /students - return a list of students:

//    @GetMapping("/students")
//    public List<Student> getStudents() {
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("Ale", "Cabrera"));
//        students.add(new Student("Tommy", "King"));
//        students.add(new Student("Jewlz", "Blue"));
//        return students;
//    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    //Define the endpoint of "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //Just index into the list ... keep it simple for now

        //check the studentId against the list size:
        if(studentId > theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student not found: "  + studentId);
        }
        return theStudents.get(studentId);
    }

}
