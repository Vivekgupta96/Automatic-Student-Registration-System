package com.Project.Student.Dao_Dao;

import java.util.List;

import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

public interface StudentEntityDao {

	boolean addStudentToDb(Student s) throws SomeThingWrongException;

	String  studentAuthToDb(String userId, String pass)throws SomeThingWrongException,NoRecordFoundException;

	String studentUpdatePassword(String userId,String oldpass, String newpass)throws SomeThingWrongException,NoRecordFoundException;

	String studentEmailUpdate(String userId, String newEmail)throws SomeThingWrongException,NoRecordFoundException;

	List<CourseEntity> viewAllCourses()throws SomeThingWrongException,NoRecordFoundException;

	String deleteAccout(String userId)throws SomeThingWrongException,NoRecordFoundException;

	

}
