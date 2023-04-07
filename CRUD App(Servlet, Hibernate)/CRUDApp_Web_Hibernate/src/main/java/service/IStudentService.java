package service;

import java.util.List;

import dto.Student;

public interface IStudentService {
	

	public String addStudent(Student student);
	
	public Student searchStudent (Integer sid);
	
	public Student updateStudent(Student student); 
	
	public String deleteStudent (Integer sid);
	
	public List<Student> viewStudent();
}
