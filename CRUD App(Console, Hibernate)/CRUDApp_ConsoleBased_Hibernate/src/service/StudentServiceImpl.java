package service;


import daofactory.StudentDaoFactory;
import dto.Student;
import persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService {

	private IStudentDao studentDao;

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		studentDao = StudentDaoFactory.getStudentDao();
		if (studentDao != null) {
			return studentDao.addStudent(sname, sage, saddress);
		
		}else
			return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		if (studentDao != null) {
			return studentDao.searchStudent(sid);
		
		}else
			return null;
	}

	@Override
	public Student updateStudent(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		if (studentDao != null) {
			return studentDao.updateStudent(student);
		
		}else
			return null;
	}

	@Override
	public String deleteStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		if (studentDao != null) {
			return studentDao.deleteStudent(sid);
		
		}else
			return "failure";
	}

}
