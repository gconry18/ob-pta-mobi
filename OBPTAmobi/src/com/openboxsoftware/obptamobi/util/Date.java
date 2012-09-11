package com.openboxsoftware.obptamobi.util;

import java.util.Calendar;

public class Date 
{
	private static Calendar c = Calendar.getInstance();
	
	private static String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
	        "Friday", "Saturday" };
	
	public Date()
	{
		c = Calendar.getInstance();
	}
	
	public static void setDate(int y, int m, int d)
	{
		c.set(y, m, d);
	}
	
	public static int getYear()
	{
		return c.get(Calendar.YEAR);
	}
	
	public static int getMonth()
	{
		return (c.get(Calendar.MONTH)+1);
	}
	
	public static int getDay() 
	{
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static String getDayString()
	{
		return days[c.get(Calendar.DAY_OF_WEEK)-1];
	}
}
