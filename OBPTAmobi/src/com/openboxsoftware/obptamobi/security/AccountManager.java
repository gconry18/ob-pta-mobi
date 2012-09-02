package com.openboxsoftware.obptamobi.security;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountManager 
{
	private static final String PREFS_ACCOUNT_DATA = "AccountData";
	private static final String KEY_USERNAME = "Username";
	private static final String KEY_PASSWORD = "Password";
	
	private final Context context;
	private static AccountManager instance;
	
	public static AccountManager get(Context context) 
	{
		if(instance == null) 
		{
			instance = new AccountManager(context);
		}
		
		return instance;
	}
	
	private AccountManager(Context context) 
	{
		this.context = context;
	}
	
	public boolean putAccount(Account account) 
	{	
		// Get private shared preferences
		SharedPreferences settings = context.getSharedPreferences(PREFS_ACCOUNT_DATA, Context.MODE_PRIVATE);
		
		// TODO: Encrypt here
		String passwordHash = account.getPassword();
		
		// Add the username and hashed password to the shared preferences
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(KEY_USERNAME, account.getUsername());
		editor.putString(KEY_PASSWORD, passwordHash);
		editor.commit();
		
		return true;
	}
	
	public Account getAccount() 
	{
		// Get private shared preferences
		SharedPreferences settings = context.getSharedPreferences(PREFS_ACCOUNT_DATA, Context.MODE_PRIVATE);
		
		String username = settings.getString(KEY_USERNAME, null);
		String passwordHash = settings.getString(KEY_PASSWORD, null);
		
		// If we cannot find the username or password we can't do much
		if(username == null || passwordHash == null) {
			return null;
		}
		
		// TODO: Decrypt here
		String password = passwordHash;
		
		Account account = new Account(username);
		account.setPassword(password);
		
		return account;
	}
	
	public void removeAccount() 
	{
		// Get private shared preferences
		SharedPreferences settings = context.getSharedPreferences(PREFS_ACCOUNT_DATA, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		
		// Remove account details
		editor.remove(KEY_USERNAME);
		editor.remove(KEY_PASSWORD);
		editor.commit();
	}
	
	public String getPassword(Account account) 
	{
		return account.getPassword();
	}
	
	public void setPassword(Account account, String password) 
	{	
		if(password == null) 
		{
			password = "";
		}
		
		// TODO: Encrypt here
		String passwordHash = password;
		account.setPassword(passwordHash);
	}
}
