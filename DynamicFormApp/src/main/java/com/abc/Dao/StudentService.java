package com.abc.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.abc.dto.Student;

public class StudentService 
{
	private static final String SQL_SELECT_QUERY = "SELECT SID,SNAME,SAGE FROM STUDENT WHERE SID=?";
	Connection connection=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet resulSet=null; 

	public StudentService()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		    connection=DriverManager.getConnection("jdbc:mysql:///abc","root","Root@123");
		    stmt=connection.createStatement();
		    pstmt=connection.prepareStatement(SQL_SELECT_QUERY);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Student getStudent(Integer sid)
	{
		try
		{
			pstmt.setInt(1,sid);
			resulSet=pstmt.executeQuery();
			if(resulSet.next())
			{
				Student student = new Student();
				student.setSid(sid);
				student.setSname(resulSet.getString(2));
				student.setSage(resulSet.getInt(3));
				return student;
			}
			else
			{
				return null;
			}
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
}
