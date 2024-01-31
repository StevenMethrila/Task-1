/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;
import java.util.*;

@RestController
public class StudentController {
    @Autowired
    private StudentH2Service shs;

    @GetMapping("/students")
    public ArrayList<Student> getMethod() {
        return shs.getAllDetails();
    }

    @PostMapping("/students")
    public Student postMethod(@RequestBody Student student) {
        return shs.addStudent(student);
    }

    @PostMapping("/students/bulk")
    public String postMethodBulk(@RequestBody ArrayList<Student> studentList) {
        return shs.addStudentBulk(studentList);
    }

    @GetMapping("/students/{studentId}")
    public Student getMethodById(@PathVariable("studentId") int studentId) {
        return shs.getById(studentId);
    }

    @PutMapping("/students/{studentId}")
    public Student putMethod(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        return shs.updateMethod(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteMethod(@PathVariable("studentId") int studentId){
        shs.remove(studentId);
    }

}
