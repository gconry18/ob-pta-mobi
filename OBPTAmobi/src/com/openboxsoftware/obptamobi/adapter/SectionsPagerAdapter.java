package com.openboxsoftware.obptamobi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.fragment.LogWorkFragment;
import com.openboxsoftware.obptamobi.fragment.SummaryFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter 
{
    private Context context;
    
	public SectionsPagerAdapter(Context context, FragmentManager fm) 
	{
		super(fm);
		this.context = context;
	}

    @Override
    public Fragment getItem(int i) 
    {
    	switch(i) 
    	{
    		case 0 : return new SummaryFragment();
    		case 1 : return new LogWorkFragment(); 
    	}

        return null;
    }

    @Override
    public int getCount() 
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) 
    {
        switch (position) 
        {
            case 0: return context.getString(R.string.title_summary_fragment).toUpperCase();
            case 1: return context.getString(R.string.title_log_work_fragment).toUpperCase();
        }
        
        return null;
    }
}
