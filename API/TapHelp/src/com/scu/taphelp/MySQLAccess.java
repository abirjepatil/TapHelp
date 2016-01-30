package com.scu.taphelp;


import java.sql.*;


public class MySQLAccess {
	public String Connect(String username, String usrpassword) {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return "[DB01]:DB Driver Error";
		}
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/taphelp","root", "password");
			
			
			//	connection = DriverManager.getConnection("jdbc:mysql://127.5.69.2:3306/taphelp","adminiKy82lU", "TIv8hkBlHyF-");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return "[DB02]:DB Connection Error";

		}

		if (connection != null) {
			
			Statement stmt = null;
			ResultSet rs=null;
			try {
				stmt = connection.createStatement();
				 String sql = "SELECT * FROM TBL_USER";
			      rs = stmt.executeQuery(sql);
			      //STEP 5: Extract data from result set
			      while(rs.next()){
			         //Retrieve by column name
			         int uid  = rs.getInt("UID");
			         String uname = rs.getString("USER_NAME");
			         String emailId = rs.getString("EMAIL_ID");
			         String password = rs.getString("PASSWORD");
			         int usrType = rs.getInt("USER_TYPE");
			         String fname = rs.getString("FIRST_NAME");
			         String lname = rs.getString("LAST_NAME");
			         String address = rs.getString("ADDRESS");
			         int pincode = rs.getInt("PINCODE");
			         
			         if(uname.equals(username)&&password.equals(usrpassword))
			         {
			        	 
			        	 return "[AUTH01] Success";
			         }
			         if(uname.equals(username)&&!password.equals(usrpassword))
			         {
			        	 
			        	 return "[AUTH02] Incorrect Password";
			         }
			         if(!uname.equals(username)&&!password.equals(usrpassword))
			         {
			        	 
			        	 return "[AUTH03] User Not Registered";
			         }
			         
			         
			         
			      }
			      
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "[DB05]:Create Statement Error";
		
			}
			finally
			{
				
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return "[DB04]:Connection Success";

			
			
		} else {
			System.out.println("Failed to make connection!");
			return "[DB03]:DB Network Error";

		}
	}
	
	
	
	
	/*
	 * 
	 * 
	 */
	
	public String Register(String userId,String userName ,String emailId,String password,String userType,String fName,String lName,String address,String pincode) {

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return "[DB01]:DB Driver Error";
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/taphelp","root", "password");
			
			
				connection = DriverManager.getConnection("jdbc:mysql://127.5.69.2:3306/taphelp","adminiKy82lU", "TIv8hkBlHyF-");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return "[DB02]:DB Connection Error";

		}

		if (connection != null) {
			
			Statement stmt = null;
			ResultSet rs=null;
			try {
				stmt = connection.createStatement();
				 String sql = "INSERT INTO TBL_USER VALUES("+userId+",'"+userName+"',"+
						 ")";
			      int res = stmt.executeUpdate(sql);
			      
			    return Integer.toString(res);  
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "[DB05]:Create Statement Error";
		
			}
			finally
			{
				
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			

			
			
		} else {
			System.out.println("Failed to make connection!");
			return "[DB03]:DB Network Error";

		}
	}
	 
}