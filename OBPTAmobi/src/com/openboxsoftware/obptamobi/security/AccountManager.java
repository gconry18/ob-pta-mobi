package com.openboxsoftware.obptamobi.security;

import java.math.BigInteger;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import android.content.Context;
import android.content.SharedPreferences;

import com.openboxsoftware.obptamobi.security.utils.BCrypt;

public class AccountManager 
{
	private static final String PREFS_ACCOUNT_DATA = "AccountData";
	private static final String KEY_USERNAME = "Username";
	private static final String KEY_PASSWORD = "Password";
	
	private Context context;
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
		
		// Generate a bcrypt hash of the password
		String passwordHash = BCrypt.hashPassword(account.getPassword(), BCrypt.generateSalt());
		
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
		String password = passwordHash;
		
		Account account = new Account(username);
		account.setPassword(password);
		
		return null;
	}
	
	public void removeAccount() 
	{
		
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
		
		String passwordHash = BCrypt.hashPassword(password, BCrypt.generateSalt());
		account.setPassword(passwordHash);
	}
}
