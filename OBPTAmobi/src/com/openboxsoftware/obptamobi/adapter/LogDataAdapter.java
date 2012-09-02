package com.openboxsoftware.obptamobi.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.openboxsoftware.obptamobi.R;
import com.openboxsoftware.obptamobi.holder.ListViewHolder;
import com.openboxsoftware.obptamobi.listener.internal.OnLogHoursChangedListener;

public class LogDataAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	private List<String> category;
	private List<Float> hours;
	private static float totalHours;

	public LogDataAdapter(Context context) 
	{
		inflater = LayoutInflater.from(context);
		totalHours = 0;
	}
	
	public LogDataAdapter(Context context, List<String> category, List<Float> hours) 
	{
		inflater = LayoutInflater.from(context);
		this.category = category;
		this.hours = hours;
		totalHours = 0;
	}

	public int getCount() 
	{		
		return category.size();
	}

	public Object getItem(int position) 
	{
		return category.get(position);
	}

	public long getItemId(int position) 
	{
		return position;
	}

	public View getView(int position, View view, ViewGroup viewGroup) 
	{
		ListViewHolder holder;
		
		if (view == null)
		{
			view = inflater.inflate(R.layout.log_work_list_view, null);
			holder = new ListViewHolder();
			holder.longCategory = (TextView) view.findViewById(R.id.label_long_category);
			holder.shortCategory = (TextView) view.findViewById(R.id.label_short_category);
			holder.hours = (EditText) view.findViewById(R.id.label_hours);
			holder.hours.setOnEditorActionListener(new OnLogHoursChangedListener());
			
			view.setTag(holder);
		}
		
		else 
		{
			holder = (ListViewHolder)view.getTag();
		}
		
		String fullCat = category.get(position);
		String longCat = fullCat.substring(0, fullCat.indexOf("\n"));
		String shortCat = fullCat.substring(fullCat.indexOf("\n"));

		holder.longCategory.setText(longCat);
		holder.shortCategory.setText(shortCat);		
		holder.hours.setText(hours.get(position).toString());
		
		totalHours += hours.get(position);
		
		return view;
	}
	
	public static float getTotalHours() 
	{
		return totalHours;
	}
	
}
