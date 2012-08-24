package com.openboxsoftware.obptamobi.dialog;

import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.openboxsoftware.obptamobi.PTAActivity;
import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.authentication.Configuration;
import com.openboxsoftware.obptamobi.authentication.ConfigurationHandler;

public class SignInDialogFragment extends DialogFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
		
		Configuration config = ((PTAActivity)getActivity()).getConfiguration();
		
		if(config.containsOption("remember")) {
			CheckBox rememberCheckBox = (CheckBox)view.findViewById(R.id.check_box_remember);
			
			if(config.getOption("remember").equals("true")) {
				rememberCheckBox.setChecked(true);
			}
		}
		
		if(config.containsOption("username")) {
			EditText username = (EditText)view.findViewById(R.id.edit_text_username);
			username.setText(config.getOption("username"));
		}
		
		if(config.containsOption("password")) {
			EditText password = (EditText)view.findViewById(R.id.edit_text_password);
			password.setText(config.getOption("password"));
		}

		Button signInButton = (Button)view.findViewById(R.id.button_sign_in);
		signInButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				Toast.makeText(getActivity(), "Signing in...", Toast.LENGTH_SHORT).show();
				((PTAActivity)getActivity()).setSignedIn(true);
				
				CheckBox rememberCheckBox = (CheckBox)view.getRootView().findViewById(R.id.check_box_remember);
				
				Configuration config = new Configuration();
				
				if(rememberCheckBox.isChecked()) {
					
					EditText username = (EditText)view.getRootView().findViewById(R.id.edit_text_username);
					EditText password = (EditText)view.getRootView().findViewById(R.id.edit_text_password);
					
					config.putOption("remember", "true");
					config.putOption("username", username.getText().toString());
					config.putOption("password", password.getText().toString());
					
				} else {
					
					config.putOption("username", "");
					config.putOption("password", "");
					config.putOption("remember", "false");
				}
				
				
				try{
					
					File file = new File(PTAActivity.STORAGE_PATH + "/obpta/config");
					ConfigurationHandler.write(config, file);
				} catch(IOException ioe) {

				}
				
				
				SignInDialogFragment.this.dismiss();
				Toast.makeText(getActivity(), "Signed in successfully.", Toast.LENGTH_SHORT).show();
			}
		});
		
		Button cancelButton = (Button)view.findViewById(R.id.button_cancel);
		cancelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				Toast.makeText(getActivity(), "Exiting application...", Toast.LENGTH_SHORT).show();
				SignInDialogFragment.this.dismiss();
				((PTAActivity)getActivity()).finish();
			}
		});
		
		// Don't allow the dialog to be cancelled by touching outside of it
		this.getDialog().setCanceledOnTouchOutside(false);
		this.getDialog().setTitle(R.string.title_sign_in);
		
		return view;
	}
}
