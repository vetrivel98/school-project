package com.project.school.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="date")
	private String date;
	
	@Column(name="tution_fee")
	private String tutionFee;
	
	@Column(name="hostel_fee")
	private String hostelFee;
	
	@Column(name="extra_fee")
	private String extraFee;
	
	@Column(name="total_paid")
	private String paidFee;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="student_class_year_id")
	private StudentSectionYear studentSectionYear;

	public Payment(String date, String tutionFee, String hostelFee, String extraFee, String paidFee,
			StudentSectionYear studentSectionYear) {
		this.date = date;
		this.tutionFee = tutionFee;
		this.hostelFee = hostelFee;
		this.extraFee = extraFee;
		this.paidFee = paidFee;
		this.studentSectionYear = studentSectionYear;
	}


}
