package service;

import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import dto.Student;

public interface IStudentService {
	

	public String addStudent(Student student);
	
	public Student searchStudent (Integer sid);
	
	public Student updateStudent(Student student); 
	
	public String deleteStudent (Integer sid);
	
	public ResultSet viewStudent();
	
}
