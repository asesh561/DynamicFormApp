package com.abc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.Dao.StudentService;
import com.abc.dto.Student;


@WebServlet("/edit")
public class EditFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public EditFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//setting the content type
		response.setContentType("text/html");

		//getting the printer object to write the response 
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Response</title></head>");
		out.println("<body bgcolor='lightgreen'>");
		out.println("<center>");

		//collecting the data from the request object
		String sid = request.getParameter("sid");

		//pass sid to service layer
		StudentService studentService = new StudentService();
		Student student = studentService.getStudent(Integer.parseInt(sid));

		//process the student object collected from DAO
		if(student!=null)
		{
			out.println("<form action='update' method='post'>");
			out.println("<table>");
			out.println("<tr><td>SID</td><td>"+student.getSid()+"</td></tr>");

			out.println("<tr><td>SNAME</td><td><input type='text' name='sname' value='"+student.getSname()+"'</td><tr>");

			out.println("<tr><td>SAGE</td><td><input type='text' name='sage' value='"+student.getSage()+"'</td></tr>");
			out.println("</table");
			out.println("</form");
		}
		else
		{
			out.println("<h1 style='color:red;text-align:center;>Record Not Available for given ID"+sid+"</h1>");
		}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		out.close();






	}

}
