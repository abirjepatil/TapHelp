package com.scu.taphelp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String userName = request.getParameter("username");
				String password = request.getParameter("password");
				response.setContentType("application/json");
		        PrintWriter out = response.getWriter();
		        
		        MySQLAccess sqlinterface = new MySQLAccess();
		        //Check if User is Present
		        String result = sqlinterface.Connect(userName,password);
		        if(result.contains("[AUTH03] User Not Registered"))
		        {
		        	
		        	
		        }
		        else
		        {
		        	out.println("[REG 02] Duplicate User");
		        	
		        }
		     
		        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
