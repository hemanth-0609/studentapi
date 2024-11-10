package com.hemancorp.pocs.studentapi.controller;

import com.hemancorp.pocs.studentapi.entity.Student;
import com.hemancorp.pocs.studentapi.exception.StudentNotFoundException;
import com.hemancorp.pocs.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling HTTP requests related to Student resources.
 * Exposes endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * GET endpoint to retrieve all students in the database.
     * @return List of all student entities.
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    /**
     * GET endpoint to retrieve a specific student by ID.
     * @param id The ID of the student to retrieve.
     * @return ResponseEntity containing the student if found, or 404 Not Found if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return ResponseEntity.ok(student);
    }

    /**
     * POST endpoint to create a new student record.
     * @param student The student entity to create.
     * @return The created student entity.
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    /**
     * PUT endpoint to update an existing student by ID.
     * @param id The ID of the student to update.
     * @param studentDetails The new student details to update with.
     * @return ResponseEntity containing the updated student if found, or 404 Not Found if not.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student studentDetails) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setMiddleName(studentDetails.getMiddleName());
        student.setDoeStudentId(studentDetails.getDoeStudentId());
        student.setSource(studentDetails.getSource());
        student.setFileName(studentDetails.getFileName());
        student.setHouseholdMemberId(studentDetails.getHouseholdMemberId());
        student.setDhsClientId(studentDetails.getDhsClientId());
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    /**
     * DELETE endpoint to remove a student by ID.
     * @param id The ID of the student to delete.
     * @return ResponseEntity with no content if deleted, or 404 Not Found if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
