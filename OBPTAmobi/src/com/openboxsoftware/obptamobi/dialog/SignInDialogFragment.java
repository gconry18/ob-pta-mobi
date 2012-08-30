package com.openboxsoftware.obptamobi.dialog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignInDialogFragment extends DialogFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
//		View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
//		
//		Button signInButton = (Button)view.findViewById(R.id.button_sign_in);
//		signInButton.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View view) {
//				SignInDialogFragment.this.dismiss();
//			}
//		});
//		
//		Button cancelButton = (Button)view.findViewById(R.id.button_cancel);
//		cancelButton.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View view) {
//				SignInDialogFragment.this.dismiss();
//				((PTAActivity)getActivity()).finish();
//			}
//		});
//		
//		// Don't allow the dialog to be cancelled by touching outside of it
//		this.getDialog().setCanceledOnTouchOutside(false);
//		this.getDialog().setTitle(R.string.title_sign_in);
		
		AccountManager accountManager = AccountManager.get(getActivity());
		
		
		Account account = new Account("zblazic", "com.openboxsoftware.pta");
		accountManager.addAccountExplicitly(account, "password", null);
		
		Account[] accounts = accountManager.getAccountsByType("com.openboxsoftware.pta");
		
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.VERTICAL);
		
		for(int i = 0; i < accounts.length; i++) {

			String username = accounts[i].name;
			
			TextView textView = new TextView(getActivity());
			textView.setText(username);
			
			layout.addView(textView);
		}
		
		return layout;
	}
}
