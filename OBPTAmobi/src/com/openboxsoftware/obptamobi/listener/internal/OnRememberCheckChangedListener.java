package com.openboxsoftware.obptamobi.listener.internal;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.openboxsoftware.obptamobi.R;

public class OnRememberCheckChangedListener implements OnCheckedChangeListener
{
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) 
	{
		View view = buttonView.getRootView();
		CheckBox autoSignIn = (CheckBox)view.findViewById(R.id.check_box_auto_sign_in);
		
		if(isChecked)
		{
			autoSignIn.setEnabled(true);
		}
		else
		{
			autoSignIn.setChecked(false);
			autoSignIn.setEnabled(false);
		}
	}
}
