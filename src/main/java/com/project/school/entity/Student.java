package com.project.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.project.school.validation.uniqueAadhaar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student")
@NoArgsConstructor
@Data
@AllArgsConstructor
@uniqueAadhaar
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "is required")
	private int id;

	@Column(name = "name")
	@NotNull(message = "is required")
	private String name;

	@Column(name = "dob")
	@NotNull(message = "is required")
	private String dob;

	@Column(name = "aadhaar_id")
	@NotNull(message = "is required")
	private String aadhaarId;

	@Column(name = "phone_number")
	@NotNull(message = "is required")
	@Size(min = 10, max = 10, message = "not a valid mobile number")
	private String phoneNumber;

	@Column(name = "hostel")
	private int hostel;

	@Column(name = "gender")
	@NotNull(message = "is required")
	private String gender;

	@Column(name="payment_wise")
	@NotNull(message="is required")
	private String paymentMethod;

	public Student(@NotNull(message = "is required") String name, @NotNull(message = "is required") String dob,
			@NotNull(message = "is required") String aadhaarId,
			@NotNull(message = "is required") @Size(min = 10, max = 10, message = "not a valid mobile number") String phoneNumber,
			int hostel, @NotNull(message = "is required") String gender,
			@NotNull(message = "is required") String paymentMethod) {
		this.name = name;
		this.dob = dob;
		this.aadhaarId = aadhaarId;
		this.phoneNumber = phoneNumber;
		this.hostel = hostel;
		this.gender = gender;
		this.paymentMethod = paymentMethod;
	}
	
	
}
