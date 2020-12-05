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
@Table(name = "fees_structure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesStructure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "tution_fee")
	private String tutionFee;

	@Column(name = "hostel_fee")
	private String hostelFee;

	@Column(name = "extra_fee")
	private String extraFee;

	@Column(name = "total_fee")
	private String total;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "class_id")
	private Section section;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "year_id")
	private Year year;

	public FeesStructure(Section section, Year year) {
		this.section = section;
		this.year = year;
	}

	public FeesStructure(String tutionFee, String hostelFee, String extraFee, String total, Section section,
			Year year) {
		this.tutionFee = tutionFee;
		this.hostelFee = hostelFee;
		this.extraFee = extraFee;
		this.total = total;
		this.section = section;
		this.year = year;
	}

}
