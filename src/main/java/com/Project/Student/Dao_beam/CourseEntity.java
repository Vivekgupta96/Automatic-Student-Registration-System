package com.Project.Student.Dao_beam;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int couresId;

	@Column(name = "course_name", unique = true, nullable = false)
	private String corseName;

	@Column(name = "course_duration", nullable = false)
	private int duration;

	@Column(name = "course_description", nullable = false)
	private String description;

	@Column(name = "course_fee", nullable = false)
	private double fee;

	@Column(name = "course_instructer", nullable = false)
	private String courseInstructor;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy ="courses" )
	private Set<StudentEntity> students=new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER)
	private Set<BatcheEntity> batche=new HashSet<>();

	public CourseEntity() {
		super();

	}

	public CourseEntity(String corseName, int duration, String description, double fee, String courseInstructor,
			Set<StudentEntity> students, Set<BatcheEntity> batche) {
		super();
		this.corseName = corseName;
		this.duration = duration;
		this.description = description;
		this.fee = fee;
		this.courseInstructor = courseInstructor;
		this.students = students;
		this.batche = batche;
	}

	public int getCouresId() {
		return couresId;
	}

	public void setCouresId(int couresId) {
		this.couresId = couresId;
	}

	public String getCorseName() {
		return corseName;
	}

	public void setCorseName(String corseName) {
		this.corseName = corseName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

	public Set<BatcheEntity> getBatche() {
		return batche;
	}

	public void setBatche(Set<BatcheEntity> batche) {
		this.batche = batche;
	}

	@Override
	public String toString() {
		return "Course [couresId=" + couresId + ", corseName=" + corseName + ", duration=" + duration
				+ ", description=" + description + ", fee=" + fee + ", courseInstructor=" + courseInstructor
				+ "]";
	}

	
	
}
