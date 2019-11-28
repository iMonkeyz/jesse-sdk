package com.imonkeyz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy/MM/dd");
	public static final SimpleDateFormat DATETIME_FORMAT_2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static final SimpleDateFormat DATE_FORMAT_EN = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat DATETIME_FORMAT_EN = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private DateUtils() {}

	public static Date getCurrentDate() {
		return parseDate(getCurrentDateTime());
	}

	public static Date getDate(Date now, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.DAY_OF_YEAR, offset);
		return c.getTime();
	}

	public static String getCurrentDateTime() {
		return formatDate(new Date());
	}

	public static String format(Date date, SimpleDateFormat sdf) {
		return sdf.format(date);
	}

	public static String formatDate(Date date) {
		return format(date, DATE_FORMAT);
	}

	public static String formatDateTime(Date date) {
		return format(date, DATETIME_FORMAT);
	}

	public static Date parse(String str, SimpleDateFormat sdf) {
		try {
			return sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String str) {
		return parse(str, DATE_FORMAT);
	}

	public static Date parseDateTime(String str) {
		return parse(str, DATETIME_FORMAT);
	}
}
