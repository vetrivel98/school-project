package com.project.school.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.school.dao.StudentRepository;
import com.project.school.entity.Student;

public class CheckDuplicate implements ConstraintValidator<uniqueAadhaar, Student> {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public void initialize(uniqueAadhaar args0) {
	}

	@Override
	public boolean isValid(Student value, ConstraintValidatorContext context) {
		if (value == null || studentRepository == null) {
			return true;
		}
		Student student = studentRepository.findByAadhaarId(value.getAadhaarId());
		if (student != null) {
			if (value.getId() == 0)
				return false;
			else if (value.getId() != student.getId())
				return false;
		}

		return true;
	}

}
