package com.project.school.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "student_class_year")
@Data
@EqualsAndHashCode(exclude = "Payment")
@NoArgsConstructor
@AllArgsConstructor
public class StudentSectionYear {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	@Valid
	private Student student;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name = "class_id")
	private Section section;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name = "year_id")
	private Year year;

	@ToString.Exclude
	@OneToMany(mappedBy = "studentSectionYear", cascade = CascadeType.ALL)
	private List<Payment> Payment;

	public StudentSectionYear(@Valid Student student, Section section, Year year) {
		this.student = student;
		this.section = section;
		this.year = year;
	}

	public StudentSectionYear(@Valid Student student, Section section, Year year,
			List<com.project.school.entity.Payment> payment) {
		this.student = student;
		this.section = section;
		this.year = year;
		Payment = payment;
	}

}
