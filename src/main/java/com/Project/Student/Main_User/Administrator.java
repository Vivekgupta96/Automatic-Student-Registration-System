package com.Project.Student.Main_User;

import java.util.InputMismatchException;
import java.util.*;
import java.util.Scanner;

import com.Project.Student.Dao_Dao.AdminEntityDao;
import com.Project.Student.Dao_Dao.AdminEntityDaoImpl;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;
import com.Project.Student.Service.AutoGenerateAdminUserID;
import com.Project.StudentRegistration.Main;

public class Administrator {


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
			
		}

		return message;
	}

	public void AddNewCourse(Scanner sc) {

		try {
			System.out.println("Enter Course Name");
			String cName = sc.next();

			System.out.println("Enter Course Duration(IN Months)");
			int cduration = sc.nextInt();

			System.out.println("Enter Course Deacription (FullTime/PartTime)");
			String description = sc.next();

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
			AddNewCourse(sc);
		}

	}

	public void updateCourseDetail(Scanner sc) {

		System.out.println("Enter Course Id");
		int courseId = sc.nextInt();
		System.out.println("Enter course fee");
		double cFee = sc.nextDouble();
		
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

		System.out.println("Enter the Batch details");
		System.out.println("------------------------");
		System.out.println("Enter the Batch Name:  ");
		String bname = sc.next();
		System.out.println("Enter the Batch Start Date: ");
		String startDate = sc.next();
		System.out.println("Enter the Batch End Date:  ");
		String bendDatename = sc.next();
		
		System.out.println("Enter the No  of the seat in batch : ");
		int noOfSeat = sc.nextInt();
		
		BatcheEntity bt = new BatcheEntity();
		bt.setBartchName(bname);
		bt.setStartDate(startDate);
		bt.setEndDate(bendDatename);
		bt.setSeat(noOfSeat);

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		boolean res;
		try {
			res = admEtdao.adminAddBatch(bt);
			if (res) {
				System.out.println(bt);

			} else {
				System.out.println("Error Occured !");
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
					System.out.println("Course Added to Batch Succesfully");
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
					System.out.println("BatchName  :" + res.getBartchName());
					System.out.println("Total Seats   :" + res.getSeat());
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

	public void viewAllStudent(Scanner sc) {
		AdminEntityDao admEtdao = new AdminEntityDaoImpl();
		try {
			List<StudentEntity> res;
			try {
				res = admEtdao.admiViewAllStudent();
				if (res != null) {
					System.out.println("All Student Detail");
					res.forEach(System.out::println);
				} else {
					System.out.println("No Student Avalable");
				}
			} catch (NoRecordFoundException e) {

				e.printStackTrace();
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

		
	}

	public void registerStudentToBatch(Scanner sc) {
		System.out.println("-----------View Batch Detail-----------");

		System.out.println("Enter Student Id");
		int Studentid = sc.nextInt();
		System.out.println("Enter BatchId");
		int batchid = sc.nextInt();

		AdminEntityDao admEtdao = new AdminEntityDaoImpl();

		try {
			admEtdao.registerStudentBatch(Studentid,batchid);
			System.out.println("Student registered For Batch Succesfully");
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		
	}

}
