package com.project.school.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.school.dao.SectionRepository;
import com.project.school.dao.StudentSectionYearRepository;
import com.project.school.dao.YearRepository;

import com.project.school.entity.Section;
import com.project.school.entity.Student;
import com.project.school.entity.StudentSectionYear;
import com.project.school.entity.Year;

@Service
public class StudentSectionYearImpl implements StudentSectionYearService {

	@Autowired
	StudentSectionYearRepository studentSectionYearRepository;

	@Autowired
	SectionRepository sectionRepository;

	@Autowired
	YearRepository yearRepository;

	@Override
	public List<StudentSectionYear> findAll() {

		return studentSectionYearRepository.findAll();
	}

	@Override
	public StudentSectionYear findByStudentAndYear(Student student, Year year) {

		return studentSectionYearRepository.findByStudentAndYear(student, year);
	}

	@Override
	public List<StudentSectionYear> findByStudent(Student student) {

		return studentSectionYearRepository.findByStudent(student);
	}


	@Override
	public void deleteById(int id) {
		
		studentSectionYearRepository.deleteById(id);
	}
	
	@Override
	public List<StudentSectionYear> findByStandardAndYear(String standard, String tempYear) {

		Section section = sectionRepository.findByStandard(standard);
		Year year = yearRepository.findByYear(tempYear);
	
		return studentSectionYearRepository.findBySectionAndYear(section, year);
	}

	@Override
	public StudentSectionYear findById(int id) {
		Optional<StudentSectionYear> result = studentSectionYearRepository.findById(id);

		StudentSectionYear theStudentSectionYear = null;
		if (result.isPresent()) {
			theStudentSectionYear = result.get();
			return theStudentSectionYear;
		}
		return theStudentSectionYear;
	}

	@Override
	public void save(StudentSectionYear theStudentSectionYear) {
 
		/*Here Year and Section  are Queried and those Queried objects are binded to studentSectionyear
		  to avoid duplicate entries of section and year in database */

		Section section = sectionRepository.findByStandard(theStudentSectionYear.getSection().getStandard());
		if(section!=null)
		{
			theStudentSectionYear.setSection(section);
		}
		
		Year year = yearRepository.findByYear(theStudentSectionYear.getYear().getYear());
		if (year != null) {
			theStudentSectionYear.setYear(year);
		}
   
		studentSectionYearRepository.save(theStudentSectionYear);
	}


}
