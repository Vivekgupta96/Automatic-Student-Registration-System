package com.Project.Student.Dao_beam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="studentRollNo")
	private int roll;
	
	@Column(name="StudentName" ,length=20,nullable=false)
	private String stName;
	
	@Column(name="sciurseId",length=10,nullable=false)
	private int courseId;
	
	@Column(name="scourseName",length=25,nullable=false)
	private String courseName;
	
	@Column(name="sbatchId" ,nullable=false)
	private int batchId;
	
	@Column(name="sbatchName",nullable=false)
	private String batchName;

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(String stName, int courseId, String courseName, int batchId, String batchName) {
		super();
		this.stName = stName;
		this.courseId = courseId;
		this.courseName = courseName;
		this.batchId = batchId;
		this.batchName = batchName;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	@Override
	public String toString() {
		return "StudentEntity [roll=" + roll + ", stName=" + stName + ", courseId=" + courseId + ", courseName="
				+ courseName + ", batchId=" + batchId + ", batchName=" + batchName + "]";
	}
	
	
	
}
