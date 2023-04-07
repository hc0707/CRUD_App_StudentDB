package persistence;

import dto.Student;

public interface IStudentDao {
	
	public String addStudent(String sname, Integer sage, String saddress);
	
	public Student searchStudent (Integer sid);
	
	public Student updateStudent(Student student); 
	
	public String deleteStudent (Integer sid);
}

