package com.scu.dataobjects;

public class User {

	
	public String userName;
	public String emailId;
	public String password;
	public int userType;
	public String fName;
	public String lName;
	public String address;
	public String pincode;
	public String phoneNo;
	public int authType;
	
	public User(String userName ,String emailId,String password,int userType,String fName,String lName,String address,String pincode,String phoneNo,int authType)
	{
		this.userName=userName;
		this.emailId=emailId;
		this.password=password;
		this.userType=userType;
		this.fName=fName;
		this.lName=lName;
		this.address=address;
		this.pincode=pincode;
		this.phoneNo=phoneNo;
		this.authType=authType;
	}
	public User(String userName)
	{
		this.userName=userName;
	}
	
}
