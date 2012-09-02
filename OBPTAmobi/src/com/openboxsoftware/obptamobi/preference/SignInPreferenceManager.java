package com.openboxsoftware.obptamobi.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SignInPreferenceManager 
{
	public static final String KEY_REMEMBER = "Remember";
	public static final String KEY_AUTO_SIGN_IN = "AutoSignIn";
	public static final String PREFS_SIGN_IN = "SignIn";
	
	private static SignInPreferenceManager instance;
	private final Context context;
	private final SharedPreferences settings;
	
	public static SignInPreferenceManager get(Context context)
	{
		if(instance == null)
		{
			instance = new SignInPreferenceManager(context);
		}
		
		return instance;
	}
	
	private SignInPreferenceManager(Context context) 
	{
		this.context = context;
		this.settings = this.context.getSharedPreferences(PREFS_SIGN_IN, Context.MODE_PRIVATE);
	}
	
	public boolean isRememberEnabled()
	{
		return settings.getBoolean(KEY_REMEMBER, false);
	}
	
	public void setRememberEnabled(boolean remember)
	{
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(KEY_REMEMBER, remember);
		editor.commit();
	}
	
	public boolean isAutoSignInEnabled()
	{
		return settings.getBoolean(KEY_AUTO_SIGN_IN, false);
	}
	
	public void setAutoSignInEnabled(boolean autoSignIn)
	{
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(KEY_AUTO_SIGN_IN, autoSignIn);
		editor.commit();
	}
}
