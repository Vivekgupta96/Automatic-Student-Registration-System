package com.Project.Student.Dao_Dao;

import java.time.LocalDate;
import java.util.*;

import com.Project.Student.Dao_Eutil.EMUtils;
import com.Project.Student.Dao_beam.Batche;
import com.Project.Student.Dao_beam.Course;
import com.Project.Student.Dao_beam.LoggedInUserId;
import com.Project.Student.Dao_beam.StudentReg;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class StudentDaoImpl implements StudentDao {

	@Override
	public boolean addStudentToDb(Student s) throws SomeThingWrongException {

		boolean Registudent = false;
		EntityManager em = EMUtils.getConnection();
		em.getTransaction().begin();

		try {
			em.persist(s);
			Registudent = true;

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.getTransaction().commit();
			em.close();
		}
		return Registudent;
	}

	@Override
	public String studentAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtils.getConnection();

			String studentAuth = "SELECT s from  Student s WHERE  s.studentUserId = :userId AND s.password = :password AND isDeactivate= 0";
			Query qq = em.createQuery(studentAuth, Student.class);
			qq.setParameter("userId", userId);
			qq.setParameter("password", pass);
			 Student singleResult = (Student)qq.getSingleResult();
			
			if (singleResult == null) {
				throw new SomeThingWrongException("password or userId or Acitve Not Exits!");
			}
			LoggedInUserId.studentloggedRollNo=singleResult.getRoll();
			return singleResult.getfName()+" "+singleResult.getlName();
			
		} catch (PersistenceException px) {

			throw new SomeThingWrongException("Account Not found");
		} finally {

			em.close();
			
		}
	}

	@Override
	public String studentUpdatePassword( String oldpass, String newpass)
			throws SomeThingWrongException, NoRecordFoundException {
		String res = null;
		EntityManager em = EMUtils.getConnection();

		try {
			Query query = em
					.createQuery("SELECT count(s) FROM Student s WHERE password = :oldPassword AND roll = :id");
			query.setParameter("oldPassword", oldpass);
			query.setParameter("id", LoggedInUserId.studentloggedRollNo);
			Long userCount = (Long) query.getSingleResult();
			if (userCount == 0) {

				throw new SomeThingWrongException("Invalid Old Password");
			}
			em.getTransaction().begin();
			Student cs = em.find(Student.class, LoggedInUserId.studentloggedRollNo);
			if (cs != null) {

				cs.setPassword(newpass);
				em.persist(cs);
				em.getTransaction().commit();
				res = "Password Updated";
			} else {
				throw new NoRecordFoundException("userId does Not Exits!");
			}

		} catch (PersistenceException px) {
			 throw new SomeThingWrongException("Unable to process try letter");
			
		} finally {

			em.close();
		}
		return res;
	}

	@Override
	public String studentEmailUpdate(Student st) throws SomeThingWrongException, NoRecordFoundException {

		String res = null;
		EntityManager em = EMUtils.getConnection();
		try {
			em.getTransaction().begin();

			Student cs = em.find(Student.class, LoggedInUserId.studentloggedRollNo);
			if (cs == null) {
				throw new NoRecordFoundException("Roll no does Not Exits!");
			} else {
				cs.setEmail(st.getEmail());
				em.persist(cs);
				em.getTransaction().commit();
				res = cs.getEmail();
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return res;
	}

	@Override
	public List<Course> viewAllCourses() throws SomeThingWrongException, NoRecordFoundException {
		List<Course> res = null;
		EntityManager em = null;
		try {
			em = EMUtils.getConnection();
			String studentQry = "FROM Course a ";
			Query qq = em.createQuery(studentQry);
			res = qq.getResultList();

		} catch (PersistenceException px) {

			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}

		return res;
	}

	@Override
	public String deleteAccout(int rollno) throws SomeThingWrongException, NoRecordFoundException {
		String res = null;
		EntityManager em = EMUtils.getConnection();
		try {
			em.getTransaction().begin();
			Student cs = em.find(Student.class, rollno);
			if (cs != null) {

				cs.setIsDeactivate(1);
				em.persist(cs);
				em.getTransaction().commit();
				res = "Accout Succesfully deleted";
			} else {
				throw new NoRecordFoundException("userId does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return res;
	}


	@Override
	public Set<Course> StudentEnrollCourses(int studentRollNo) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em=null;
	
		try {
			em = EMUtils.getConnection();
			Student st = em.find(Student.class, studentRollNo);
			if (st==null) {
				throw new NoRecordFoundException("Student does Not Exits!");	
			}
			return (Set<Course>) st.getCourses();
	
		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {
			em.close();
		}
		
	}
	
	



}
