package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import play.Logger;

public class StringUtil {

	public static String formdata(String str){
		return "data["+str+"]";
	}

	public static String getStandTime() {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dFormat.format(new Date());
	}
	
	public static Date convertDate(String date,String pattern){
		if(date!=null){
			SimpleDateFormat format;
			if(pattern==null||pattern.equals("")){
				format = new SimpleDateFormat("yyyy-MM-dd");
			}else{
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
	
	public static String convertDate(Date date,String pattern){
		if(date!=null){
			SimpleDateFormat format;
			if(pattern==null||pattern.equals("")){
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{
				format = new SimpleDateFormat(pattern);
			}
			
			return format.format(date);
			
		}
		return null;
	}
}
