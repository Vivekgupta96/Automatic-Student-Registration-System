package com.Project.Student.Dao_beam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="AdminDetail")
public class AdminEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Admin_id", nullable=false)
	private int adminid;
	
	@Column(name="Admin_name", nullable=false,length=50)
	private String adminName;
	
	@Column(name="Admin_userId", unique =true, nullable=false,length=20)
	private String adminUserId;
	
	@Column(name="Admin_Pass", nullable=false,length=16)
	private String adminPassword;

	public AdminEntity() {
		super();
		
	}
	
	/* for autheticate the admin creadential */
	public AdminEntity(String adminUserId, String adminPassword) {
		super();
		this.adminUserId = adminUserId;
		this.adminPassword = adminPassword;
	}



	public AdminEntity(String adminName, String adminUserId, String adminPassword) {
		super();
		this.adminName = adminName;
		this.adminUserId = adminUserId;
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + ", adminUserId=" + adminUserId + ", adminPassword=" + adminPassword
				+ "]";
	}
	
}
