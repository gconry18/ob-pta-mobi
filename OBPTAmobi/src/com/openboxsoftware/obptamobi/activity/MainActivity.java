package com.openboxsoftware.obptamobi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.preference.SignInPreferenceManager;

public class MainActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Read the state of preferences
        SignInPreferenceManager pm = SignInPreferenceManager.get(this);
        boolean remember = pm.isRememberEnabled();
        boolean autoSignIn = pm.isAutoSignInEnabled();
        
        Intent intent;
        
        if(remember && autoSignIn)
        {
        	// Start the main fragment activity and attempt to sign in
        	intent = new Intent(this, MainFragmentActivity.class);
        	intent.putExtra(MainFragmentActivity.INTENT_AUTO_SIGN_IN, true);
        }
        else
        {
        	// Start the sign in activity and pass the preferences along too
        	intent = new Intent(this, SignInActivity.class);
        	intent.putExtra(SignInActivity.INTENT_REMEMBER, remember);
        	intent.putExtra(SignInActivity.INTENT_AUTO_SIGN_IN, autoSignIn);
        }
        
        startActivity(intent);
        finish();   
    }
}
