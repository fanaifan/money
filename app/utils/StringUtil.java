package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.Logger;

public class StringUtil {

	public static String formdata(String str) {
		return "data[" + str + "]";
	}

	public static String getStandTime() {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dFormat.format(new Date());
	}

	public static String getDateFromPattern(String str) {
		SimpleDateFormat dFormat = new SimpleDateFormat(str);
		return dFormat.format(new Date());
	}

	public static int getArriveMonthEndDay(int month) {
		int[] monthend = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int days = monthend[month - 1];
		int currentDay = Integer.parseInt(getDateFromPattern("dd"));
		return days - currentDay;
	}

	public static String repayDate(String reday) {
		int reday_i = Integer.parseInt(reday);
		int currentMonth = Integer.parseInt(getDateFromPattern("MM"));
		int currentDay = Integer.parseInt(getDateFromPattern("dd"));
		int currentHour = Integer.parseInt(getDateFromPattern("HH"));
		int currentMin = Integer.parseInt(getDateFromPattern("mm"));
		String repay_day = "";
		if (reday_i > currentDay) {
			int dd = reday_i - currentDay;
			int hh = 24 - currentHour;
			int mm = 60 - currentMin;
			repay_day = proccessDate(dd, hh, mm);
		} else {
			int dd = getArriveMonthEndDay(currentMonth) + reday_i;
			int hh = 24 - currentHour;
			int mm = 60 - currentMin;

			repay_day = proccessDate(dd, hh, mm);
		}
		return repay_day;
	}
	
	public static String repayEndDate(String reday){
		String date = repayDate(reday);
		String[] dates = date.split(":");
		int dd = Integer.parseInt(dates[0])+20;
		dates[0] = dd+"";
		return proccessDate(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));
	}

	public static String proccessDate(int dd, int hh, int mm) {
		String dd_s, hh_s, mm_s;
		if (dd < 10) {
			dd_s = "0" + dd;
		} else {
			dd_s = dd + "";
		}
		if (hh < 10) {
			hh_s = "0" + hh;
		} else {
			hh_s = hh + "";
		}
		if (mm < 10) {
			mm_s = "0" + mm;
		} else {
			mm_s = mm + "";
		}
		return dd_s + ":" + hh_s + ":" + mm_s + ":00";

	}


	public static Date convertDate(String date, String pattern) {
		if (date != null) {
			SimpleDateFormat format;
			if (pattern == null || pattern.equals("")) {
				format = new SimpleDateFormat("yyyy-MM-dd");
			} else {
				format = new SimpleDateFormat("yyyy-MM-dd");
			}

			try {
				return format.parse(date);
			} catch (ParseException e) {
				Logger.info(e.toString());
			}

		}
		return null;
	}
	
	
	public static String convertDate(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat format;
			if (pattern == null || pattern.equals("")) {
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else {
				format = new SimpleDateFormat(pattern);
			}

			return format.format(date);

		}
		return null;
	}
}
