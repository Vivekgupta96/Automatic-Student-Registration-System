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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Course_Table")
public class Course {

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
	
	
	//A student can be enrolled in multiple courses, and a course
	//can have multiple students.
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Student> students=new HashSet<>();

	//one course can have multiple batches
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "courses")
	private Set<Batche> batche=new HashSet<>();

	public Course() {
		super();

	}

	public Course(String corseName, int duration, String description, double fee, String courseInstructor,
			Set<Student> students, Set<Batche> batche) {
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Batche> getBatche() {
		return batche;
	}

	public void setBatche(Set<Batche> batche) {
		this.batche = batche;
	}

	@Override
	public String toString() {
		return "Course [couresId=" + couresId + ", corseName=" + corseName + ", duration=" + duration
				+ ", description=" + description + ", fee=" + fee + ", courseInstructor=" + courseInstructor
				+ "]";
	}

	
	
}
