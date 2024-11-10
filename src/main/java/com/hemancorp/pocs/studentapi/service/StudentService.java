package com.hemancorp.pocs.studentapi.service;

import com.hemancorp.pocs.studentapi.entity.Student;
import com.hemancorp.pocs.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for business logic and operations on Student entities.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Retrieves a list of all students in the database.
     * @return List of all students.
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves a student by their unique ID.
     * @param studentId The ID of the student to retrieve.
     * @return Optional containing the student if found, or empty if not found.
     */
    public Optional<Student> getStudentById(String studentId) {
        return studentRepository.findById(studentId);
    }

    /**
     * Saves a new student or updates an existing student in the database.
     * @param student The student entity to save or update.
     * @return The saved or updated student entity.
     */
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Deletes a student by their unique ID.
     * @param studentId The ID of the student to delete.
     */
    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }
}
