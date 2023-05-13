package com.Project.Student.Dao_Dao;

import com.Project.Student.Dao_beam.AdminEntity;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

import java.util.List;

import com.Project.Student.Dao_Eutil.EMUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminEntityDaoImpl implements AdminEntityDao {

	@Override
	public boolean addAdminToDb(AdminEntity adminDetailReg) throws SomeThingWrongException {

		boolean Regiadmin = false;
		EntityManager em = EMUtils.getConnection();
		em.getTransaction().begin();

		try {
			em.persist(adminDetailReg);
			Regiadmin = true;

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.getTransaction().commit();
			em.close();
		}
		return Regiadmin;
	}

	@Override
	public String adminAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException {

		String authadmin = null;
		EntityManager em = EMUtils.getConnection();
		try {

			String adminAuth = "SELECT a.adminUserId FROM AdminEntity a WHERE a.adminUserId = :userId AND a.adminPassword = :password";
			Query qq = em.createQuery(adminAuth, AdminEntity.class);
			qq.setParameter("userId", userId);
			qq.setParameter("password", pass);

			List<String> list = (List<String>) qq.getResultList();
			if (list.size() == 0) {

				throw new SomeThingWrongException("userId or Password Are Incorrect");
			}
			authadmin = list.get(0);

		} catch (PersistenceException px) {

			throw new SomeThingWrongException("Unable to process request, try again later");
		} finally {

			em.close();
		}
		return authadmin;

	}

	@Override
	public boolean admiNewCourseAdd(CourseEntity course) throws SomeThingWrongException {
		boolean status = false;
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT count(c) FROM CourseEntity c WHERE c.corseName = :coursename");
			query.setParameter("coursename", course.getCorseName());
			if ((Long) query.getSingleResult() > 0) {
				// you are here means company with given name exists so throw exceptions
				throw new SomeThingWrongException("course already exits:" + course.getCorseName());
			}

			em.persist(course);
			status = true;

		} catch (PersistenceException px) {
			throw new SomeThingWrongException("Unable to process request, try again later");
		} finally {

			em.getTransaction().commit();
			em.close();
		}

		return status;
	}

	@Override
	public CourseEntity admiCourseUpdate(double cFee, int courseId)
			throws SomeThingWrongException, NoRecordFoundException {
		CourseEntity status = null;
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			CourseEntity cs = em.find(CourseEntity.class, courseId);
			if (cs != null) {

				cs.setFee(cFee);
				em.persist(cs);
				em.getTransaction().commit();
				status = cs;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return status;
	}

	@Override
	public List<CourseEntity> admiViewAllCourse() throws SomeThingWrongException, NoRecordFoundException {
		List<CourseEntity> res = null;
		EntityManager em = EMUtils.getConnection();
		try {

			String adminAuth = "SELECT a FROM CourseEntity a ";
			Query qq = em.createQuery(adminAuth);
			res = qq.getResultList();

		} catch (PersistenceException px) {

			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}

		return res;
	}

	@Override
	public boolean admiCourseDelete(int courseId) throws SomeThingWrongException, NoRecordFoundException {
		boolean status = false;
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			CourseEntity cs = em.find(CourseEntity.class, courseId);
			if (cs != null) {
				em.remove(cs);
				em.getTransaction().commit();
				status = true;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return status;
	}

	@Override
	public CourseEntity adminCourseSerch(int courseId) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = EMUtils.getConnection();
		CourseEntity res = null;

		try {
			CourseEntity cs = em.find(CourseEntity.class, courseId);
			if (cs != null) {
				res = cs;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return res;
	}

	@Override
	public boolean adminAddBatch(BatcheEntity bt) throws SomeThingWrongException {
		
		boolean b_status=false;
		EntityManager em=null;
		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			em.persist(bt);
			b_status = true;

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.getTransaction().commit();
			em.close();
		}
		return b_status;
		
	}

	@Override
	public BatcheEntity admiBatchUpdate(int seat, int batchId) throws SomeThingWrongException, NoRecordFoundException {
		
		BatcheEntity res=null;
		EntityManager em=null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			BatcheEntity cs = em.find(BatcheEntity.class, batchId);
			if (cs != null) {

				cs.setSeat(seat);
				em.persist(cs);
				em.getTransaction().commit();
				res=cs;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return res;
		
	}

	
	@Override
	public CourseEntity admiCourseToBatch(int bid, int courseId)

	
			throws SomeThingWrongException, NoRecordFoundException {
		
		CourseEntity status = null;
		
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			CourseEntity cs = em.find(CourseEntity.class, courseId);
			
			BatcheEntity bt=em.find(BatcheEntity.class, bid);
			if (cs != null&&bt!=null) {

				bt.getCourses().add(cs);
				em.persist(bt);
				em.getTransaction().commit();
				status = cs;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return status;
	}

	
	@Override
	public StudentEntity admiStudentToBatch(int bid, int studentrollno)
			throws SomeThingWrongException, NoRecordFoundException {
		StudentEntity status = null;
		
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			
			StudentEntity cs = em.find(StudentEntity.class, studentrollno);
			BatcheEntity bt=em.find(BatcheEntity.class, bid);
			
			if (cs != null&&bt!=null) {

				bt.getStudents().add(cs);
				cs.setBatch(bt);
				em.persist(bt);
				em.persist(cs);
				em.getTransaction().commit();
				status = cs;
			} else {
				throw new NoRecordFoundException("stuent or Batches  Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return status;
		
	
	}

	
	
//	**************************************************************************************************************

	@Override
	public
	BatcheEntity searchBatch(int Batchid)throws SomeThingWrongException, NoRecordFoundException {
		BatcheEntity status = null;
		
		EntityManager em=null;

		try {
			em = EMUtils.getConnection();
			BatcheEntity cs = em.find(BatcheEntity.class, Batchid);
			if (cs != null) {

				status = cs;
			} else {
				throw new NoRecordFoundException("stuent Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return status;
	}
	
	
}
