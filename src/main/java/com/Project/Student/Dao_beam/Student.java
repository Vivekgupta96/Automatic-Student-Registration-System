package com.Project.Student.Dao_beam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Student_Table")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentRollNo")
	private int roll;

	@Column(name = "First_Name", length = 20, nullable = false)
	private String fName;
	
	@Column(name = "Last_Name", length = 20, nullable = false)
	private String lName;

	@Column(name = "StudentEmail", length = 50, unique = true)
	private String email;

//	@Column(name = "studentAddress", length = 50, nullable = false)
//	private String address;
	@Embedded
	private Address address;

	@Column(name = "studentUserId", nullable = false)
	private String studentUserId;

	@Column(name = "studentPassword", nullable = false, length = 50)
	private String password;

	@Column(name = "isAccoutDeactivate")
	private int isDeactivate;

	///
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "students")
	private Set<Course> courses=new HashSet<>();
	
	//one Student can hava registration for many batches
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "students")
	private Set<StudentReg> reg=new HashSet<>();

	public Student() {}

	public Student(String fName, String lName, String email, Address address, String studentUserId, String password,
			int isDeactivate, Set<Course> courses, Set<StudentReg> reg) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.address = address;
		this.studentUserId = studentUserId;
		this.password = password;
		this.isDeactivate = isDeactivate;
		this.courses = courses;
		this.reg = reg;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<StudentReg> getReg() {
		return reg;
	}

	public void setReg(Set<StudentReg> reg) {
		this.reg = reg;
	}

	@Override
	public String toString() {
		return "Student [roll=" + roll + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", address="
				+ address + ", studentUserId=" + studentUserId + ", isDeactivate=" + isDeactivate + "]";
	}

	

}
