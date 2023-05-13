package com.Project.Student.Dao_Dao;

import java.util.*;

import com.Project.Student.Dao_Eutil.EMUtils;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.LoggedInUserId;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class StudentEntityDaoImpl implements StudentEntityDao {

	@Override
	public boolean addStudentToDb(StudentEntity s) throws SomeThingWrongException {

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
	public void studentAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = null;
		Integer authstudentRoll = null;
		try {
			em = EMUtils.getConnection();

			String studentAuth = "SELECT s.roll FROM  StudentEntity s WHERE  s.studentUserId = :userId AND s.password = :password AND isDeactivate=0";
			Query qq = em.createQuery(studentAuth, StudentEntity.class);
			qq.setParameter("userId", userId);
			qq.setParameter("password", pass);
			List<Integer> listInt = (List<Integer>) qq.getResultList();
			if (listInt.size() == 0) {
				throw new SomeThingWrongException("password or userId are Incorrect");
			}
			LoggedInUserId.studentloggedInUserId = listInt.get(0);

		} catch (PersistenceException px) {

			// throw new SomeThingWrongException("Error occured");
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}

	}

	@Override
	public String studentUpdatePassword(int rollNo, String oldpass, String newpass)
			throws SomeThingWrongException, NoRecordFoundException {
		String res = null;
		EntityManager em = EMUtils.getConnection();

		try {
			Query query = em
					.createQuery("SELECT count(s) FROM StudentEntity s WHERE password = :oldPassword AND roll = :id");
			query.setParameter("oldPassword", oldpass);
			query.setParameter("id", rollNo);
			Long userCount = (Long) query.getSingleResult();
			if (userCount == 0) {

				throw new SomeThingWrongException("Invalid Old Password");
			}
			em.getTransaction().begin();
			StudentEntity cs = em.find(StudentEntity.class, rollNo);
			if (cs != null) {

				cs.setPassword(newpass);
				em.persist(cs);
				em.getTransaction().commit();
				res = "Password Updated";
			} else {
				throw new NoRecordFoundException("userId does Not Exits!");
			}

		} catch (PersistenceException px) {
			// throw new SomeThingWrongException("Unable to process try letter");
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return res;
	}

	@Override
	public String studentEmailUpdate(StudentEntity st) throws SomeThingWrongException, NoRecordFoundException {

		String res = null;
		EntityManager em = EMUtils.getConnection();

		// System.out.println("1");
		try {
			em.getTransaction().begin();

			StudentEntity cs = em.find(StudentEntity.class, st.getRoll());

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
	public List<CourseEntity> viewAllCourses() throws SomeThingWrongException, NoRecordFoundException {
		List<CourseEntity> res = null;
		EntityManager em = EMUtils.getConnection();
		try {

			String studentQry = "FROM CourseEntity a ";
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
			StudentEntity cs = em.find(StudentEntity.class, rollno);
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
	public void registerCourses(int studentid, int courseid) throws SomeThingWrongException, NoRecordFoundException {

		EntityManager em = EMUtils.getConnection();
		try {
			em.getTransaction().begin();
			StudentEntity se = em.find(StudentEntity.class, studentid);
			CourseEntity ce = em.find(CourseEntity.class, courseid);

			if (ce != null && se != null) {

				ce.getStudents().add(se);
				se.getCourses().add(ce);
				em.persist(ce);
				em.persist(se);
				em.getTransaction().commit();

			} else {
				em.getTransaction().rollback();
				throw new NoRecordFoundException("student does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {
			em.close();
		}

	}

	@Override
	public void StudentEnrollCourses(int studentRollNo) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = EMUtils.getConnection();
		
		
		
		try {
			em.getTransaction().begin();

			StudentEntity se = em.find(StudentEntity.class, studentRollNo);

			if (se != null) {
				
				Set<CourseEntity>  res =(Set<CourseEntity>) se.getCourses();
				
				for(CourseEntity cc:res) {
					
					System.out.println(cc.getCorseName());
					
				}

			} else {
				em.getTransaction().rollback();
				throw new NoRecordFoundException("student does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {
			em.close();
		}

	}

}
