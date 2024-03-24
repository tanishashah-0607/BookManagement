package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet 
{
	private static String query="delete from BOOKDATA where id=?";
	protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException ,IOException
	{
	  	PrintWriter pw=resp.getWriter();
	  	resp.setContentType("text/html");
	  	int id=Integer.parseInt(req.getParameter("id"));
	  	
	  	
	  	
	  	try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			
		}
	  	 try
	  	 {
	  		 Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","Tanisha123");
	  		 PreparedStatement ps=con.prepareStatement(query); 
	  		 ps.setInt(1, id);
	  		 int count=ps.executeUpdate();
	  		 if(count==1)
	  		 {
	  			 pw.println("<h2>Record is deleted successfully</h2>");
	  		 }
	  		 else
	  		{
	  			 pw.println("<h2>Record is not deleted successfully</h2>");
	  		 }
	  			 
	  	}catch(Exception se)
	  	 {
			   se.printStackTrace();
			   pw.println("<h1>"+se.getMessage()+"</h1>");
	  	 }
	  	 pw.println("<a href='home.html'>Home</a>");
	  	 pw.println("<br>");
		 pw.println("<a href='bookList'>Book List</a>");
	  	
	  
	 }
	protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException ,IOException
	{
	  doGet(req,resp);
	}
	
}
	
