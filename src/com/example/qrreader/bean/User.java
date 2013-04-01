package com.example.qrreader.bean;

public class User {

	public String user_name =null;

	public String email=null;
	
	public User(){
		
	}

	public User(String user_name, String date, String email) {

		this.user_name = user_name;

		this.email = email;

	}
	@Override
	public String toString()
	  {
	    return "Usrename is " + user_name + ",Email is " + email;
	  }

}
