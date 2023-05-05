package com.Project.Student.Dao_beam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

	@Column(name = "course_instructer")
	private String courseInstructor;

	public CourseEntity() {
		super();

	}

	public CourseEntity(String corseName, int duration, String description, double fee, String courseInstructor) {
		super();
		this.corseName = corseName;
		this.duration = duration;
		this.description = description;
		this.fee = fee;
		this.courseInstructor = courseInstructor;
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

	@Override
	public String toString() {
		return "CourseEntity [couresId=" + couresId + ", corseName=" + corseName + ", duration=" + duration
				+ ", description=" + description + ", fee=" + fee + ", courseInstructor=" + courseInstructor + "]";
	}

}
