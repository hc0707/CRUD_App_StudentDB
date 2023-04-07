package persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dto.Student;
import util.HibernateUtil;

public class StudentDaoImpl implements IStudentDao {

	private Session session = HibernateUtil.getSession();

	@Override
	public String addStudent(Student student) {
		Transaction transaction = session.beginTransaction();
		Boolean flag = false;
		String status;
		try {
			if (transaction != null) {
				Student searchStudent = new Student();
				searchStudent.setSname(student.getSname());
				searchStudent.setSage(student.getSage());
				searchStudent.setSaddress(student.getSaddress());
				session.save(student);
				flag = true;
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failure";
			}
		}
		return status;
	}

	@Override
	public Student searchStudent(Integer sid) {
		Student student = session.get(Student.class, sid);
		if (student != null) {
			return student;
		} else {
			return null;
		}
	}

	@Override
	public Student updateStudent(Student studentIn) {

		Transaction transaction = session.beginTransaction();
		Boolean flag = false;
		Student updatedStudent = null;
		Student student;
		try {
			if (transaction != null) {
				session.clear();
				updatedStudent = (Student) session.merge(studentIn);
				flag = true;
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				student = updatedStudent;
			} else {
				transaction.rollback();
				student = null;
			}
		}
		return student;

	}

	@Override
	public String deleteStudent(Integer sid) {
		Transaction transaction = session.beginTransaction();
		Boolean flag = false;
		String status;
		Student student = searchStudent(sid);
		try {
			if (transaction != null) {
				session.delete(student);
				flag = true;
			}

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				transaction.commit();
				status = "success";
			} else {
				transaction.rollback();
				status = "failure";
			}
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> viewStudent() {

		String sqlQuery = "from dto.Student";
//		try {
//			connection = JdbcUtil.getJdbcConnection();
//			if (connection != null) {
//				pstmt = connection.prepareStatement(sqlQuery);
//
//			}
//			if (pstmt != null) {
//				ResultSet rs = pstmt.executeQuery();
//				return rs;
//			}
//		} catch (SQLException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		Query<Student> query = session.createQuery(sqlQuery);
		List<Student> list = query.list();
		return list;

	}
}
