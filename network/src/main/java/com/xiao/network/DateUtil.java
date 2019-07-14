package com.xiao.network;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class DateUtil {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Java Calender类获得指定日期加几天
	 *
	 * @param specifiedDay
	 * @param d
	 *            day
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specified, int d) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specified);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + d);
		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	/**
	 * Java Calender类获得指定日期加N月
	 *
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedMonthAfter(String specified, int month) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specified);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		// int day = c.get(Calendar.DATE);
		// c.set(Calendar.DATE, day - 1);
		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	/**
	 * 比较两个时间的大小
	 *
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_date(String DATE1, String DATE2) {
		int a = 0;
		try {
			Date dt1 = sdf.parse(DATE1);
			Date dt2 = sdf.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				a = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				a = -1;
			} else {
				System.out.println("两个时间相等");
				a = 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return a;
	}

	/**
	 * 比较两个时间的大小
	 *
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_datetime(String DATE1, String DATE2) {
		int a = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dt1 = sdf.parse(DATE1);
			Date dt2 = sdf.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				a = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				a = -1;
			} else {
				System.out.println("两个时间相等");
				a = 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return a;
	}

	public static int compare_date(String DATE1) {
		int a = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dt1 = sdf.parse(DATE1);
			Date dt2 = new Date();
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				a = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				a = -1;
			} else {
				System.out.println("两个时间相等");
				a = 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return a;
	}
	public static int compare_date2(String DATE1) {
		int a = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt1 = sdf.parse(DATE1);
			Date dt2 = sdf.parse(getCurTime2());
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				a = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				a = -1;
			} else {
				System.out.println("两个时间相等");
				a = 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return a;
	}

	public static int[] getDates(String date) {
		Calendar mCalendar = Calendar.getInstance();
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				mCalendar.setTime(format.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// mCalendar.set(2014, 5, 4);
		int[] mToday = new int[3];
		mToday[0] = mCalendar.get(Calendar.DAY_OF_MONTH);
		mToday[1] = mCalendar.get(Calendar.MONTH); // zero based
		mToday[2] = mCalendar.get(Calendar.YEAR);
		return mToday;
	}
	public static long getLongTimes(String date){
		Calendar mCalendar = Calendar.getInstance();
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				mCalendar.setTime(format.parse(date));
				return mCalendar.getTimeInMillis();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return 0 ;
	}

	public static int[] getTimes(String date,String formatstr) {
		Calendar mCalendar = Calendar.getInstance();
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(formatstr);
			try {
				mCalendar.setTime(format.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// mCalendar.set(2014, 5, 4);
		int[] mToday = new int[5];
		mToday[0] = mCalendar.get(Calendar.DAY_OF_MONTH);
		mToday[1] = mCalendar.get(Calendar.MONTH); // zero based
		mToday[2] = mCalendar.get(Calendar.YEAR);
		mToday[3] = mCalendar.get(Calendar.HOUR_OF_DAY);
		mToday[4] = mCalendar.get(Calendar.MINUTE);
		return mToday;
	}
	public static int[] getTimes(String date) {
		Calendar mCalendar = Calendar.getInstance();
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				mCalendar.setTime(format.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		// mCalendar.set(2014, 5, 4);
		int[] mToday = new int[5];
		mToday[0] = mCalendar.get(Calendar.DAY_OF_MONTH);
		mToday[1] = mCalendar.get(Calendar.MONTH); // zero based
		mToday[2] = mCalendar.get(Calendar.YEAR);
		mToday[3] = mCalendar.get(Calendar.HOUR_OF_DAY);
		mToday[4] = mCalendar.get(Calendar.MINUTE);
		return mToday;
	}

	public static int getWeekNum(String time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(time));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int getAge(String date) {
		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

			cal.setTime(format.parse(date));
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			int age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					// monthNow==monthBirth
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					// monthNow>monthBirth
					age--;
				}
			}
			return age;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getWeek(String pTime) {
		String Week = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			Week += "周日";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Week += "周一";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Week += "周二";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Week += "周三";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Week += "周四";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Week += "周五";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Week += "周六";
		}
		return Week;
	}

	public static String getCurTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm",
				Locale.CHINA);
		f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Calendar c = Calendar.getInstance();
		return f.format(c.getTimeInMillis());
	}
	public static String getDateTime(Date date,String fomate) {
		SimpleDateFormat f = new SimpleDateFormat(fomate,
				Locale.CHINA);
		return f.format(date);
	}
	public static String getCurTime2() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Calendar c = Calendar.getInstance();
		return f.format(c.getTimeInMillis());
	}

	public static String getCurDate() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Calendar c = Calendar.getInstance();
		return f.format(c.getTimeInMillis());
	}

	public static String getLastTime() {
		SimpleDateFormat f = new SimpleDateFormat("MM/dd", Locale.CHINA);
		f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Calendar c = Calendar.getInstance();
		return f.format(c.getTimeInMillis());
	}

	// 格式为yyyy-MM-dd HH:mm:ss
	public static String getDateStr(String date) {
		return date.trim().substring(0, date.indexOf(" "));
	}

	public static String getDateTimeStr(String date) {
		return date.trim().substring(0, date.lastIndexOf(":"));
	}

	public static long getTimestamp() {
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}


	public static String getDateTime() {
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.CHINA);
		f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Calendar c = Calendar.getInstance();
		return f.format(c.getTimeInMillis());
	}
}