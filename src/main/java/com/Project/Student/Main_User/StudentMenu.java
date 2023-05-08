package com.Project.Student.Main_User;

import java.util.List;
import java.util.Scanner;

import com.Project.Student.Dao_Dao.*;
import com.Project.Student.Dao_beam.*;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;
import com.Project.Student.Service.AutoGenerateAdminUserID;
import com.Project.StudentRegistration.Main;

public class StudentMenu {

	public void enrollmentStudent(Scanner sc) {
		String message = null;
		System.out.println("Welcome TO student enrollment ,Please Fill Requred Details");
		System.out.println("Enter Student Name");
		String studentName = sc.next();
		System.out.println("Enter Email Address");
		String Email = sc.next();
		System.out.println("Enter  Address");
		String Address = sc.next();
		System.out.println("Enter Login Password ");
		String pass = sc.next();

		String genetuserid = AutoGenerateAdminUserID.StudentuserId();

		Student s = new Student();

		s.setStName(studentName);
		s.setStudentUserId(genetuserid);
		s.setEmail(Email);
		s.setAddress(Address);
		s.setPassword(pass);
		s.setIsDeactivate(0);

		StudentEntityDao stEtdao = new StudentEntityDaoImpl();

		try {
			boolean status = stEtdao.addStudentToDb(s);
			if (status) {
				System.out.println("---------------------------------------------");
				System.out.println("              Administer                     ");
				System.out.println("Welcome : " + s.getStName());
				System.out.println("Your Roll No : " + s.getRoll());
				System.out.println("User Id: " + s.getStudentUserId());
				System.out.println("Your PassWord: " + s.getPassword());
			} else {
				System.out.println("Error Occure");
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public boolean LoginStudent(Scanner sc) {
//		
		
		
		boolean message = false;
		System.out.println("Welcome TO student Login ,Please Fill Requred Details");
		System.out.println("Enter your UserName");
		String userId = sc.next();
		System.out.println("Enter Your Password ");
		String pass = sc.next();

		StudentEntityDao stEtdao = new StudentEntityDaoImpl();

		String name=null;
		String Acstsus=null;
		try {

			String status = stEtdao.studentAuthToDb(userId, pass);		
			
			
			System.out.println("Welcome : " +status);
			message = true;
			

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

	public void UpdatePassword(Scanner sc) {

		System.out.println("Enter your userid");
		String userId = sc.next();
		System.out.println("Enter your old Password");
		String oldPass = sc.next();
		System.out.println("Enter Your New  Password ");
		String pass = sc.next();

		StudentEntityDao stEtdao = new StudentEntityDaoImpl();

		try {

			String status = stEtdao.studentUpdatePassword(userId,oldPass, pass);

			System.out.println("Mesage : " + status);

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void UpdateEmail(Scanner sc) {
		System.out.println("Enter your UserName");
		String userId = sc.next();
		System.out.println("Enter Your New email ");
		String newEmail = sc.next();

		StudentEntityDao stEtdao = new StudentEntityDaoImpl();

		try {

			String status = stEtdao.studentEmailUpdate(userId, newEmail);

			System.out.println("updated email : " + status);

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void viewAllStudentCourse() {

		StudentEntityDao stEtdao = new StudentEntityDaoImpl();

		try {
			List<CourseEntity> res;
			try {
				res = stEtdao.viewAllCourses();
				if (res != null) {
					System.out.println("All Course Detail");
					res.forEach(System.out::println);
				} else {
					System.out.println("No course Avalable");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public void deleteStudentAccout(Scanner sc) {
		
		String choice=null;
		System.out.println("You you sure To delete Your Account-->(Y/N)");
		choice=sc.next().toLowerCase();
		if(choice.equals("y")) {
			System.out.println("Enter your UserName To de_activate");
			String userId = sc.next();
			
			StudentEntityDao stEtdao = new StudentEntityDaoImpl();

			try {

				String status = stEtdao.deleteAccout(userId.toString());

				System.out.println("Account Deleted : " + status);

			} catch (SomeThingWrongException | NoRecordFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			 Main.studentAactivity(sc);
		}
		
	}

	public void Logout(Scanner sc) {
		Main.dashboardMenu();
		
	}
	
}
