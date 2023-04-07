package persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dto.Student;
import util.HibernateUtil;

public class StudentDaoImpl implements IStudentDao {

	private Session session = HibernateUtil.getSession();

	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
//        
		Transaction transaction = session.beginTransaction();
		Boolean flag = false;
		String status;
		try {
			if (transaction != null) {
				Student student = new Student();
				student.setSname(sname);
				student.setSage(sage);
				student.setSaddress(saddress);
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
				status = "Success! Record Inserted Successfully ";
			} else {
				transaction.rollback();
				status = "Failed! Record Not Inserted ";
			}
		}
		return status;
	}

	@Override
	public Student searchStudent(Integer sid) {
//       
		Student student = session.get(Student.class, sid);
		if (student != null) {
			return student;
		} else {
			return null;
		}
	}

	@Override
	public Student updateStudent(Student studentIn) {
//
		Transaction transaction = session.beginTransaction();
		Boolean flag = false;
		Student updatedStudent = null;
		Student student;
		try {
			if (transaction != null) {
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
//       
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
				status = "Success! Record Deleted Successfully";
			} else {
				transaction.rollback();
				status = "Record not found for id: " + sid;
			}
		}
		return status;
	}

}
