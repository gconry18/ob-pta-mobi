package com.openboxsoftware.obptamobi.listener.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnCancelClickListener implements OnClickListener 
{
	private Context context;
	
	public OnCancelClickListener(Context context)
	{
		this.context = context;
	}
	
	public void onClick(View view) 
	{
		((Activity)context).finish();
	}
}
