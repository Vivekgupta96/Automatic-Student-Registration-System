package com.Project.Student.Dao_Dao;

import java.time.LocalDate;
import java.util.List;

import com.Project.Student.Dao_Eutil.EMUtils;
import com.Project.Student.Dao_beam.Batche;
import com.Project.Student.Dao_beam.Course;
import com.Project.Student.Dao_beam.CourseDto;
import com.Project.Student.Dao_beam.StudentReg;
import com.Project.Student.Dao_beam.Student;
import com.Project.Student.Exception.NoRecordFoundException;
import com.Project.Student.Exception.SomeThingWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminDaoImpl implements AdminDao {

	@Override
	public String adminAuthToDb(String userId, String pass) throws SomeThingWrongException, NoRecordFoundException {

		String authadmin = null;

			if (userId.equals("admin")) {
				if( pass.equals("admin")){
					authadmin = "Admin Autheticate Succesfully";
				}else {
					throw new SomeThingWrongException("Password Are Incorrect");
				}
			} else {
				throw new SomeThingWrongException("userId Are Incorrect");
			}
		return authadmin;
	}

	@Override
	public boolean admiNewCourseAdd(Course course) throws SomeThingWrongException {
		boolean status = false;
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT count(c) FROM Course c WHERE c.corseName = :coursename");
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
	public Course admiCourseUpdate(CourseDto cdto, int courseId)
			throws SomeThingWrongException, NoRecordFoundException {

		Course status = null;
		EntityManager em = EMUtils.getConnection();

		try {
			em.getTransaction().begin();
			Course cs = em.find(Course.class, courseId);
			if (cs != null) {

				cs.setFee(cdto.getFee());
				cs.setDuration(cdto.getDuration());
				cs.setCourseInstructor(cdto.getInstructerName());
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
	public List<Course> admiViewAllCourse() throws SomeThingWrongException, NoRecordFoundException {
		List<Course> res = null;
		EntityManager em = EMUtils.getConnection();
		try {

			String adminAuth = "SELECT a FROM Course a ";
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
			Course cs = em.find(Course.class, courseId);
			if (cs != null) {
				em.remove(cs);
				em.getTransaction().commit();
				status = true;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {

		} finally{
			em.close();
		}
		return status;
	}

	@Override
	public Course adminCourseSerch(int courseId) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = EMUtils.getConnection();
		Course res = null;

		try {
			Course cs = em.find(Course.class, courseId);
			if (cs != null) {
				res = cs;
			} else {
				throw new NoRecordFoundException("Course does Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException("Error Occured");
		} finally {

			em.close();
		}
		return res;
	}

	@Override
	public boolean adminAddBatch(Batche bt) throws SomeThingWrongException {

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
	public Batche admiBatchUpdate(int seat, int batchId) throws SomeThingWrongException, NoRecordFoundException {

		Batche res = null;
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			Batche cs = em.find(Batche.class, batchId);
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

	@Override
	public Batche searchBatch(int Batchid) throws SomeThingWrongException, NoRecordFoundException {
		Batche status = null;

		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			Batche cs = em.find(Batche.class, Batchid);
			if (cs != null) {

				status = cs;
			} else {
				throw new NoRecordFoundException("Batch Not Exits!");
			}

		} catch (PersistenceException px) {
			throw new SomeThingWrongException("");
		} finally {

			em.close();
		}
		return status;
	}

	@Override
	public Course admiCourseToBatch(int bid, int courseId) throws SomeThingWrongException, NoRecordFoundException {
		EntityManager em = null;

		try {
			em = EMUtils.getConnection();
			em.getTransaction().begin();
			Batche bt = em.find(Batche.class, bid);
			Course ct = em.find(Course.class, courseId);

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
	public Student admiStudentToBatch(int bid, int studentrollno)
			throws SomeThingWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> admiViewAllStudent() throws SomeThingWrongException, NoRecordFoundException {
		List<Student> res = null;
		EntityManager em = EMUtils.getConnection();
		try {

			String adminAuth = "SELECT a FROM Student a ";
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
			Batche bt = em.find(Batche.class, batchid);
			Object couresId=null;
			try {
				if(bt==null) {
					System.out.println("Batch not exits ");
				}
			}catch(Exception e) {
				throw new NoRecordFoundException("Batch Not started Yet");
			}
			
			 
			
			try {
				couresId= bt.getCourses().getCouresId();
				if(couresId==null) {
					throw new NoRecordFoundException("Batch Not started Yet");
				}
			}catch(Exception e) {
				throw new NoRecordFoundException("Batch Not started Yet");
			}
			int corseIdres=(int)couresId;
			Course ct = em.find(Course.class, corseIdres);
			Student st = em.find(Student.class, studentid);
			StudentReg reg = new StudentReg();
			if (st == null) {
				throw new NoRecordFoundException("Student Not Found");
			}
			if (ct == null) {
				throw new NoRecordFoundException("Batch  Not have Couse Found");
			}
			reg.setBatchs(bt);
			reg.setStudents(st);
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
			System.out.println("Batch not exits");
		} finally {
			em.close();
		}

	}

}
