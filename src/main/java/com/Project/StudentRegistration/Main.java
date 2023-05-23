package com.Project.StudentRegistration;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.Project.Student.Main_User.Administrator;
import com.Project.Student.Main_User.StudentMenu;

public class Main {
	public static void studentAactivity(Scanner sc) {

		System.out.println("------------------------------------------------------");
		System.out.println("              *********************                   ");
		System.out.println("------------------------------------------------------");
		System.out.println("Select -> 1 Update password ");
		System.out.println("Select -> 2 Update email Id");
		System.out.println("Select -> 3 See all Courses List");
		System.out.println("Select -> 4 Register For Course In Batch");
		System.out.println("Select -> 5 See all Enrolled Course");
		System.out.println("Select -> 6 Delete your Account");

		System.out.println("Select -> 7 Logout ");
		System.out.println("\n\n");
		System.out.println("Select -> 0  Exit from system");
		System.out.println("Select -> 99  Back To DashBoard");

		int studentActivity = sc.nextInt();

		StudentMenu stmenu = new StudentMenu();
		switch (studentActivity) {
		case 1:
			stmenu.UpdatePassword(sc);
			studentAactivity(sc);
			break;
		case 2:
			stmenu.UpdateEmail(sc);
			studentAactivity(sc);
			break;
		case 3:
			stmenu.viewAllStudentCourse();
			studentAactivity(sc);
			break;
		case 6:
			stmenu.deleteStudentAccout(sc);
			dashboardMenu();
			break;
		case 7:
			stmenu.Logout(sc);
			break;
		case 4:
			stmenu.registerForCourses(sc);
			studentAactivity(sc);
			break;
		case 5:
			stmenu.SeeEnrollCourses(sc);
			studentAactivity(sc);
			break;
		case 99:
			studentdashboard(sc);
			break;
		case 0:
			System.out.println("Thanks you Visit Again");
			break;

		default:
			System.out.println("Invalid Input Selection, Please Select the Valid Input");
			System.out.println("------------------------------------------------------");
			studentAactivity(sc);

		}

	}

	/* step-2.2, dashbord for student */
	private static void studentdashboard(Scanner sc) {
		int studentChoice = 0;
		System.out.println("------------------------------------------------------");
		System.out.println("                 Student Dashbroad                    ");
		System.out.println("------------------------------------------------------");
		System.out.println("Select -> 1 for Enrollment Student");
		System.out.println("Select -> 2 for Login Student");
		System.out.println("Select -> 3 for Back To Dashboard");

		System.out.println();
		System.out.println("Select-> 0 for Exit  ");
		studentChoice = sc.nextInt();
		try {

			StudentMenu stmenu = new StudentMenu();
			switch (studentChoice) {
			case 1:
				stmenu.enrollmentStudent(sc);
				System.out.println("------------------------------------------------------");
				System.out.println("******Student can Login Now**********");
				studentdashboard(sc);
				break;

			case 2:
				boolean autheticateStudent = stmenu.LoginStudent(sc);
				if (autheticateStudent) {
					studentAactivity(sc);

				} else {
					studentdashboard(sc);
				}
				break;
			case 3:
				dashboardMenu();

				break;

			case 4:
				System.out.println("Thanks you Visit Again");
				break;

			default:
				System.out.println("Invalid Input Selection, Please Select the Valid Input");
				System.out.println("------------------------------------------------------");

			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Entry!Please Select the from Given Input");
			System.out.println("------------------------------------------------------");
			studentdashboard(sc);
		}
		sc.close();

	}

	private static void administrationActivity(Scanner sc) {

		int administrationActivity = -1;
		System.out.println("----------------------------------------");
		System.out.println("Select -> 1: Add New Course");
		System.out.println("Select -> 2: update Course ");
		System.out.println("Select -> 3: View All Course");
		System.out.println("Select -> 4: Delete Course");
		System.out.println("Select -> 5: Search Course");
		System.out.println("Select -> 6: Create Batch");
		System.out.println("Select -> 7: update Batch Details");
		System.out.println("Select -> 8: Add Couses To Batch");
		System.out.println("Select -> 9: Search Batch ");
		System.out.println("Select -> 10: View All Student ");
		System.out.println("Select -> 11: Register Student To Batch ");

		System.out.println("Select -> 12: Logout");
		System.out.println("\n\n");

		System.out.println("Select -> 99: previous Page");
		System.out.println("Select -> 0: Exit from System");
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("Chose any of the Option above");

		administrationActivity = sc.nextInt();
		Administrator admMenu = new Administrator();
		switch (administrationActivity) {
		case 1:
			admMenu.AddNewCourse(sc);
			administrationActivity(sc);
			break;

		case 2:
			admMenu.updateCourseDetail(sc);
			administrationActivity(sc);
			break;
		case 3:
			admMenu.viewAllCourse();
			administrationActivity(sc);
			break;
		case 4:
			admMenu.deleteCourse(sc);
			administrationActivity(sc);
			break;
		case 5:
			admMenu.SearchCourse(sc);
			administrationActivity(sc);
			break;

		case 6:
			admMenu.createBatch(sc);
			administrationActivity(sc);
			break;
		case 7:
			admMenu.updateBatchDetail(sc);
			administrationActivity(sc);
			break;
		case 8:
			admMenu.addCousesToBatch(sc);
			administrationActivity(sc);
			break;
		case 9:
			admMenu.searchBatch(sc);
			administrationActivity(sc);
			break;
		case 10:
			admMenu.viewAllStudent(sc);
			administrationActivity(sc);
			break;
		case 11:
			admMenu.registerStudentToBatch(sc);
			administrationActivity(sc);
			break;

		case 12:
			admMenu.Logout();
			break;
		case 99:
			admindashboard(sc);
			break;

		case 0:
			System.out.println("Thanks you Visit Again");
			break;

		default:
			System.out.println("Invalid Input Selection, Please Select the Valid Input");
			System.out.println("------------------------------------------------------");
			administrationActivity(sc);

		}
	}

	private static void admindashboard(Scanner sc) {

		int adminChoice = 0;
		System.out.println("------------------------------------------------------");
		System.out.println("              Administer Dashbroad                    ");
		System.out.println();
		System.out.println("Select -> 1 for Login Admin");
		System.out.println("Select -> 2 for Back DashBoard");
		System.out.println();
		System.out.println("Select-> 0 for Exit  ");
		adminChoice = sc.nextInt();

		try {

			Administrator admMenu = new Administrator();
			switch (adminChoice) {
			case 1:
				boolean autheticateResult = admMenu.adminLoginAuthrticate(sc);
				if (autheticateResult) {
					administrationActivity(sc);
				} else {
					System.out.println("UserId or Password is Invalid");
					admindashboard(sc);
				}
				break;
			case 2:
				dashboardMenu();
				break;

			case 0:
				System.out.println("Thanks you Visit Again");
				break;

			default:
				System.out.println("Invalid Input Selection, Please Select the Valid Input");
				System.out.println("------------------------------------------------------");
				admindashboard(sc);
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Entry!Please Select the from Given Input");
			System.out.println("------------------------------------------------------");
			admindashboard(sc);
		}
		sc.close();

	}

	/* step-1, dashbord for both admin and student */

	public static void dashboardMenu() {

		Scanner sc = new Scanner(System.in);
		int dashboardChoice = 0;
		System.out.println("------------------------------------------------------");
		System.out.println("                  Dashbroad                           ");
		System.out.println();
		System.out.println("Select-> 1 for Admin DashBoard ");
		System.out.println("Select-> 2 for Student DashBoard");
		System.out.println("Select-> 3 for Exit  ");
		System.out.println("------------------------------------------------------");
		try {
			dashboardChoice = sc.nextInt();

			switch (dashboardChoice) {
			case 1:
				admindashboard(sc);
				break;

			case 2:
				studentdashboard(sc);
				break;

			case 3:
				System.out.println("Thanks you Visit Again");
				break;

			default:
				System.out.println("Invalid Input Selection, Please Select the Valid Input");
				System.out.println("------------------------------------------------------");
				dashboardMenu();
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input Entry!Please Select the from Given Input");
			System.out.println("------------------------------------------------------");
			dashboardMenu();
		}
		sc.close();
	}

	public static void main(String[] args) {

		System.out.println("| Welcome To Automated Student Registration System  |");
		System.out.println("----------------------------------------------------");
		System.out.println("\n");
		dashboardMenu();

	}

}
