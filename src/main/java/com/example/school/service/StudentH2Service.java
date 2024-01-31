/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;

import java.util.*;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getAllDetails() {
        List<Student> list = db.query("Select * from Student", new StudentRowMapper());
        ArrayList<Student> Arr = new ArrayList<>(list);
        return Arr;
    }

    @Override
    public Student addStudent(Student student) {
        db.update("insert into Student(studentName,gender,standard) values(?,?,?)", student.getStudentName(),
                student.getGender(), student.getStandard());
        Student s = db.queryForObject("Select * from Student where studentName=? and gender=? and standard=?",
                new StudentRowMapper(),
                student.getStudentName(), student.getGender(), student.getStandard());
        return s;
    }

    @Override
    public String addStudentBulk(ArrayList<Student> studentList) {
        for (Student i : studentList) {
            db.update("insert into Student(studentName,gender,standard) values(?,?,?)", i.getStudentName(),
                    i.getGender(), i.getStandard());
        }
        String response = String.format("Successfullty added %d students", studentList.size());
        return response;
    }

    @Override
    public Student getById(int studentId) {
        try {
            Student s = db.queryForObject("Select * from Student where studentId=?", new StudentRowMapper(), studentId);
            return s;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student updateMethod(int studentId, Student student) {
        if (student.getStudentName() != null) {
            db.update("update Student set studentName=? where studentId=?", student.getStudentName(), studentId);
        }
        if (student.getGender() != null) {
            db.update("update Student set Gender=? where studentId=?", student.getGender(), studentId);
        }
        if (student.getStandard() != 0) {
            db.update("update Student set Standard=? where studentId=?", student.getStandard(), studentId);
        }
        return getById(studentId);
    }

    public void remove(int studentId) {
        db.update("delete from Student where studentId=?", studentId);
    }

}