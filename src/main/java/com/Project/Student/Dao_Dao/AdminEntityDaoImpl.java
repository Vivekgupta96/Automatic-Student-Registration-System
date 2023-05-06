package com.Project.Student.Dao_Dao;

import com.Project.Student.Dao_beam.AdminEntity;
import com.Project.Student.Dao_beam.BatcheEntity;
import com.Project.Student.Dao_beam.CourseEntity;
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

			String adminAuth = "SELECT a.adminName FROM AdminEntity a WHERE a.adminUserId = :userId AND a.adminPassword = :password";
			Query qq = em.createQuery(adminAuth, AdminEntity.class);
			qq.setParameter("userId", userId);
			qq.setParameter("password", pass);

			String adminName = (String) qq.getSingleResult();
			authadmin = adminName;
		} catch (PersistenceException px) {

			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.close();
		}
		return authadmin;

	}

	@Override
	public boolean admiNewCourseAdd(CourseEntity course) throws SomeThingWrongException {
		boolean status = false;
		EntityManager em = EMUtils.getConnection();
		em.getTransaction().begin();

		try {
			em.persist(course);
			status = true;

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.getTransaction().commit();
			em.close();
		}

		return status;
	}

	@Override
	public CourseEntity admiCourseUpdate(double cFee, int courseId) throws SomeThingWrongException, NoRecordFoundException {
		CourseEntity status = null;
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			CourseEntity cs = em.find(CourseEntity.class, courseId);
			if (cs != null) {

				cs.setFee(cFee);
				em.persist(cs);
				em.getTransaction().commit();
				status=cs;
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
	public List<CourseEntity> admiCourseUpdate() throws SomeThingWrongException, NoRecordFoundException {
		List<CourseEntity> res=null;
		EntityManager em = EMUtils.getConnection();
		try {

			String adminAuth = "SELECT a FROM CourseEntity a ";
			Query qq = em.createQuery(adminAuth);
			res=qq.getResultList();
			
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
				status=true;
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
		CourseEntity  res=null;

		try {
			CourseEntity cs = em.find(CourseEntity.class, courseId);
			if (cs != null) {
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

	
	
	//batch portion stated;
	@Override
	public boolean adminAddBatch(BatcheEntity bt) throws SomeThingWrongException {
		boolean batchAdd=false;
		EntityManager em = EMUtils.getConnection();
		em.getTransaction().begin();
		try {
			em.persist(bt);
			batchAdd = true;

		} catch (PersistenceException px) {
			throw new SomeThingWrongException(px.getMessage());
		} finally {

			em.getTransaction().commit();
			em.close();
		}
		return batchAdd;
		
	}

	@Override
	public BatcheEntity admiBatchUpdate(int seat, int batchId) throws SomeThingWrongException, NoRecordFoundException {
		BatcheEntity res=null;
		EntityManager em = EMUtils.getConnection();

		try {
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

	
}
