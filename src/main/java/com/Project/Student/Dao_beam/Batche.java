package com.Project.Student.Dao_beam;

import java.util.*;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Batch_Table")
public class Batche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private int batchId;

	@Column(name = "Batch_name", nullable = false, length = 25, unique = true)
	private String bartchName;

	@Column(name = "Start_Date", nullable = false, length = 25)
	private String startDate;

	@Column(name = "End_Date", nullable = false, length = 25)
	private String endDate;

	@Column(name = "Batch_seat", nullable = false, length = 10)
	private int seat;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "batchs")
	private Set<StudentReg> reg = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "Course_Id")
	private Course courses ;

	public Batche() {}

	public Batche(String bartchName, String startDate, String endDate, int seat, Set<StudentReg> reg,
			Course courses) {
		super();
		this.bartchName = bartchName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seat = seat;
		this.reg = reg;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Set<StudentReg> getReg() {
		return reg;
	}

	public void setReg(Set<StudentReg> reg) {
		this.reg = reg;
	}

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "BatcheEntity [batchId=" + batchId + ", bartchName=" + bartchName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", seat=" + seat + ", reg=" + reg + "]";
	}


}
