package com.project.school.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.school.entity.StudentSectionYear;
import com.project.school.helper.Search;
import com.project.school.service.FeesStructureService;
import com.project.school.service.StudentSectionYearService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentSectionYearService theSSYS;

	@Autowired
	private FeesStructureService feesStructureService;

	// This functions trims down leading and trailing white spaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, editor);
	}

	@GetMapping("/list")
	public String find(@ModelAttribute("search") Search search, Model model) {

		if (search.getSearchYear() == null) {
			Date d = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			String batch = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.YEAR) + 1);
			search.setSearchYear(batch);
		}
		if (search.getSearchStandard() == null) {
			search.setSearchStandard("Pre-kg");
		}

		List<StudentSectionYear> tempSSY = theSSYS.findByStandardAndYear(search.getSearchStandard(),
				search.getSearchYear());
		model.addAttribute("ssy", tempSSY);

		return "student-list";
	}

	@GetMapping("/add")
	public String save(Model model) {

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(feesStructureService.findAll());
		} catch (Exception e) {
			System.out.println(e);
		}
		model.addAttribute("ssy", new StudentSectionYear());
		model.addAttribute("fees", jsonString);

		return "studentform";
	}

	@PostMapping("/save")
	public String processForm(@Valid @ModelAttribute("ssy") StudentSectionYear ssy, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) { // checking for form input error if any then display form with errors

			return "studentform";
		}

		theSSYS.save(ssy);

		Search search = new Search(); // Pre populating search fields of student list page
		search.setSearchStandard(ssy.getSection().getStandard());
		search.setSearchYear(ssy.getYear().getYear());
		model.addAttribute("search", search);

		List<StudentSectionYear> SSY = theSSYS.findByStandardAndYear(search.getSearchStandard(),
				search.getSearchYear());
		model.addAttribute("ssy", SSY);

		return "student-list";
	}

	@GetMapping("/update")
	public String updateForm(@RequestParam("ssyId") int id, Model model) {

		StudentSectionYear tempSSY = theSSYS.findById(id); // get the associated object and binding it to student form
		model.addAttribute("ssy", tempSSY);

		return "studentform";
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam("ssyId") int id, Model model) {

		StudentSectionYear studentSectionYear = theSSYS.findById(id);

		/*
		 * After delete operation performed its directed to student list by pre
		 * populating search fields year and standard
		 */
		Search search = new Search();
		search.setSearchStandard(studentSectionYear.getSection().getStandard());
		search.setSearchYear(studentSectionYear.getYear().getYear());
		model.addAttribute("search", search);

		theSSYS.deleteById(id);
		List<StudentSectionYear> SSY = theSSYS.findByStandardAndYear(search.getSearchStandard(),
				search.getSearchYear());
		model.addAttribute("ssy", SSY);

		return "student-list";
	}
}
