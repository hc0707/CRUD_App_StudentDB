package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
import service.IStudentService;
import servicefactory.StudentServiceFactory;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Student student = new Student();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		System.out.println(request.getRequestURI());
		System.out.println(request.getPathInfo());
		if (request.getRequestURI().endsWith("addform")) {
			String sname = request.getParameter("name");
			String sage = request.getParameter("age");
			String saddress = request.getParameter("address");
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setSaddress(saddress);

			if (studentService != null) {
				String status = studentService.addStudent(student);
				System.out.println(status);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/addStudent.jsp");
				request.setAttribute("status", status);
				try {
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		if (request.getRequestURI().endsWith("searchform")) {
			Integer sid = Integer.parseInt(request.getParameter("id"));
			if (studentService != null) {
				Student std = studentService.searchStudent(sid);
				if (std != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/searchStudent.jsp");
					request.setAttribute("student", std);
					try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/recordNotFound.jsp");
					request.setAttribute("id", sid);
					try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if (request.getRequestURI().endsWith("updateform")) {
			if (studentService != null) {
				Integer sid = Integer.parseInt(request.getParameter("id"));
				Student std = studentService.searchStudent(sid);
				student.setSid(sid);
				student.setSname(request.getParameter("name"));
				student.setSage(Integer.parseInt(request.getParameter("age")));
				student.setSaddress(request.getParameter("address"));
				Student student2 = studentService.updateStudent(student);
				if (student2 != null) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/updateStudent.jsp");
					request.setAttribute("student1", std);
					request.setAttribute("student2", student2);
					try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		if (request.getRequestURI().endsWith("editform")) {
			Integer sid = Integer.parseInt(request.getParameter("id"));

			student = studentService.searchStudent(sid);
			RequestDispatcher rd = null;
			if (student != null) {
				request.setAttribute("student", student);
				rd = request.getRequestDispatcher("/updateform.jsp");
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("/recordNotFound.jsp");
				request.setAttribute("id", sid);
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		if (request.getRequestURI().endsWith("deleteform")) {
			Integer sid = Integer.parseInt(request.getParameter("id"));
			if (studentService != null) {
				String status = studentService.deleteStudent(sid);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteStudent.jsp");
				request.setAttribute("status", status);
				request.setAttribute("id", sid);
				try {
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		if (request.getRequestURI().endsWith("view")) {
			PrintWriter writer = response.getWriter();
			ArrayList<Student> list = new ArrayList<>();
			if (studentService != null) {
				ResultSet rs = studentService.viewStudent();
				if (rs != null) {
					try {
						while (rs.next()) {
							Student std = new Student();
							std.setSid(rs.getInt(1));
							std.setSname(rs.getString(2));
							std.setSage(rs.getInt(3));
							std.setSaddress(rs.getString(4));
							list.add(std);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("/viewAll.jsp");
					request.setAttribute("student", list);

					try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					writer.println("<h1 align='center'>No Data Available</h1");
				}
			}
		}

	}
}
