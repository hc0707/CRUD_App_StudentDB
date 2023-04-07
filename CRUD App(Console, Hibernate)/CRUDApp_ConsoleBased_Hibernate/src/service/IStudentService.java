package service;

import dto.Student;

public interface IStudentService {
	

	public String addStudent(String sname, Integer sage, String saddress);
	
	public Student searchStudent (Integer sid);
	
	public Student updateStudent(Student student); 
	
	public String deleteStudent (Integer sid);
	
}
