package com.Project.Student.Service;

import java.util.Random;

public class AutoGenerateUserIDs {

	public  static String userId(String name) {

		String input = name; // example input string

		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%04d", random.nextInt(10000)));

		String randomNumber = sb.toString();
		String result = input.substring(0, 4) + randomNumber;
		
		return result.toUpperCase();

	}
	public  static String StudentuserId() {
		
		String input = "ASRS"; // example input string
		
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%04d", random.nextInt(10000)));
		
		String randomNumber = sb.toString();
		String result = input.substring(0, 4) + randomNumber;
		
		return result.toUpperCase();
		
	}
	
	

}
