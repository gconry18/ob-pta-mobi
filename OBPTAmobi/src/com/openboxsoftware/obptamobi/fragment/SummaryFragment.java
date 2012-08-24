package com.openboxsoftware.obptamobi.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.dialog.AddNewCategoryDialogFragment;

public class SummaryFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_summary, container, false);
		
		Button newCategoryButton = (Button)view.findViewById(R.id.button_new_category);
		newCategoryButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View view) {
				showAddCategoryDialog();
			}
		});
		
		return view;
	}
	
	private void showAddCategoryDialog() {
		FragmentManager fm = getFragmentManager();
    	FragmentTransaction ft = fm.beginTransaction();
    	Fragment previous = fm.findFragmentByTag("add_new_category_dialog");
    	
    	if(previous != null) {
    		ft.remove(previous);
    	}
    	
    	ft.addToBackStack(null);
    	
    	DialogFragment fragment = AddNewCategoryDialogFragment.newInstance();
    	fragment.show(ft, "add_new_category_dialog");
	}
}
