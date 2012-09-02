package com.openboxsoftware.obptamobi.security;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.openboxsoftware.obptamobi.listener.OnFailureListener;
import com.openboxsoftware.obptamobi.listener.OnSuccessListener;

public class AuthorizationTask extends AsyncTask<Account, Void, Boolean> 
{	
	private static class ListenerInfo 
	{
		public OnSuccessListener mOnSuccessListener;
		public OnFailureListener mOnFailureListener;
	}
	
	private Context mContext;
	private ListenerInfo mListenerInfo;
	private ProgressDialog mProgressDialog;
	
	public AuthorizationTask(Context context)
	{
		mContext = context;
		
		mProgressDialog = new ProgressDialog(mContext);
		mProgressDialog.setMessage("Signing in...");
		mProgressDialog.setCanceledOnTouchOutside(false);
	}
	
	@Override
	protected void onPreExecute() 
	{
		super.onPreExecute();
		mProgressDialog.show();
	}
	
	@Override
	protected Boolean doInBackground(Account... accounts) 
	{
		AccountManager am = AccountManager.get(mContext);
		Account account = accounts[0];
		
		if(account == null)
		{
			return false;
		}
		
		String username = account.getUsername();
		String password = am.getPassword(account);
		
		try
		{
			// Spoof network waiting time
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		if(username.equals("openbox\\zblazic") && password.equals("password"))
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void onPostExecute(Boolean result) 
	{
		super.onPostExecute(result);
		
		ListenerInfo li = mListenerInfo;
		
		if(result)
		{
			if(li != null && li.mOnSuccessListener != null)
			{
				li.mOnSuccessListener.onSuccess();
			}
		}
		else
		{
			if(li != null && li.mOnFailureListener != null)
			{
				li.mOnFailureListener.onFailure();
			}
		}
		
		mProgressDialog.dismiss();
	}
	
	private ListenerInfo getListenerInfo() 
	{
		if(mListenerInfo == null)
		{
			mListenerInfo = new ListenerInfo();
		}
		
		return mListenerInfo;
	}
	
	public OnSuccessListener getOnSuccessListener()
	{
		return getListenerInfo().mOnSuccessListener;
	}
	
	public void setOnSuccessListener(OnSuccessListener l)
	{
		getListenerInfo().mOnSuccessListener = l;
	}
	
	public OnFailureListener getOnFailureListener()
	{
		return getListenerInfo().mOnFailureListener;
	}
	
	public void setOnFailureListener(OnFailureListener l)
	{
		getListenerInfo().mOnFailureListener = l;
	}
}
