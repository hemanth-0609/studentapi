package com.hemancorp.pocs.studentapi.repository;

import com.hemancorp.pocs.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Student entity.
 * Provides CRUD operations for interacting with the Students table.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
