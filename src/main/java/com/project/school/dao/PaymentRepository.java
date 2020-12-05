package com.project.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.school.entity.Payment;
import com.project.school.entity.StudentSectionYear;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	List<Payment> findByStudentSectionYear(StudentSectionYear studentSectionYear);
}
