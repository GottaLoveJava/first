package org.formation.jsf.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.formation.jsf.dao.IStudentDao;
import org.formation.jsf.model.Student;

public class StudentService implements IStudentService, Serializable {

	private static final long serialVersionUID = 1L;
@Inject
private IStudentDao studentDao;
	
@PostConstruct
public void initService() {
	System.out.println("DaoInject ok");
}
	
	@Override
	public List<Student> getStudents() throws Exception {
		return studentDao.getStudents();
}

	@Override
	public void addStudent(Student student) throws Exception {
		studentDao.addStudent(student);
	}

	@Override
	public Student getStudent(int id) throws Exception {
		return studentDao.getStudent(id);
	}

	@Override
	public void updateStudent(Student student) throws Exception {
		studentDao.updateStudent(student);
	}

	@Override
	public void deleteStudent(int id) throws Exception {
		studentDao.deleteStudent(id);
	}

}
