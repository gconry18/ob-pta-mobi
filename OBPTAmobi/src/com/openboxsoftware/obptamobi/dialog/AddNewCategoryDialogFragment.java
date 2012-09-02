package com.openboxsoftware.obptamobi.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.openboxsoftware.obptamobi.R;

public class AddNewCategoryDialogFragment extends DialogFragment {

	public static AddNewCategoryDialogFragment newInstance() {
		return new AddNewCategoryDialogFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.dialog_add_new_category, container, false);
		
		// Add events to the buttons
		Button saveButton = (Button)view.findViewById(R.id.button_save);
		saveButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				Toast.makeText(getActivity(), "Added new category.", Toast.LENGTH_SHORT).show();
				AddNewCategoryDialogFragment.this.dismiss();
			}
		});
		
		Button cancelButton = (Button)view.findViewById(R.id.button_cancel);
		cancelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				AddNewCategoryDialogFragment.this.dismiss();
			}
		});
		
		this.getDialog().setTitle(R.string.title_new_category);
		
		return view;
	}
}
