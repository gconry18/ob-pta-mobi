package com.openboxsoftware.obptamobi.listener.internal;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.activity.SignInActivity;
import com.openboxsoftware.obptamobi.security.Account;
import com.openboxsoftware.obptamobi.security.AccountManager;
import com.openboxsoftware.obptamobi.security.AuthorizationTask;

public class OnSignInClickListener implements OnClickListener
{
	private Context mContext;
	
	public OnSignInClickListener(Context context)
	{
		this.mContext = context;
	}
	
	public void onClick(View view) 
	{
		InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		
		View root = view.getRootView();
		EditText usernameEditText = (EditText)root.findViewById(R.id.edit_text_username);
		EditText passwordEditText = (EditText)root.findViewById(R.id.edit_text_password);
		
		String username = usernameEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		
		AccountManager am = AccountManager.get(mContext);
		Account account = new Account(username);
		am.setPassword(account, password);
		
		// Do authorization
		AuthorizationTask at = new AuthorizationTask(mContext);
		at.setOnSuccessListener(((SignInActivity)mContext));
		at.setOnFailureListener(((SignInActivity)mContext));
		at.execute(account);
	}
}

