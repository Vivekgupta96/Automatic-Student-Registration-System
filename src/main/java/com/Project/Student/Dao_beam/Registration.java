package com.Project.Student.Dao_beam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Registeration_Id")
	private int registrationId;
	
	@Column(name="Registration_Date")
	private String registrationDate;

	@ManyToOne
	@JoinColumn(name="Student_ID")
	private StudentEntity studentEnitty;

	@ManyToOne
	@JoinColumn(name="Batche_ID")
	private BatcheEntity batcheEntity;

	public Registration() {}

	public Registration(String registrationDate, StudentEntity studentEnitty, BatcheEntity batcheEntity) {
		super();
		this.registrationDate = registrationDate;
		this.studentEnitty = studentEnitty;
		this.batcheEntity = batcheEntity;
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

	public StudentEntity getStudentEnitty() {
		return studentEnitty;
	}

	public void setStudentEnitty(StudentEntity studentEnitty) {
		this.studentEnitty = studentEnitty;
	}

	public BatcheEntity getBatcheEntity() {
		return batcheEntity;
	}

	public void setBatcheEntity(BatcheEntity batcheEntity) {
		this.batcheEntity = batcheEntity;
	}

	
	

}
