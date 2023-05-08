package com.Project.Student.Dao_Dao;

import java.util.List;

import com.Project.Student.Dao_Eutil.EMUtils;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.LoggedInUserId;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class StudentEntityDaoImpl implements StudentEntityDao {

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
	public String studentAuthToDb(String userId, String pass)
			throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = null;
		String  authstudent = null;
		try {
			em = EMUtils.getConnection();

			String studentAuth = "SELECT s.studentUserId FROM  Student s WHERE  s.studentUserId = :userId AND s.password = :password AND isDeactivate=0";
			Query qq = em.createQuery(studentAuth, Student.class);
			qq.setParameter("userId", userId);
			qq.setParameter("password", pass);
			List<String> listInt = (List<String>)qq.getResultList();
			if(listInt.size() == 0) {
				throw new SomeThingWrongException("password or userId are Incorrect");
			}
			LoggedInUserId.studentloggedInUserId = listInt.get(0);
			authstudent=listInt.get(0);;
	
		} catch (PersistenceException px) {

			//throw new SomeThingWrongException("Error occured");
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return authstudent;
	}

	@Override
	public String studentUpdatePassword(String userid,String oldpass, String newpass)
			throws SomeThingWrongException, NoRecordFoundException {
		String res = null;
		EntityManager em = EMUtils.getConnection();

		try {
			Query query = em.createQuery("SELECT count(s) FROM Student s WHERE password = :oldPassword AND studentUserId = :id");
			query.setParameter("oldPassword", oldpass);
			query.setParameter("id",userid );
			Long userCount = (Long) query.getSingleResult();
			if(userCount == 0) {
				
				throw new SomeThingWrongException("Invalid Old Password");
			}
			em.getTransaction().begin();
			Student cs = em.find(Student.class,userid );
			System.out.println("before commit2");
			if (cs != null) {

				cs.setPassword(newpass);
				em.persist(cs);
				em.getTransaction().commit();
				res = "Password Updated";
			} else {
				throw new NoRecordFoundException("userId does Not Exits!");
			}
			System.out.println("before commit3");
		} catch (PersistenceException px) {
			//throw new SomeThingWrongException("Unable to process try letter");
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return res;
	}

	@Override
	public String studentEmailUpdate(String userId, String newEmail)
			throws SomeThingWrongException, NoRecordFoundException {
		String res = null;
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			Student cs = em.find(Student.class, userId);
			if (cs != null) {

				cs.setEmail(newEmail);
				em.persist(cs);
				em.getTransaction().commit();
				res = newEmail;
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
	public String deleteAccout(String userId) throws SomeThingWrongException, NoRecordFoundException {
		String res = null;
		EntityManager em = EMUtils.getConnection();
		try {
			em.getTransaction().begin();
			Student cs = em.find(Student.class, userId);
			if (cs != null) {

				cs.setIsDeactivate(1);
				em.persist(cs);
				em.getTransaction().commit();
				res = "Succesfully";
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

}
