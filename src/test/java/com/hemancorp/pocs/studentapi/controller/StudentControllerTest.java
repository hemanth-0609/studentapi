package com.hemancorp.pocs.studentapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemancorp.pocs.studentapi.entity.Student;
import com.hemancorp.pocs.studentapi.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for StudentController class.
 * Uses MockMvc to simulate HTTP requests and test API endpoints.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc for simulating HTTP requests

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper for converting objects to JSON

    @Autowired
    private StudentRepository studentRepository; // Actual repository for data persistence

    private Student student;

    /**
     * Set up method to initialize data and clean the database before each test.
     * Creates a sample Student object for testing.
     */
    @BeforeEach
    public void setup() {
        studentRepository.deleteAll(); // Clear the database before each test
        student = new Student("S123", "John", "Doe", "M", "DOE123", "School", "file123", "HM123", "DHS123456789012345");
    }

    /**
     * Test case for creating a student (POST /api/students).
     * Verifies that a student is created and returned with a 201 Created status.
     */
    @Test
    public void testCreateStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated()) // Expect 201 Created status
                .andExpect(jsonPath("$.studentId", is("S123")))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    /**
     * Test case for retrieving all students (GET /api/students).
     * Verifies that it returns the correct list of students.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        studentRepository.save(student); // Save the student to the database

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect 200 OK status
                .andExpect(jsonPath("$.size()", is(1))) // Expect one student in the list
                .andExpect(jsonPath("$[0].studentId", is("S123")));
    }

    /**
     * Test case for retrieving a student by ID (GET /api/students/{id}).
     * Verifies that it returns the correct student.
     */
    @Test
    public void testGetStudentById() throws Exception {
        studentRepository.save(student); // Save the student to the database

        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/S123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Expect 200 OK status
                .andExpect(jsonPath("$.studentId", is("S123")))
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    /**
     * Test case for updating a student (PUT /api/students/{id}).
     * Verifies that the student is updated with new details.
     */
    @Test
    public void testUpdateStudent() throws Exception {
        studentRepository.save(student); // Save the original student

        Student updatedStudent = new Student("S123", "Jane", "Smith", "A", "DOE456", "SchoolUpdated", "file456", "HM456", "DHS654321098765432");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/students/S123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk()) // Expect 200 OK status
                .andExpect(jsonPath("$.firstName", is("Jane"))) // Check updated first name
                .andExpect(jsonPath("$.lastName", is("Smith"))); // Check updated last name
    }

    /**
     * Test case for deleting a student (DELETE /api/students/{id}).
     * Verifies that the student is deleted and is no longer retrievable.
     */
    @Test
    public void testDeleteStudent() throws Exception {
        studentRepository.save(student); // Save the student to the database

        // Delete the student
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/S123"))
                .andExpect(status().isNoContent()); // Expect 204 No Content status

        // Verify that the student no longer exists
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/S123"))
                .andExpect(status().isNotFound()); // Expect 404 Not Found status
    }

    /**
     * Test case for retrieving a non-existent student (GET /api/students/{id}).
     * Verifies that it returns a 404 Not Found status with an error message.
     */
    @Test
    public void testGetStudentNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/nonexistentId"))
                .andExpect(status().isNotFound()) // Expect 404 Not Found status
                .andExpect(jsonPath("$.error", is("Student with ID nonexistentId not found.")));
    }
}
