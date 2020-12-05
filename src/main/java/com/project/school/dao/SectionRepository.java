package com.project.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {

	Section findByStandard(String standard);
}
