package com.project.school.service;

import java.util.List;

import com.project.school.entity.Student;
import com.project.school.entity.StudentSectionYear;
import com.project.school.entity.Year;

public interface StudentSectionYearService {

	public List<StudentSectionYear> findAll();

	public void save(StudentSectionYear theStudentSectionYear);

	public void deleteById(int id);

	public StudentSectionYear findById(int id);

	public List<StudentSectionYear> findByStandardAndYear(String standard, String year);

	public List<StudentSectionYear> findByStudent(Student student);

	public StudentSectionYear findByStudentAndYear(Student student, Year year);

	
}
