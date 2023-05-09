package com.Project.Student.Dao_Dao;

import java.util.List;

import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

public interface StudentEntityDao {

	boolean addStudentToDb(StudentEntity s) throws SomeThingWrongException;

	void  studentAuthToDb(String userId, String pass)throws SomeThingWrongException,NoRecordFoundException;

	String studentUpdatePassword(int userId,String oldpass, String newpass)throws SomeThingWrongException,NoRecordFoundException;

	String  studentEmailUpdate(StudentEntity  et)throws SomeThingWrongException,NoRecordFoundException;

	List<CourseEntity> viewAllCourses()throws SomeThingWrongException,NoRecordFoundException;

	String deleteAccout(int roolNo)throws SomeThingWrongException,NoRecordFoundException;

	

}
