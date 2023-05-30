package com.Project.Student.Dao_Dao;

import java.util.List;
import com.Project.Student.Dao_beam.Batche;
import com.Project.Student.Dao_beam.Course;
import com.Project.Student.Dao_beam.CourseDto;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

public interface AdminDao {

	String adminAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException;

	
	boolean admiNewCourseAdd(Course cs) throws SomeThingWrongException;

	Course admiCourseUpdate(CourseDto cdto, int courseId) throws SomeThingWrongException, NoRecordFoundException;

	List<Course> admiViewAllCourse()throws SomeThingWrongException, NoRecordFoundException;

	boolean admiCourseDelete(int courseId)throws SomeThingWrongException, NoRecordFoundException;

	Course adminCourseSerch(int courseId)throws SomeThingWrongException, NoRecordFoundException;

	boolean adminAddBatch(Batche bt)throws SomeThingWrongException;

	Batche admiBatchUpdate(int seat, int batchId) throws SomeThingWrongException, NoRecordFoundException;

	Course admiCourseToBatch(int bid, int courseId)throws SomeThingWrongException, NoRecordFoundException;

	Student admiStudentToBatch(int bid, int studentrollno)throws SomeThingWrongException, NoRecordFoundException;

	Batche searchBatch(int Batchid)throws SomeThingWrongException, NoRecordFoundException;

	List<Student> admiViewAllStudent()throws SomeThingWrongException, NoRecordFoundException;

	void registerStudentBatch(int studentid, int batchid)throws SomeThingWrongException, NoRecordFoundException;


	

	
}
