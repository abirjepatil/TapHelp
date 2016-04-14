package com.scu.taphelp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scu.dataobjects.User;

/**
 * Servlet implementation class GetDetails
 */
@WebServlet("/GetDetails")
public class GetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String userName = request.getParameter("USER_NAME");
		 MySQLAccess details = new MySQLAccess();
		 User detailsUser = details.GetDetails(userName) ;
		 response.setContentType("application/json");
		 PrintWriter out = response.getWriter();	
		 if(detailsUser==null)
		 {
			 out.println("User Not Present");
		 }
		 else
		 {		
		 String JsonResponse="{\"username\":\""+detailsUser.userName+"\","+
                 "\"emailId\":\""+detailsUser.emailId+"\","+
                 "\"userType\":\""+detailsUser.userType+"\","+
                 "\"firstName\":\""+detailsUser.fName+"\","+
                 "\"lastName\":\""+detailsUser.lName+"\","+
                 "\"address\":\""+detailsUser.address+"\","+
                 "\"pincode\":\""+detailsUser.pincode+"\","+
                 "\"phoneNo\":\""+detailsUser.phoneNo+"\","+
                 "\"authType\":\""+detailsUser.authType+"\""+
                 "}";

out.println(JsonResponse);
out.close();
			 
		 }

		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
