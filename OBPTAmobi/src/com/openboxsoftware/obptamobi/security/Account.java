package com.openboxsoftware.obptamobi.security;

public class Account 
{
	private final String username;
	private String password;
	
	public Account(String username) 
	{
		if(username == null) 
		{
			throw new IllegalArgumentException("Username cannot be null");
		}
		
		this.username = username;
		this.password = new String();
	}	
	
	public String getUsername() 
	{
		return username;
	}

	String getPassword() 
	{
		return password;
	}
	
	void setPassword(String password) 
	{
		if(password == null)
		{
			throw new IllegalArgumentException("Password cannot be null");
		}
		
		this.password = password;
	}
}
