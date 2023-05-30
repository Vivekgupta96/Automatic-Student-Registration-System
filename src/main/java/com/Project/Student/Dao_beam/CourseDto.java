package com.Project.Student.Dao_beam;

public class CourseDto {
	
	private String InstructerName;
	private int duration;
	private double fee;
	public CourseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseDto(String instructerName, int duration, double fee) {
		super();
		InstructerName = instructerName;
		this.duration = duration;
		this.fee = fee;
	}
	public String getInstructerName() {
		return InstructerName;
	}
	public void setInstructerName(String instructerName) {
		InstructerName = instructerName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	

}
