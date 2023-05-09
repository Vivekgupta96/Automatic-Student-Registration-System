package com.Project.Student.Main_User;

import java.util.InputMismatchException;
import java.util.*;
import java.util.Scanner;

import com.Project.Student.Dao_Dao.AdminEntityDao;
import com.Project.Student.Dao_Dao.AdminEntityDaoImpl;
import com.Project.Student.Dao_beam.AdminEntity;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;
import com.Project.Student.Service.AutoGenerateAdminUserID;
import com.Project.StudentRegistration.Main;


public class Administrator {

	public void adminRegister(Scanner sc) {

		String message = null;
		System.out.println("Welcome TO Admin Register ,Please Fill Requred Details");
		System.out.println("Enter Your Name");
		String adminName = sc.next();
		System.out.println("Enter your UserName");// need to auto generate user name
		String userId = sc.next();
		System.out.println("Enter Your Password ");
		String pass = sc.next();

		String genetuserid = AutoGenerateAdminUserID.userId(adminName);

		AdminEntity admEt = new AdminEntity();
		admEt.setAdminName(adminName);
		admEt.setAdminUserId(genetuserid);
		admEt.setAdminPassword(pass);

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			boolean status = admEtdao.addAdminToDb(admEt);
			if (status) {
				System.out.println("---------------------------------------------");
				System.out.println("              Administer                     ");
				System.out.println("Welcome : " + admEt.getAdminName());
				System.out.println("User Id: " + admEt.getAdminUserId());
				System.out.println("Your PassWord: " + admEt.getAdminPassword());
			} else {
				System.out.println("Error Occure");
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public boolean adminLoginAuthrticate(Scanner sc) {

		boolean message = false;
		System.out.println("------------------------------------------------------");
		System.out.println("Welcome TO Admin Login ,Please Fill Requred Details");
		System.out.println("Enter your UserName");
		String userId = sc.next();
		System.out.println("Enter Your Password ");
		String pass = sc.next();
		System.out.println("------------------------------------------------------");

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {

			String status = admEtdao.adminAuthToDb(userId, pass);

			System.out.println("Welcome : " + status);
			message = true;

		} catch (SomeThingWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	public void AddNewCourse(Scanner sc) {
		
		try {
		System.out.println("Enter Course Name");
		String cName = sc.next();
		
		System.out.println("Enter Course Duration(IN Months)");
		int cduration = sc.nextInt();
		
		System.out.println("Enter Course Deacription (fUllTime/PartTime)");
		String description=sc.next();
		
		System.out.println("Enter course fee");
		double cFee = sc.nextDouble();
		System.out.println("Enter Course Instructer");
		String InsName = sc.next();

		CourseEntity cs = new CourseEntity();
		cs.setCorseName(cName);
		cs.setDescription(description);
		cs.setDuration(cduration);
		cs.setFee(cFee);
		cs.setCourseInstructor(InsName);

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			boolean res = admEtdao.admiNewCourseAdd(cs);
			if (res) {
				System.out.println("Course Added Succesfully");
				System.out.println(cs);
			}
		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}
		
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Entry!Please Select the from Given Input");
			System.out.println("------------------------------------------------------");
			 AddNewCourse( sc);
		}

	}

	public void updateCourseDetail(Scanner sc) {

		System.out.println("Enter course fee");
		double cFee = sc.nextDouble();
		System.out.println("Enter Course Id");
		int courseId = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			CourseEntity res;
			try {
				res = admEtdao.admiCourseUpdate(cFee, courseId);
				if (res != null) {
					System.out.println("Course Update Succesfully");
					System.out.println(res);
				} else {
					System.out.println("error Occured");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public void viewAllCourse() {

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			List<CourseEntity> res;
			try {
				res = admEtdao.admiViewAllCourse();
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

	public void deleteCourse(Scanner sc) {

		System.out.println("Enter Course Id to Delete Course");
		int courseId = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			boolean res;
			try {
				res = admEtdao.admiCourseDelete(courseId);
				if (res) {
					System.out.println("Course Deleted Succesfully");

				} else {
					System.out.println("error Occured or corse Not Exist!");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public void SearchCourse(Scanner sc) {

		System.out.println("Enter Course Id to Search Course");
		int courseId = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		CourseEntity res;
		try {
			res = admEtdao.adminCourseSerch(courseId);
			if (res != null) {
				System.out.println(res);

			} else {
				System.out.println("error Occured or corse Not Exist!");
			}
		} catch (SomeThingWrongException | NoRecordFoundException e) {

			e.printStackTrace();
		}
	}
	
	public void Logout() {
		Main.dashboardMenu();	
	}
	
	public void createBatch(Scanner sc) {

		System.out.println("enter the Batch details");
		System.out.println("enter the Batch Name ");
		String bname = sc.next();
		System.out.println("Enter the No  of the seat in batch : ");
		int noOfSeat = sc.nextInt();
		BatcheEntity bt=new BatcheEntity();	
		bt.setBartchName(bname);
		bt.setSeat(noOfSeat);
		
		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		boolean  res;
			try {
				res = admEtdao.adminAddBatch(bt);
				if (res ) {
					System.out.println(bt);

				} else {
					System.out.println("error Occured or corse Not Exist!");
				}
			
			} catch (SomeThingWrongException e) {
				e.printStackTrace();
			}
		
	}
	
	public void updateBatchDetail(Scanner sc) {
		
		System.out.println("Enter Batch Id");
		int batchId = sc.nextInt();
		System.out.println("Enter Batch Updated Seat");
		int seat = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();
		try {
			BatcheEntity res;
			try {
				res = admEtdao.admiBatchUpdate(seat, batchId);
				if (res != null) {
					System.out.println("------------------------------------------------------");
					System.out.println("Batch Update Succesfully");
					System.out.println(res);
					System.out.println("------------------------------------------------------");
				} else {
					System.out.println("error Occured");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}
	}



	public void addCousesToBatch(Scanner sc) {
	    System.out.println("-----------Course Add to Batch-----------");
		System.out.println("Enter Batch Id");
		int bid = sc.nextInt();
		System.out.println("Enter Course Id");
		int courseId = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			CourseEntity res;
			try {
				res = admEtdao.admiCourseToBatch(bid, courseId);
				if (res != null) {
					System.out.println("Course Update Succesfully");
					System.out.println(res);
				} else {
					System.out.println("error Occured");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public void addStudentToBatch(Scanner sc) {
		System.out.println("-----------Student Add to Batch-----------");
		System.out.println("Enter Batch Id");
		int bid = sc.nextInt();
		System.out.println("Enter Student RollNo");
		int courseId = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			StudentEntity res;
			try {
				res = admEtdao.admiStudentToBatch(bid, courseId);
				if (res != null) {
					System.out.println("Student added Succesfully");
					System.out.println(res);
				} else {
					System.out.println("error Occured");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

		
	}
	
	public void searchBatch(Scanner sc) {
		System.out.println("-----------View Batch Detail-----------");
		
		System.out.println("Enter BatchId");
		int batchid = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			BatcheEntity res;
			try {
				res = admEtdao.searchBatch(batchid);
				if (res != null) {
					System.out.println("Student Detail batchwise");
					System.out.println(res.getBartchName());
					System.out.println(res.getSeat());
					
					Set<CourseEntity> cs=res.getCourses();
					Set<StudentEntity> std=res.getStudents();
					System.out.println("Total No of Student :"+std.size());
					System.out.println("****Total No of courses*******");
					System.out.println("Total No of Student :"+cs.size());
					for(CourseEntity cc:cs) {
						System.out.println("course name  : "+cc.getCorseName()+ "Instructer :"+cc.getCourseInstructor());
					}
				} else {
					System.out.println("error Occured");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

		
	}


	
}
