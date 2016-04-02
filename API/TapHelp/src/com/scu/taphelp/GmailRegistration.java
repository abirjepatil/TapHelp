package com.scu.taphelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.Gson;
import com.scu.dataobjects.User;

/**
 * Servlet implementation class GmailRegistration
 */
@WebServlet("/GmailRegistration")
public class GmailRegistration extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GmailRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("USER_NAME");
		String emailId = request.getParameter("EMAIL_ID");
		String password = request.getParameter("PASSWORD");
		int userType = Integer.parseInt(request.getParameter("USER_TYPE")); //Service Provider or a Service Reciever
		String fName = request.getParameter("FIRST_NAME");
		String lName = request.getParameter("LAST_NAME");
		String address = request.getParameter("ADDRESS");
		String pincode = request.getParameter("PINCODE");		
		String phoneNo = request.getParameter("PHONENO");		
		String servicesListed = request.getParameter("SERVICES");
		int authType  = Integer.parseInt(request.getParameter("AUTHTYPE")); //Defines if its a google plus login or a native login
		String authToken = request.getParameter("AUTHTOKEN");
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        MySQLAccess sqlinterface = new MySQLAccess();
        User registerNew = new User(userName , emailId, password,userType,fName,lName,address,pincode,phoneNo,authType);
        VerifyAuthToken verifyUser = new VerifyAuthToken();
       //Temporary
        //authToken= "ya29..twIhGtPtG2IOxBnHm55hl7cMRFQt0LNAsXeu5l36yivxpbsWya-EKKGkLa_G-bA3rA";
        System.out.println(verifyUser.verifyUser(authToken));
        if(verifyUser.verifyUser(authToken))
        {
        	if(userType==1)
        	{
        		out.println(sqlinterface.Register(registerNew));
        	}
        	//If it is a Service Provider Map the Services
        	if(userType==2)
        	{
        		List<String> serviceList = Arrays.asList(servicesListed.split(","));
        		out.println(sqlinterface.Register(registerNew));//Register New User Same Type
        		//Get the UID of the last inserted 
        		int latestInsertedUID= sqlinterface.getLastInsertedRecord();
        		sqlinterface.insertServiceMapping(latestInsertedUID, serviceList);
        	}
        	
        }
        else
        {
        	out.println("Unauthorized Google Login");
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
