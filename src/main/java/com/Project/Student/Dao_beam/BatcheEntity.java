package com.Project.Student.Dao_beam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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


	@Column(name="courseId")
	private int couresId;
	
	public BatcheEntity() {
		super();
	}

	public BatcheEntity(String bartchName, int seat, int couresId) {
		super();
		this.bartchName = bartchName;
		this.seat = seat;
		this.couresId = couresId;
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

	public int getCouresId() {
		return couresId;
	}

	public void setCouresId(int couresId) {
		this.couresId = couresId;
	}

	@Override
	public String toString() {
		return "BatcheEntity [batchId=" + batchId + ", bartchName=" + bartchName + ", seat=" + seat + ", couresId="
				+ couresId + "]";
	}

	
}
