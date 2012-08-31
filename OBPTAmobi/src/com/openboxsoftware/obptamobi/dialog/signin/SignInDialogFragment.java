package com.openboxsoftware.obptamobi.dialog.signin;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.preference.SignInPreferenceManager;

public class SignInDialogFragment extends DialogFragment 
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		// Inflate the layout
		View view = inflater.inflate(R.layout.dialog_sign_in, container, false);
		
		// Add listeners to views
		Button signInButton = (Button)view.findViewById(R.id.button_sign_in);
		signInButton.setOnClickListener(new OnSignInClickListener(this));
		
		Button cancelButton = (Button)view.findViewById(R.id.button_cancel);
		cancelButton.setOnClickListener(new OnCancelClickListener(this));
		
		CheckBox rememberCheckBox = (CheckBox)view.findViewById(R.id.check_box_remember);
		rememberCheckBox.setOnCheckedChangeListener(new OnRememberCheckChangedListener());
		
		// Don't allow the dialog to be cancelled by touching outside of it
		this.getDialog().setCanceledOnTouchOutside(false);
		this.getDialog().setTitle(R.string.title_sign_in);
		
		SignInPreferenceManager pm = SignInPreferenceManager.get(getActivity());
    	
        CheckBox remember = (CheckBox)view.findViewById(R.id.check_box_remember);
    	remember.setChecked(pm.isRememberEnabled());
    	
    	CheckBox autoSignIn = (CheckBox)view.findViewById(R.id.check_box_auto_sign_in);
    	autoSignIn.setChecked(pm.isAutoSignInEnabled());
		
		return view;
	}
}
