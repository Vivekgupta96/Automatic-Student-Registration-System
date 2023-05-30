package com.Project.Student.Dao_beam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Registration_Table")
public class StudentReg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Registeration_Id")
	private int registrationId;
	
	@Column(name="Registration_Date")
	private String registrationDate;

	@ManyToOne
	@JoinColumn(name="Student_ID")
	private Student students;

	@ManyToOne
	@JoinColumn(name="Batche_ID")
	private Batche batchs;

	public StudentReg() {}

	public StudentReg( String registrationDate, Student students, Batche batchs) {
		super();
		this.registrationDate = registrationDate;
		this.students = students;
		this.batchs = batchs;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Student getStudents() {
		return students;
	}

	public void setStudents(Student students) {
		this.students = students;
	}

	public Batche getBatchs() {
		return batchs;
	}

	public void setBatchs(Batche batchs) {
		this.batchs = batchs;
	}

	

}
