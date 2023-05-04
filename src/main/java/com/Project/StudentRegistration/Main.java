package com.Project.StudentRegistration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	private static void studentdashboard(Scanner sc) {
		// TODO Auto-generated method stub

	}

	private static void admindashboard(Scanner sc) {
		System.out.println("Select -> 1 for Register Admin");
		System.out.println("Select -> 2 for Login Adminr");
		System.out.println("Select -> 3 for Back To Prevoius Page");
		System.out.println("Select-> 3 for Exit  ");

	}

	public static void dashboardMenu() {

		Scanner sc = new Scanner(System.in);
		int panelChoice = 0;
		System.out.println("Select-> 1 for Admin DashBoard ");
		System.out.println("Select-> 2 for Student DashBoard");
		System.out.println("Select-> 3 for Exit  ");
		System.out.println("------------------------------------------------------");
		try {
			panelChoice = sc.nextInt();

			switch (panelChoice) {
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
		dashboardMenu();

	}

}
