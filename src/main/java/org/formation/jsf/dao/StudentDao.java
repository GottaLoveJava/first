package org.formation.jsf.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.formation.jsf.model.Student;

@Named
@ApplicationScoped
public class StudentDao implements IStudentDao, Serializable {


	private static final long serialVersionUID = 234433199707500924L;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-pu");

	@Override
	public List<Student> getStudents() throws Exception {
		EntityManager em = emf.createEntityManager();
		List<Student> retList = new ArrayList<>();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			TypedQuery<Student> query = em.createQuery(" from Student", Student.class);
			retList = query.getResultList();
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}

		}
		return retList;

	}

	@Override
	public void addStudent(Student theStudent) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(theStudent);
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public Student getStudent(int studentId) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Student student = new Student();
		try {
			et.begin();

			student = em.find(Student.class, studentId);
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return student;

	}

	@Override
	public void updateStudent(Student theStudent) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Student student = em.find(Student.class, theStudent.getId());
			em.merge(student);
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public void deleteStudent(int studentId) throws Exception {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Student student = em.find(Student.class, studentId);
			em.remove(student);
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}
}
