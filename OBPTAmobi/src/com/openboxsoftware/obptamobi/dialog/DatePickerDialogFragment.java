package com.openboxsoftware.obptamobi.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.util.Date;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		int year = Date.getYear();
		int month = Date.getMonth();
		int day = Date.getDay();
		
		DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month-1, day);
		dpd.setTitle("Date Worked");
		
		return dpd;
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) 
	{
		Button dateWorkedButton = (Button)getActivity().findViewById(R.id.button_date_worked);
		dateWorkedButton.setText(day + "/" + (month+1) + "/" + year);
		
		Date.setDate(year, month, day);
		
		TextView weekday = (TextView)getActivity().findViewById(R.id.label_weekday);
		weekday.setText(Date.getDayString());
    } 
}
