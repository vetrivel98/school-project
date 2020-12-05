package com.project.school.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.school.dao.FeesStructureRepository;
import com.project.school.dao.SectionRepository;
import com.project.school.dao.StudentSectionYearRepository;
import com.project.school.dao.YearRepository;
import com.project.school.entity.FeesStructure;
import com.project.school.entity.Payment;
import com.project.school.entity.Section;
import com.project.school.entity.StudentSectionYear;
import com.project.school.entity.Year;


@Service
public class FeesStructureServiceImpl implements FeesStructureService {

	@Autowired
	private FeesStructureRepository feesStructureRepository;

	@Autowired
	private StudentSectionYearRepository studentSectionYearRepository;

	@Autowired
	private YearRepository yearRepository;

	@Autowired
	private SectionRepository sectionRepository;

	@Override
	public List<FeesStructure> findAll() {

		return feesStructureRepository.findAll();
	}

	@Override
	public FeesStructure findByYearAndSection(Year year, Section section) {

		return feesStructureRepository.findByYearAndSection(year, section);
	}

	@Override
	public void save(Year year) {

		int i = 0;
		List<Section> section = sectionRepository.findAll();
		for (FeesStructure temp : year.getFeesStructure()) {
			temp.setSection(section.get(i));
			feesStructureRepository.save(temp);
			i++;
		}

	}

	@Override
	public Year findByYear(String year) {

		Year tempYear = yearRepository.findByYear(year);
		List<FeesStructure> feesStructure = feesStructureRepository.findByYear(tempYear);

		/*
		 * Initially checking do we have FeesStructure with associated year if Yes then
		 * we set the list to wrapper class Fees and return it
		 */

		if (!feesStructure.isEmpty()) {
			
			return tempYear;
		}
		feesStructure = new LinkedList<FeesStructure>();

		// If the year itself not available in database creates one and saves it

		if (tempYear == null) {
			tempYear = new Year(year);
			yearRepository.save(tempYear);
			tempYear = yearRepository.findByYear(year);
		}

		// Querying List of Sections
		// Adding each section and year to the feesStructure List

		List<Section> theSection = sectionRepository.findAll();
		for (Section tempSection : theSection) {
			feesStructure.add(new FeesStructure(tempSection, tempYear));
		}

		// Wrap the feeStructure List to Fees class and Return it.

		tempYear.setFeesStructure(feesStructure);
		System.out.println("End of findByyear controller");
		return tempYear;
	}

	@Override
	public void save(StudentSectionYear studentSectionYear) {

		Payment currentPayment = studentSectionYear.getPayment().get(studentSectionYear.getPayment().size() - 1);
		
		// check if the total paid fee is greater than zero else don't save the object
		if (currentPayment.getPaidFee() == null) {
			studentSectionYear.getPayment().remove(studentSectionYear.getPayment().size() - 1);
		}
		studentSectionYearRepository.save(studentSectionYear);
	}

}
