package com.project.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	Student findByAadhaarId(String aadhaarId);
}
