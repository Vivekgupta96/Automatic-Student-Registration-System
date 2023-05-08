package com.Project.Student.Dao_beam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentRollNo")
	private int roll;

	@Column(name = "StudentName", length = 20, nullable = false)
	private String stName;

	@Column(name="batchId")
    private int batchId;
	
	
	@Column(name = "sbatchName", nullable = false)
	private String batchName;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="studentCourse",
     joinColumns={@JoinColumn(name="studentRollNo")},
	inverseJoinColumns={@JoinColumn(name="courseId")}
	)
	private Set<CourseEntity> courses= new HashSet<>();

	public StudentEntity() {
		super();
		
	}
	
	public StudentEntity(String stName, int batchId, String batchName, Set<CourseEntity> courses) {
		super();
		this.stName = stName;
		this.batchId = batchId;
		this.batchName = batchName;
		this.courses = courses;
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

	public Set<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseEntity> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "StudentEntity [roll=" + roll + ", stName=" + stName + ", batchId=" + batchId + ", batchName="
				+ batchName + ", courses=" + courses + "]";
	}

	
}
