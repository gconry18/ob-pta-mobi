package com.openboxsoftware.obptamobi.dialog;

import java.util.Calendar;

import com.openboxsoftware.obptamobi.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	Calendar c;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, day);
		dpd.setTitle("Date Worked");
		
		return dpd;
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Button dateWorkedButton = (Button)getActivity().findViewById(R.id.button_date_worked);
		dateWorkedButton.setText(day + "/" + month + "/" + year);
		
		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
		        "Friday", "Saturday" };
		
		c.set(year, month, day);
		
		TextView weekday = (TextView)getActivity().findViewById(R.id.label_weekday);
		weekday.setText(strDays[c.get(Calendar.DAY_OF_WEEK) -1]);
    } 
}
