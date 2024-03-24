package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet 
{
	private static String query="insert into bookdata(BOOKNAME,BOOKEDITION,BOOKPRICE)values(?,?,?)";
	protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException ,IOException
	{
      	PrintWriter pw=resp.getWriter();
      	resp.setContentType("text/html");
      	
      	String bookName=req.getParameter("bookName");
      	String bookEdition=req.getParameter("bookEdition");
      	float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
      	try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			
		}
      	 try{
      		 
      		 Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","Tanisha123");
      	     PreparedStatement ps=con.prepareStatement(query); 
      	     ps.setString(1, bookName);
      	     ps.setString(2, bookEdition);
      	     ps.setFloat(3, bookPrice);
      	     int count=ps.executeUpdate();
      	     if(count==1)
      	     {
      	    	 pw.println("<h2>Record is Registered Successfully!!!</h2>");
      	     }
      	     else
      	     {
      	    	 pw.println("<h2>Record not Registered Successfully!!!</h2>");
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
