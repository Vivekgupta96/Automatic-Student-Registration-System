package com.Project.Student.Main_User;

import java.util.*;
import java.util.Scanner;

import com.Project.Student.Dao_Dao.AdminDao;
import com.Project.Student.Dao_Dao.AdminDaoImpl;
import com.Project.Student.Dao_Dao.StudentDao;
import com.Project.Student.Dao_Dao.StudentDaoImpl;
import com.Project.Student.Dao_beam.Address;
import com.Project.Student.Dao_beam.Course;
import com.Project.Student.Dao_beam.LoggedInUserId;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;
import com.Project.Student.Service.AutoGenerateUserIDs;
import com.Project.StudentRegistration.Main;


public class StudentMenu {

	public void enrollmentStudent(Scanner sc) {
		String message = null;
		System.out.println("Welcome TO student enrollment ,Please Fill Requred Details");
		System.out.println();
		System.out.println("Enter First Name : ");
		String studentfName = sc.next();
		System.out.println("Enter Last Name :");
		String studentlName = sc.next();
		
		//************************************
		System.out.println("********Enter  Address ************");
		System.out.println("Enter City Name");
		String cityName = sc.next();
		
		System.out.println("Enter PinCode Name");
		String pinCode = sc.next();
		
		System.out.println("Enter State Name");
		String State = sc.next();
		
		System.out.println("Enter Email Id  :");
		String Email = sc.next();
		System.out.println("Enter Login Password :");
		String pass = sc.next();

		String genetuserid = AutoGenerateUserIDs.StudentuserId();

		Student s = new Student();
		Address ad=new Address();
		ad.setCity(cityName);
		ad.setZipCode(pinCode);
		ad.setState(State);
		
		s.setfName(studentfName);
		s.setlName(studentlName);
		s.setStudentUserId(genetuserid);
		s.setEmail(Email);
		s.setAddress(ad);
		s.setPassword(pass);
		s.setIsDeactivate(0);

		StudentDao stEtdao = new StudentDaoImpl();

		try {
			boolean status = stEtdao.addStudentToDb(s);
			if (status) {
				System.out.println("---------------------------------------------");
				System.out.println("              Student                     ");
				System.out.println("Welcome : " + s.getfName() + s.getlName());
				System.out.println("Your Roll No : " + s.getRoll());
				System.out.println("User Id: " + s.getStudentUserId());
				System.out.println("Your PassWord: " + s.getPassword());
				System.out.println("---------------------------------------------");
			} else {
				System.out.println("Error Occure");
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public boolean LoginStudent(Scanner sc) {

		boolean message = false;
		System.out.println("Welcome TO student Login ,Please Fill Requred Details");
		System.out.println("Enter your UserName");
		String userId = sc.next();
		System.out.println("Enter Your Password ");
		String pass = sc.next();

		StudentDao stEtdao = new StudentDaoImpl();
		try {

			String res=stEtdao.studentAuthToDb(userId, pass);
			if(res=="") {
				System.out.println("Account Not Exist!");
			}else {
				System.out.println("Welcome :"+res);
				message = true;
			}
		} catch (SomeThingWrongException | NoRecordFoundException e) {
			System.out.println("AccountNot found or UserId PassWord Invalid*");
			
		}
		return message;

	}

	public void UpdatePassword(Scanner sc) {

		System.out.println("Enter your old Password");
		String oldPass = sc.next();
		System.out.println("Enter Your New  Password ");
		String pass = sc.next();

		StudentDao stEtdao = new StudentDaoImpl();

		try {
			String status = stEtdao.studentUpdatePassword( oldPass, pass);

			System.out.println("Mesage : " +status);

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			System.out.println("old Password Are Incorrect");
		}

	}

	public void UpdateEmail(Scanner sc) {

		System.out.println("Enter Your New email ");
		String newEmail = sc.next();
		
		Student et = new Student();
		et.setEmail(newEmail);

		StudentDao stEtdao = new StudentDaoImpl();
		try {
			String status = stEtdao.studentEmailUpdate(et);

			System.out.println("updated email : " + status);

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			System.out.println("Error Occured");
		}

	}

	public void viewAllStudentCourse() {
		StudentDao stEtdao = new StudentDaoImpl();

		try {
			List<Course> res;
			try {
				res = stEtdao.viewAllCourses();
				if (res != null) {
					System.out.println("All Course Detail");
					res.forEach(System.out::println);
				} else {
					System.out.println("No Course Found");
				}
			} catch (NoRecordFoundException e) {
				System.out.println("error Found");
			}
		} catch (SomeThingWrongException e) {
			System.out.println("error Found");
		}
	}

	public void deleteStudentAccout(Scanner sc) {

		String choice = null;
		System.out.println("You you sure To delete Your Account-->(Y/N)");
		choice = sc.next().toLowerCase();
		if (choice.equals("y")) {
			StudentDao stEtdao = new StudentDaoImpl();
			System.out.println("Enter your RollNo To de_activate");
			try {
				int userId = sc.nextInt();
				if(userId==LoggedInUserId.studentloggedRollNo) {
					String status = stEtdao.deleteAccout(userId);

					System.out.println("Account Deleted : " + status);
					Main.dashboardMenu();
				}else {
					System.out.println("Wrong Roll Number");
					deleteStudentAccout(sc);
				}
			} catch (SomeThingWrongException | NoRecordFoundException e) {
				System.out.println("Error Ocured");
			}
		} else {
			Main.studentAactivity(sc);
		}
	}

	public void Logout(Scanner sc) {
		LoggedInUserId.studentloggedRollNo=0;
		Main.dashboardMenu();

	}

	// wrong have to fixed
	public void registerForCourses(Scanner sc) {
		System.out.println("   Please Fill Requred Details     ");
		System.out.println("Enter BatchId ");
		int Batchid = sc.nextInt();
		int StudentRoll=(int) LoggedInUserId.studentloggedRollNo;
		AdminDao admEtdao = new AdminDaoImpl();
		try {
			admEtdao.registerStudentBatch(StudentRoll, Batchid);
			System.out.println("Batch With Course alloted succesfully");

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			System.out.println("Error Occured");
		}
	}

	public void SeeEnrollCourses(Scanner sc) {
		
		int StudentRollNo = (int) LoggedInUserId.studentloggedRollNo;
		StudentDao stEtdao = new StudentDaoImpl();

		try {

			Set<Course> res = stEtdao.StudentEnrollCourses(StudentRollNo);
			System.out.println("*******   Student Enrolled Courses  *********");
			if(res==null) {
				System.out.println("Student Not Erroled in any Course");
			}else {
				for(Course cc:res) {
					System.out.println("Course_Name :"+cc.getCorseName());
				}
			}

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
