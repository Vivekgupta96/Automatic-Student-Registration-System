package com.Project.Student.Dao_Dao;

import java.util.List;
import java.util.Set;

import com.Project.Student.Dao_beam.Course;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

public interface StudentDao {

	public boolean addStudentToDb(Student s) throws SomeThingWrongException;

	public String  studentAuthToDb(String userId, String pass)throws SomeThingWrongException,NoRecordFoundException;

	public String studentUpdatePassword(String oldpass, String newpass)throws SomeThingWrongException,NoRecordFoundException;

	public String  studentEmailUpdate(Student  et)throws SomeThingWrongException,NoRecordFoundException;

	public List<Course> viewAllCourses()throws SomeThingWrongException,NoRecordFoundException;

	public String deleteAccout(int roolNo)throws SomeThingWrongException,NoRecordFoundException;

	public Set<Course> StudentEnrollCourses(int studentRollNo)throws SomeThingWrongException,NoRecordFoundException;
	

}
