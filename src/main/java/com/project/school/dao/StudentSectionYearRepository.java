package com.project.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.entity.Section;
import com.project.school.entity.Student;
import com.project.school.entity.StudentSectionYear;
import com.project.school.entity.Year;

public interface StudentSectionYearRepository extends JpaRepository<StudentSectionYear, Integer> {

	List<StudentSectionYear> findBySectionAndYear(Section section, Year year);

	List<StudentSectionYear> findByStudent(Student student);

	List<StudentSectionYear> findByYear(Year year);

	StudentSectionYear findByStudentAndYear(Student student, Year year);
}
