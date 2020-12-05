package com.project.school.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.school.dao.PaymentRepository;

import com.project.school.entity.FeesStructure;
import com.project.school.entity.Payment;

import com.project.school.entity.StudentSectionYear;
import com.project.school.entity.Year;
import com.project.school.helper.Search;
import com.project.school.service.FeesStructureService;
import com.project.school.service.StudentSectionYearService;

@Controller
@RequestMapping("/fee")
public class FeesStructureController {

	@Autowired
	private FeesStructureService feesStructureService;

	@Autowired
	private StudentSectionYearService ssys;

	@Autowired
	private PaymentRepository paymentRepository;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	@GetMapping("/generate")
	public String generate(@ModelAttribute("search") Search search, Model model) {

		if (search.getSearchYear() == null) {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			String batch = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.YEAR) + 1);
			search.setSearchYear(batch);
		}
		Year year = feesStructureService.findByYear(search.getSearchYear());
		model.addAttribute("controller", year);

		return "fees-structure";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("controller") Year year) {

		feesStructureService.save(year);

		return "index";
	}

	@GetMapping("/payment")
	public String feePayment(@RequestParam(name = "id", required = false) Integer id,
			@ModelAttribute("search") Search search, Model model) {

		if (id == null) {
			id = search.getSearchId();
		}
		StudentSectionYear studentSectionYear = ssys.findById(id);
		List<StudentSectionYear> year = ssys.findByStudent(studentSectionYear.getStudent());
		search.setSearchYear(year.get(year.size() - 1).getYear().getYear());
		search.setSearchId(id);
		FeesStructure feesStructure = null;
		for (StudentSectionYear temp : year) {
			if (temp.getYear().getYear().equals(search.getSearchYear())) {
				feesStructure = feesStructureService.findByYearAndSection(temp.getYear(), temp.getSection());
				studentSectionYear = ssys.findByStudentAndYear(temp.getStudent(), temp.getYear());
			}
		}

		List<Payment> payment = new LinkedList<Payment>();
		payment = paymentRepository.findByStudentSectionYear(studentSectionYear);
		Payment pay = new Payment();
		pay.setStudentSectionYear(studentSectionYear);
		payment.add(pay);
		studentSectionYear.setPayment(payment);
		model.addAttribute("year", year);
		model.addAttribute("ssy", studentSectionYear);
		model.addAttribute("feesStructure", feesStructure);

		return "fees-records";
	}

	@PostMapping("/payment/save")
	public String savePayment(@ModelAttribute("ssy") StudentSectionYear studentSectionYear) {

		StudentSectionYear tempStudentSectionYear = ssys.findById(studentSectionYear.getId());
		tempStudentSectionYear.setPayment(studentSectionYear.getPayment());
		feesStructureService.save(tempStudentSectionYear);

		return "index";
	}
}
