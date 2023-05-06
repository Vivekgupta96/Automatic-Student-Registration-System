package com.Project.Student.Dao_Dao;

import java.util.List;

import com.Project.Student.Dao_beam.AdminEntity;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

public interface AdminEntityDao {

	boolean addAdminToDb(AdminEntity admin) throws SomeThingWrongException;

	String adminAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException;

	boolean admiNewCourseAdd(CourseEntity cs) throws SomeThingWrongException;

	CourseEntity admiCourseUpdate(double cFee, int courseId) throws SomeThingWrongException, NoRecordFoundException;

	List<CourseEntity> admiCourseUpdate()throws SomeThingWrongException, NoRecordFoundException;

	boolean admiCourseDelete(int courseId)throws SomeThingWrongException, NoRecordFoundException;

	CourseEntity adminCourseSerch(int courseId)throws SomeThingWrongException, NoRecordFoundException;

	boolean adminAddBatch(BatcheEntity bt)throws SomeThingWrongException;

	BatcheEntity admiBatchUpdate(int seat, int batchId)throws SomeThingWrongException, NoRecordFoundException;

	
}
