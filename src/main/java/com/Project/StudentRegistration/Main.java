package com.Project.StudentRegistration;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.Project.Student.Main_User.Administrator;

public class Main {

	/* step-2.2, dashbord for student */
	private static void studentdashboard(Scanner sc) {
		int studentChoice = 0;
		System.out.println("------------------------------------------------------");
		System.out.println("                 Student Dashbroad                      ");
		System.out.println("------------------------------------------------------");
		System.out.println("Select -> 1 for Register Student");
		System.out.println("Select -> 2 for Login Student");
		System.out.println("Select -> 3 for Back To Dashboard");

		System.out.println();
		System.out.println("Select-> 0 for Exit  ");
		try {
			studentChoice = sc.nextInt();

			switch (studentChoice) {
			case 1:

				break;

			case 2:

				break;
			case 3:

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
			admindashboard(sc);
		}
		sc.close();

	}

	/* step-2.1.1, dashbord for admin */
	private static void administrationActivity(Scanner sc) {

		int administrationActivity = -1;
		System.out.println("Select -> 1: Add new Course");
		System.out.println("Select -> 2: update course detail");
		System.out.println("Select -> 3: view All Course");
		System.out.println("Select -> 4: Delete Course");
		System.out.println("Select -> 5: Search Course");

		System.out.println("Select -> 6: Create Batch");
		System.out.println("Select -> 7: update  Detail of batch");
		System.out.println("Select -> 8: update Seat in batch");

//		6. Search for information about batches (by batch name, by start date range, by end
//		date
//		range, by start and end date. By course name of the batch)
		System.out.println("Select -> #: Search Student by Detail");
		System.out.println("Select -> #: View List Of  Student in Batch");
		System.out.println("\n\n");

		System.out.println("Select -> 0: Exit from System");
		System.out.println("Select -> 00: Exit from System");
		System.out.println();
		System.out.println("Chose anyOf the Option above");

		Administrator admMenu = new Administrator();
		administrationActivity = sc.nextInt();
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
			admMenu.CreateBatch(sc);
			administrationActivity(sc);
			break;
		case 7: admMenu.updateBatchDetail(sc);
			administrationActivity(sc);
			break;
		case 8:
			
			//administrationActivity(sc);
			break;
		case 9:
		
			//administrationActivity(sc);
			break;
		case 10:
			
			//administrationActivity(sc);
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

	
	/* step-2.1, dashbord for admin */
	private static void admindashboard(Scanner sc) {
		int adminChoice = 0;
		System.out.println("------------------------------------------------------");
		System.out.println("              Administer Dashbroad                    ");
		System.out.println("------------------------------------------------------");
		System.out.println("Select -> 1 for Register Admin");
		System.out.println("Select -> 2 for Login Admin");
		System.out.println("Select -> 3 for Back DashBoard");

		System.out.println();
		System.out.println("Select-> 0 for Exit  ");
		Administrator admMenu = new Administrator();
		try {
			adminChoice = sc.nextInt();

			switch (adminChoice) {
			case 1:
				admMenu.adminRegister(sc);
				System.out.println("------------------------------------------------------");
				System.out.println("You can Login Now");
				admindashboard(sc);
				break;

			case 2:
				boolean autheticateResult = admMenu.adminLoginAuthrticate(sc);
				if (autheticateResult) {
					System.out.println("user Validate");
					administrationActivity(sc);
				} else {
					System.out.println("UserId or Password is Invalid");
					admindashboard(sc);
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
		System.out.println("------------------------------------------------------");
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

		System.out.println("----------------------------------------------------");
		System.out.println("| Welcome To Automated Student Registration System  |");
		System.out.println("----------------------------------------------------");
		System.out.println("\n\n");
		dashboardMenu();

	}

}
