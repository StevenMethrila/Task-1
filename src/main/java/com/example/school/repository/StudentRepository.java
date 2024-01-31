// Write your code here
package com.example.school.repository;

import com.example.school.model.Student;
import java.util.ArrayList;

public interface StudentRepository {

    public ArrayList<Student> getAllDetails();

    public Student addStudent(Student student);

    public String addStudentBulk(ArrayList<Student> studentList);

    public Student getById(int studentId);

    public Student updateMethod(int studentId, Student student);

    public void remove(int studentId);
}