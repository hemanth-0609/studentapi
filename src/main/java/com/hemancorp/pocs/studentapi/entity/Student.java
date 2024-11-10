package com.hemancorp.pocs.studentapi.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Student. Maps to the "Students" table in the database.
 */
@Entity
@Table(name = "Students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    private String studentId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String doeStudentId;
    private String source;
    private String fileName;
    private String householdMemberId;
    private String dhsClientId;

}
