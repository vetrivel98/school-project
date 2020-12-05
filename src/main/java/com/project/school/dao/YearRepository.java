package com.project.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.entity.Year;

public interface YearRepository extends JpaRepository<Year, Integer> {

	Year findByYear(String year);
}
