package com.Project.Student.Dao_beam;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class BatcheEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private int batchId;

	@Column(name = "Batch_name", nullable = false, length = 25, unique = true)
	private String bartchName;

	@Column(name = "total_seat", nullable = false, length = 10)
	private int seat;


	@OneToMany(mappedBy = "batch", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<StudentEntity> students = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "batch_course")
	private Set<CourseEntity> courses = new HashSet<>();

	public BatcheEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BatcheEntity(String bartchName, int seat, Set<StudentEntity> students, Set<CourseEntity> courses) {
		super();
		this.bartchName = bartchName;
		this.seat = seat;
		this.students = students;
		this.courses = courses;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBartchName() {
		return bartchName;
	}

	public void setBartchName(String bartchName) {
		this.bartchName = bartchName;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	public Set<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseEntity> courses) {
		this.courses = courses;
	}

}
