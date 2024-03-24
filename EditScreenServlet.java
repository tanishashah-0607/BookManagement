package com.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet
{
	private static String query="Select BOOKNAME,BOOKEDITION,BOOKPRICE from bookdata where id=?";
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
	  		 ps.setInt(1,id);
	  		 ResultSet rs=ps.executeQuery();
	  		 rs.next();
	  		 pw.println("<form action='editurl?id="+id+"' method='post'>");
	  		 pw.println("<table align='center'>");
  			 pw.println("<tr>");
  			 pw.println("<td>Book Name</td>");
  			 pw.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
  			 pw.println("</tr>");
  			 pw.println("<tr>");
  			 pw.println("<td>Book Edition</td>");
  			 pw.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
  			 pw.println("</tr>");
  			pw.println("<tr>");
 			 pw.println("<td>Book Price</td>");
 			 pw.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
 			 pw.println("</tr>");
 			pw.println("<tr>");
			 pw.println("<td><input type='submit' vlaue='edit'> </td>");
			 pw.println("<td><input type='reset' value='cancel' </td>");
			 pw.println("</tr>");
 			pw.println("</table>");
 			pw.println("</form>");
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
