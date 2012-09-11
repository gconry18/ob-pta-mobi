package com.openboxsoftware.obptamobi.security;

public class AuthenticationResponse 
{
	private boolean mSuccessful;
	private String mMessage;
	
	public AuthenticationResponse(boolean successful)
	{
		mSuccessful = successful;
	}
	
	public AuthenticationResponse(boolean successful, String message)
	{
		mSuccessful = successful;
		mMessage = message;
	}
	
	public boolean isSuccessful()
	{
		return mSuccessful;
	}
	
	public String getMessage()
	{
		return mMessage;
	}
}
