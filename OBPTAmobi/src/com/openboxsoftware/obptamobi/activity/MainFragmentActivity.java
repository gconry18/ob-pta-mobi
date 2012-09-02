package com.openboxsoftware.obptamobi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.adapter.SectionsPagerAdapter;
import com.openboxsoftware.obptamobi.listener.OnFailureListener;
import com.openboxsoftware.obptamobi.listener.OnSuccessListener;
import com.openboxsoftware.obptamobi.preference.SignInPreferenceManager;
import com.openboxsoftware.obptamobi.security.Account;
import com.openboxsoftware.obptamobi.security.AccountManager;
import com.openboxsoftware.obptamobi.security.AuthorizationTask;

public class MainFragmentActivity extends FragmentActivity implements OnSuccessListener, OnFailureListener
{
	public static final String INTENT_AUTO_SIGN_IN = "IntentAutoSignIn";
	
	private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        
        Intent intent = getIntent();
        boolean autoSignIn = intent.getBooleanExtra(INTENT_AUTO_SIGN_IN, false);
        
        if(autoSignIn)
        {
        	AccountManager am = AccountManager.get(this);
    		Account account = am.getAccount();
    		
    		// Do authorization
    		AuthorizationTask at = new AuthorizationTask(this);
    		at.setOnSuccessListener(this);
    		at.setOnFailureListener(this);
    		at.execute(account);
        }
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main_fragment, menu);
        
        MenuItem signOutMenuItem = menu.findItem(R.id.menu_signout);
        signOutMenuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() 
        {	
			public boolean onMenuItemClick(MenuItem item) 
			{	
				SignInPreferenceManager pm = SignInPreferenceManager.get(MainFragmentActivity.this);
		        boolean remember = pm.isRememberEnabled();
		        boolean autoSignIn = pm.isAutoSignInEnabled();
				
				Intent intent = new Intent(MainFragmentActivity.this, SignInActivity.class);
				intent.putExtra(SignInActivity.INTENT_REMEMBER, remember);
	        	intent.putExtra(SignInActivity.INTENT_AUTO_SIGN_IN, autoSignIn);
	        	
	        	startActivity(intent);
	        	finish();
				
				return true;
			}
		});
        
        return true;
    }

	public void onSuccess() 
	{
		Toast.makeText(this, "Signed in successfully.", Toast.LENGTH_SHORT).show();
	}
	
	public void onFailure() 
	{
		Toast.makeText(this, "Sign in failed.", Toast.LENGTH_SHORT).show();
	}
}
