package com.openboxsoftware.obptamobi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.listener.OnFailureListener;
import com.openboxsoftware.obptamobi.listener.OnSuccessListener;
import com.openboxsoftware.obptamobi.listener.internal.OnCancelClickListener;
import com.openboxsoftware.obptamobi.listener.internal.OnRememberCheckChangedListener;
import com.openboxsoftware.obptamobi.listener.internal.OnSignInClickListener;
import com.openboxsoftware.obptamobi.preference.SignInPreferenceManager;
import com.openboxsoftware.obptamobi.security.Account;
import com.openboxsoftware.obptamobi.security.AccountManager;

public class SignInActivity extends Activity implements OnSuccessListener, OnFailureListener
{
	public static final String INTENT_REMEMBER = "SavedStateRemember";
	public static final String INTENT_AUTO_SIGN_IN = "SavedStateAutoSignIn";
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        registerListeners();
        
        Intent intent = getIntent();
        boolean remember = intent.getBooleanExtra(INTENT_REMEMBER, false);
        boolean auto = intent.getBooleanExtra(INTENT_AUTO_SIGN_IN, false);

        if(remember)
        {
        	EditText usernameEditText = (EditText)findViewById(R.id.edit_text_username);
        	EditText passwordEditText = (EditText)findViewById(R.id.edit_text_password);
        	
        	AccountManager am = AccountManager.get(this);
        	Account account = am.getAccount();
        	
        	if(account != null)
        	{
        		usernameEditText.setText(account.getUsername());
        		passwordEditText.setText(am.getPassword(account));
        	}
        	
        	CheckBox rememberCheckBox = (CheckBox)findViewById(R.id.check_box_remember);
        	rememberCheckBox.setChecked(true);
        }
        
        if(auto)
        {
        	CheckBox autoSignInCheckBox = (CheckBox)findViewById(R.id.check_box_auto_sign_in);
        	autoSignInCheckBox.setChecked(true);
        }
    }
	
	private void registerListeners()
	{
		Button signInButton = (Button)findViewById(R.id.button_sign_in);
		signInButton.setOnClickListener(new OnSignInClickListener(this));
		
		Button cancelButton = (Button)findViewById(R.id.button_cancel);
		cancelButton.setOnClickListener(new OnCancelClickListener(this));
		
		CheckBox rememberCheckBox = (CheckBox)findViewById(R.id.check_box_remember);
		rememberCheckBox.setOnCheckedChangeListener(new OnRememberCheckChangedListener());
		
		EditText usernameEditText = (EditText)findViewById(R.id.edit_text_username);
		usernameEditText.addTextChangedListener(new OnEditTextChangedListener());
		
		EditText passwordEditText = (EditText)findViewById(R.id.edit_text_password);
		passwordEditText.addTextChangedListener(new OnEditTextChangedListener());
	}

	public void onSuccess() 
	{
		// Get the preference manager
		SignInPreferenceManager pm = SignInPreferenceManager.get(this);
		
		// Get the previous preference settings
		boolean rememberBefore = pm.isRememberEnabled();
		boolean autoBefore = pm.isAutoSignInEnabled();
		
		// Get the checkboxes
		CheckBox rememberCheckBox = (CheckBox)findViewById(R.id.check_box_remember);
		CheckBox autoSignInCheckBox = (CheckBox)findViewById(R.id.check_box_auto_sign_in);
		
		// Get the new preference settings
		boolean rememberAfter = rememberCheckBox.isChecked();
		boolean autoAfter = autoSignInCheckBox.isChecked();
		
		if(!rememberBefore && rememberAfter)
		{
			// Store details
			EditText usernameEditText = (EditText)findViewById(R.id.edit_text_username);
			EditText passwordEditText = (EditText)findViewById(R.id.edit_text_password);
			String username = usernameEditText.getText().toString().trim();
			String password = passwordEditText.getText().toString().trim();
			
			AccountManager am = AccountManager.get(this);
			Account account = new Account(username);
			am.setPassword(account, password);
			am.putAccount(account);
			
			pm.setRememberEnabled(true);
			
			if(!autoBefore && autoAfter)
			{
				pm.setAutoSignInEnabled(true);
			}
		}
		else if(rememberBefore && rememberAfter)
		{
			// If details have changed update the account details
			EditText usernameEditText = (EditText)findViewById(R.id.edit_text_username);
			EditText passwordEditText = (EditText)findViewById(R.id.edit_text_password);
			String newUsername = usernameEditText.getText().toString().trim();
			String newPassword = passwordEditText.getText().toString().trim();
			
			AccountManager am = AccountManager.get(this);
			Account account = am.getAccount();
			
			if(account != null && !account.getUsername().equals(newUsername) || !am.getPassword(account).equals(newPassword))
			{
				Account updatedAccount = new Account(newUsername);
				am.setPassword(updatedAccount, newPassword);
				am.putAccount(updatedAccount);
			}
			
			if(!autoBefore && autoAfter)
			{
				pm.setAutoSignInEnabled(true);
			}
			else if(autoBefore && !autoAfter)
			{
				pm.setAutoSignInEnabled(false);
			}
		}
		else if(rememberBefore && !rememberAfter)
		{
			// Remove the account details
			AccountManager am = AccountManager.get(this);
			am.removeAccount();
			
			pm.setRememberEnabled(false);
			
			if(autoBefore && !autoAfter)
			{
				pm.setAutoSignInEnabled(false);
			}
		}
		
		Intent intent = new Intent(this, MainFragmentActivity.class);
		startActivity(intent);
		finish();
		
		Toast.makeText(this, "Signed in successfully.", Toast.LENGTH_SHORT).show();
	}

	public void onFailure()
	{
		Toast.makeText(this, "Sign in failed.", Toast.LENGTH_SHORT).show();
	}
	
	private class OnEditTextChangedListener implements TextWatcher 
	{
		public void afterTextChanged(Editable s) 
		{
			Button signInButton = (Button)findViewById(R.id.button_sign_in);
			EditText usernameEditText = (EditText)findViewById(R.id.edit_text_username);
			EditText passwordEditText = (EditText)findViewById(R.id.edit_text_password);
			
			String username = usernameEditText.getText().toString().trim();
			String password = passwordEditText.getText().toString().trim();
			
			if(username.length() == 0 && password.length() == 0)
			{
				signInButton.setEnabled(false);
			}
			else
			{
				signInButton.setEnabled(true);
			}
		}

		public void beforeTextChanged(CharSequence s, int start, int count, int after) 
		{

		}

		public void onTextChanged(CharSequence s, int start, int before, int count) 
		{
			
		}
	}
}
