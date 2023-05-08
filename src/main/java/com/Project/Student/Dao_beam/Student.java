package com.Project.Student.Dao_beam;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentRollNo")
	private int roll;

	@Column(name = "StudentName", length = 20, nullable = false)
	private String stName;
	
	@Column(name="StudentEmail",length=50,unique=true)
	private String email;
	
	@Column(name="studentAddress",length=50,nullable=false)
	private String address;
	
	@Column(name="studentUserId",nullable=false)
	private  String studentUserId;
	
	
	@Column(name="studentPassword",nullable=false,length=50)
	private String password;
	
	@Column(name="isAccoutDeactivate")
	private int isDeactivate;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
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

	public Student(String stName, String email, String address, String studentUserId, String password,
			int isDeactivate) {
		super();
		this.stName = stName;
		this.email = email;
		this.address = address;
		this.studentUserId = studentUserId;
		this.password = password;
		this.isDeactivate = isDeactivate;
	}

	@Override
	public String toString() {
		return "Student [roll=" + roll + ", stName=" + stName + ", email=" + email + ", address=" + address
				+ ", studentUserId=" + studentUserId + ", password=" + password + ", isDeactivate=" + isDeactivate
				+ "]";
	}

	
}
