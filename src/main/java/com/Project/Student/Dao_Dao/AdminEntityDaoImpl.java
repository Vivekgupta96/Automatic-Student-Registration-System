package com.Project.Student.Dao_Dao;

import java.time.LocalDate;
import java.util.List;

import com.Project.Student.Dao_Eutil.EMUtils;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
import com.Project.Student.Dao_beam.Registration;
import com.Project.Student.Dao_beam.StudentEntity;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminEntityDaoImpl implements AdminEntityDao {

	@Override
	public String adminAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException {

		String authadmin = null;
		try {
			if (userId.equals("admin")&&pass.equals(pass)) {
				authadmin="Admin Autheticate Succesfully";
				
			}else {
				throw new SomeThingWrongException("userId or Password Are Incorrect");
			}
		} catch (PersistenceException px) {

			throw new SomeThingWrongException("Unable to process request, try again later");
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

		boolean b_status = false;
		EntityManager em = null;
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

		BatcheEntity res = null;
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			BatcheEntity cs = em.find(BatcheEntity.class, batchId);
			if (cs != null) {

				cs.setSeat(seat);
				em.persist(cs);
				em.getTransaction().commit();
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

//	@Override
//	public CourseEntity admiCourseToBatch(int bid, int courseId)
//
//	
//			throws SomeThingWrongException, NoRecordFoundException {
//		
//		CourseEntity status = null;
//		
//		EntityManager em = EMUtils.getConnection();
//
//		try {
//			em.getTransaction().begin();
//			CourseEntity cs = em.find(CourseEntity.class, courseId);
//			
//			BatcheEntity bt=em.find(BatcheEntity.class, bid);
//			if (cs != null&&bt!=null) {
//
//				bt.getCourses().add(cs);
//				em.persist(bt);
//				em.getTransaction().commit();
//				status = cs;
//			} else {
//				throw new NoRecordFoundException("Course does Not Exits!");
//			}
//
//		} catch (PersistenceException px) {
//			throw new SomeThingWrongException(px.getMessage());
//		} finally {
//
//			em.close();
//		}
//		return status;
//	}

//	**************************************************************************************************************

	@Override
	public BatcheEntity searchBatch(int Batchid) throws SomeThingWrongException, NoRecordFoundException {
		BatcheEntity status = null;

		EntityManager em = null;

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

	@Override
	public CourseEntity admiCourseToBatch(int bid, int courseId)
			throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			BatcheEntity bt = em.find(BatcheEntity.class, bid);
			CourseEntity ct = em.find(CourseEntity.class, courseId);

			if (bt == null) {
				throw new NoRecordFoundException("Batch Not Found");
			}
			if (ct == null) {
				throw new NoRecordFoundException("Course Not Found");
			}
			ct.getBatche().add(bt);
			bt.setCourses(ct);
			em.persist(ct);
			em.persist(bt);
			em.getTransaction().commit();
			return ct;
		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {
			em.close();
		}
	}

	@Override
	public StudentEntity admiStudentToBatch(int bid, int studentrollno)
			throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEntity> admiViewAllStudent() throws SomeThingWrongException, NoRecordFoundException {
		List<StudentEntity> res = null;
		EntityManager em = EMUtils.getConnection();
		try {

			String adminAuth = "SELECT a FROM StudentEntity a ";
			Query qq = em.createQuery(adminAuth);
			res = qq.getResultList();
			return res;
		} catch (PersistenceException px) {

			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
	}

	@Override
	public void registerStudentBatch(int studentid, int batchid)
			throws NoRecordFoundException, SomeThingWrongException {
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			BatcheEntity bt = em.find(BatcheEntity.class, batchid);
			int crsId =bt.getCourses().getCouresId();
			
			CourseEntity ct=em.find(CourseEntity.class, crsId);
			StudentEntity st = em.find(StudentEntity.class, studentid);
			Registration reg = new Registration();
			if (st == null) {
				throw new NoRecordFoundException("Student Not Found");
			}
			if (ct == null) {
				throw new NoRecordFoundException("Batch  Not have Couse Found");
			}
			reg.setBatcheEntity(bt);
			reg.setStudentEnitty(st);
		    reg.setRegistrationDate((LocalDate.now()).toString());
			
			st.getReg().add(reg);
			bt.getReg().add(reg);
			st.getCourses().add(ct);
			ct.getStudents().add(st);
			
			em.persist(reg);
			em.persist(st);
			em.persist(ct);
			em.persist(bt);
			em.getTransaction().commit();

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {
			em.close();
		}

	}

}
