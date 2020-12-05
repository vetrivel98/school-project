package com.project.school.service;

import java.util.List;


import com.project.school.entity.FeesStructure;
import com.project.school.entity.Section;
import com.project.school.entity.StudentSectionYear;
import com.project.school.entity.Year;


public interface FeesStructureService {
	
	public List<FeesStructure> findAll();

	public Year findByYear(String year);
	
	public FeesStructure findByYearAndSection(Year year,Section section);
	
	public void save(Year year);
	
	public void save(StudentSectionYear studentSectionYear);

}
