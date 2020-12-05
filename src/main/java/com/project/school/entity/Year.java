package com.project.school.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="year")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Year {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="batch")
	private String year;
	
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "year", cascade = CascadeType.ALL)
	private List<FeesStructure> feesStructure;

	public Year(String year) {
		this.year = year;
	}

	public Year(String year, List<com.project.school.entity.FeesStructure> feesStructure) {
		this.year = year;
		this.feesStructure = feesStructure;
	}

}
