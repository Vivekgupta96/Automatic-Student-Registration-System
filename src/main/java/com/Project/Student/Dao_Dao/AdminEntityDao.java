package com.Project.Student.Dao_Dao;

import java.util.List;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

public interface AdminEntityDao {

	String adminAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException;

	
	boolean admiNewCourseAdd(CourseEntity cs) throws SomeThingWrongException;

	CourseEntity admiCourseUpdate(double cFee, int courseId) throws SomeThingWrongException, NoRecordFoundException;

	List<CourseEntity> admiViewAllCourse()throws SomeThingWrongException, NoRecordFoundException;

	boolean admiCourseDelete(int courseId)throws SomeThingWrongException, NoRecordFoundException;

	CourseEntity adminCourseSerch(int courseId)throws SomeThingWrongException, NoRecordFoundException;

	boolean adminAddBatch(BatcheEntity bt)throws SomeThingWrongException;

	BatcheEntity admiBatchUpdate(int seat, int batchId) throws SomeThingWrongException, NoRecordFoundException;

	CourseEntity admiCourseToBatch(int bid, int courseId)throws SomeThingWrongException, NoRecordFoundException;

	StudentEntity admiStudentToBatch(int bid, int studentrollno)throws SomeThingWrongException, NoRecordFoundException;

	BatcheEntity searchBatch(int Batchid)throws SomeThingWrongException, NoRecordFoundException;

	List<StudentEntity> admiViewAllStudent()throws SomeThingWrongException, NoRecordFoundException;

	void registerStudentBatch(int studentid, int batchid)throws SomeThingWrongException, NoRecordFoundException;


	

	
}
