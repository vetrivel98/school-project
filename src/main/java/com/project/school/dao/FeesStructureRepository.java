package com.project.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.entity.FeesStructure;
import com.project.school.entity.Section;

import com.project.school.entity.Year;

public interface FeesStructureRepository extends JpaRepository<FeesStructure, Integer> {

	List<FeesStructure> findByYear(Year year); 

	FeesStructure findByYearAndSection(Year year, Section section);
}
