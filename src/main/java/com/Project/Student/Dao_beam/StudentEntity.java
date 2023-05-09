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

	@Column(name = "StudentEmail", length = 50, unique = true)
	private String email;

	@Column(name = "studentAddress", length = 50, nullable = false)
	private String address;

	@Column(name = "studentUserId", nullable = false)
	private String studentUserId;

	@Column(name = "studentPassword", nullable = false, length = 50)
	private String password;

	@Column(name = "isAccoutDeactivate")
	private int isDeactivate;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "StudentBatchId")
	private BatcheEntity batch;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "studentCourse", joinColumns = { @JoinColumn(name = "studentRollNo") }, inverseJoinColumns = {
			@JoinColumn(name = "courseId") })
	private Set<CourseEntity> courses = new HashSet<>();


	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentEntity(String stName, String email, String address, String studentUserId, String password,
			int isDeactivate, BatcheEntity batch, Set<CourseEntity> courses) {
		super();
		this.stName = stName;
		this.email = email;
		this.address = address;
		this.studentUserId = studentUserId;
		this.password = password;
		this.isDeactivate = isDeactivate;
		this.batch = batch;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStudentUserId() {
		return studentUserId;
	}


	public void setStudentUserId(String studentUserId) {
		this.studentUserId = studentUserId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getIsDeactivate() {
		return isDeactivate;
	}


	public void setIsDeactivate(int isDeactivate) {
		this.isDeactivate = isDeactivate;
	}


	public BatcheEntity getBatch() {
		return batch;
	}


	public void setBatch(BatcheEntity batch) {
		this.batch = batch;
	}


	public Set<CourseEntity> getCourses() {
		return courses;
	}


	public void setCourses(Set<CourseEntity> courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "StudentEntity [roll=" + roll + ", stName=" + stName + ", email=" + email + ", address=" + address
				+ ", studentUserId=" + studentUserId + ", password=" + password + ", isDeactivate=" + isDeactivate
				+ ", batch=" + batch + ", courses=" + courses + "]";
	}


	
}
