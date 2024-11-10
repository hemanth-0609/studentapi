package com.hemancorp.pocs.studentapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;

import com.hemancorp.pocs.studentapi.entity.Student;
import com.hemancorp.pocs.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for StudentService class.
 * Uses Mockito to mock the StudentRepository and test service methods.
 */
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository; // Mocked StudentRepository

    @InjectMocks
    private StudentService studentService; // Service under test

    private Student student;

    /**
     * Set up method to initialize the mock environment before each test.
     * Creates a sample Student object for testing.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        student = new Student("S123", "John", "Doe", "M", "DOE123", "School", "file123", "HM123", "DHS123456789012345");
    }

    /**
     * Test case for getAllStudents method in StudentService.
     * Verifies that it returns a list of students and interacts correctly with the repository.
     */
    @Test
    public void testGetAllStudents() {
        when(studentRepository.findAll()).thenReturn(List.of(student)); // Mock repository response
        List<Student> students = studentService.getAllStudents();
        
        assertEquals(1, students.size()); // Check that one student is returned
        verify(studentRepository, times(1)).findAll(); // Verify that findAll() is called once
    }

    /**
     * Test case for getStudentById method in StudentService.
     * Verifies that it returns the correct student and interacts correctly with the repository.
     */
    @Test
    public void testGetStudentById() {
        when(studentRepository.findById("S123")).thenReturn(Optional.of(student)); // Mock repository response
        Optional<Student> foundStudent = studentService.getStudentById("S123");
        
        assertTrue(foundStudent.isPresent()); // Ensure the student is found
        assertEquals("John", foundStudent.get().getFirstName()); // Check the first name
        verify(studentRepository, times(1)).findById("S123"); // Verify that findById() is called once
    }

    /**
     * Test case for saveStudent method in StudentService.
     * Verifies that it saves the student correctly and interacts with the repository.
     */
    @Test
    public void testSaveStudent() {
        when(studentRepository.save(student)).thenReturn(student); // Mock repository save response
        Student savedStudent = studentService.saveStudent(student);
        
        assertNotNull(savedStudent); // Check that the saved student is not null
        assertEquals("S123", savedStudent.getStudentId()); // Check the student ID
        verify(studentRepository, times(1)).save(student); // Verify that save() is called once
    }

    /**
     * Test case for deleteStudent method in StudentService.
     * Verifies that it deletes the student correctly by calling deleteById.
     */
    @Test
    public void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById("S123"); // Mock repository delete behavior
        studentService.deleteStudent("S123");
        
        verify(studentRepository, times(1)).deleteById("S123"); // Verify that deleteById() is called once
    }
}
