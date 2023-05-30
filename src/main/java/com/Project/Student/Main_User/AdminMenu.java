package com.Project.Student.Main_User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.Project.Student.Dao_Dao.AdminDao;
import com.Project.Student.Dao_Dao.AdminDaoImpl;
import com.Project.Student.Dao_beam.Batche;
import com.Project.Student.Dao_beam.Course;
import com.Project.Student.Dao_beam.CourseDto;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;
import com.Project.StudentRegistration.Main;

public class AdminMenu {

	public boolean adminLoginAuthrticate(Scanner sc) {

		boolean message = false;
		System.out.println("------------------------------------------------------");
		System.out.println("Welcome TO Admin Login ,Please Fill Requred Details");
		System.out.println("Enter your UserName");
		String userId = sc.next();
		System.out.println("Enter Your Password ");
		String pass = sc.next();
		System.out.println("------------------------------------------------------");

		AdminDao admEtdao = new AdminDaoImpl();

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

			Course cs = new Course();
			cs.setCorseName(cName);
			cs.setDescription(description);
			cs.setDuration(cduration);
			cs.setFee(cFee);
			cs.setCourseInstructor(InsName);

			AdminDao admEtdao = new AdminDaoImpl();

			try {
				boolean res = admEtdao.admiNewCourseAdd(cs);
				if (res) {
					System.out.println("Course Added Succesfully");
					System.out.println(cs);
				}
			} catch (SomeThingWrongException e) {

				System.out.println("course Alresy existed:   "+cName);
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
		
		System.out.println("Enter Updated course fee");
		double cFee = sc.nextDouble();
		
		System.out.println("Enter updated course Duration");
		int cduration = sc.nextInt();
		
		System.out.println("Enter updated course Intructor Name");
		String cInstructer = sc.next();
		
		CourseDto cdto= new CourseDto();
		cdto.setDuration(cduration);
		cdto.setFee(cFee);
		cdto.setInstructerName(cInstructer);
		
		AdminDao admEtdao = new AdminDaoImpl();
		try {
			Course res;
			try {
				res = admEtdao.admiCourseUpdate(cdto, courseId);
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

		AdminDao admEtdao = new AdminDaoImpl();
		try {
			List<Course> res;
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
		
		AdminDao admEtdao = new AdminDaoImpl();
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
				
					System.out.println("Course not Exit!");
			}

		} catch (SomeThingWrongException e) {

		}

	}

	public void SearchCourse(Scanner sc) {

		System.out.println("Enter Course Id to Search Course");
		int courseId = sc.nextInt();
		
		AdminDao admEtdao = new AdminDaoImpl();
		Course res;
		try {
			res = admEtdao.adminCourseSerch(courseId);
			if (res != null) {
				System.out.println(res);

			} else {
				System.out.println("error Occured or corse Not Exist!");
			}
		} catch (SomeThingWrongException | NoRecordFoundException e) {

			System.out.println("Error Occured or Invalid course Id");
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
		
		Batche bt = new Batche();
		bt.setBartchName(bname);
		bt.setStartDate(startDate);
		bt.setEndDate(bendDatename);
		bt.setSeat(noOfSeat);

		AdminDao admEtdao = new AdminDaoImpl();

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

		AdminDao admEtdao = new AdminDaoImpl();
		try {
			Batche res;
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

		AdminDao admEtdao = new AdminDaoImpl();
		try {
			Course res;
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

		AdminDao admEtdao = new AdminDaoImpl();

		try {
			Batche res;
			try {
				res = admEtdao.searchBatch(batchid);
				if (res != null) {
					System.out.println("****  Batch Detail   ********");
					System.out.println("BatchName  :" + res.getBartchName());
					System.out.println("Total Seats   :" + res.getSeat());
					System.out.println("Course Name   :" + res.getCourses().getCorseName());
					System.out.println("Enrolled Student   :" + res.getCourses().getStudents().size());
				} else {
					System.out.println("error Occured");
				}
			} catch (NoRecordFoundException e) {

				System.out.println("Batch not exist!");
			}

		} catch (SomeThingWrongException e) {

			e.printStackTrace();
		}

	}

	public void viewAllStudent(Scanner sc) {
		AdminDao admEtdao = new AdminDaoImpl();
		try {
			List<Student> res;
			try {
				res = admEtdao.admiViewAllStudent();
				if (res != null) {
					System.out.println("All Student Detail");
					for(Student ss:res) {
						System.out.println("-------------------------------------");
						System.out.println("Student UserId :"+ss.getStudentUserId());
						System.out.println("Student Name :"+ss.getfName()+" "+ss.getlName());
						System.out.println("Student Roll No :"+ss.getRoll());
						 Set<Course> couseEnrollByStd=ss.getCourses();
						 for(Course cc:couseEnrollByStd) {
							 System.out.println("course Name :"+cc.getCorseName());
						 }
					}
					
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
		System.out.println("-------Register Student In Batch With Course----------");

		System.out.println("Enter Student Id");
		int Studentid = sc.nextInt();
		System.out.println("Enter BatchId");
		int batchid = sc.nextInt();
		AdminDao admEtdao = new AdminDaoImpl();
		try {
			admEtdao.registerStudentBatch(Studentid,batchid);
			System.out.println("Student registered For Batch Succesfully");
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		
	}

}
