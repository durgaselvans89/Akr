package hm.akr.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Hour {

	/**
	 * 
	 * @param hour_add
	 * @return
	 */
	public static String getHour(int hour_add) {
		Calendar cal = getCalendar();
		cal.add(Calendar.HOUR, hour_add);
		DateFormat smsFormat = new SimpleDateFormat("h:mma");
		return smsFormat.format(cal.getTime()).toLowerCase();
	}

	/***************************************************************************
	 * 
	 * @return
	 */
	private static Calendar getCalendar() {
		Calendar cal = Calendar.getInstance();
		try {
			DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String serverDt = BeanUtil.getInstance().getServerDateTime();
			Date today = hourFormat.parse(serverDt);
			cal.setTime(today);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return cal;
	}

	/**
	 * 
	 * @return
	 */
	public static String getHourOnly() {
		Calendar cal = getCalendar();
		DateFormat smsFormat = new SimpleDateFormat("h:mma");
		return smsFormat.format(cal.getTime()).toLowerCase();
	}

	/**
	 * 
	 * @return
	 */
	public static String getDate() {
		Calendar cal = getCalendar();
		DateFormat smsFormat = new SimpleDateFormat("dd/MMM/yy");
		return smsFormat.format(cal.getTime());
	}
	
	
	/**
	 * 
	 * @param toDate
	 * @return
	 */
	public static int getDateDifference(Date toDate) {

		try {
			DateFormat form = new SimpleDateFormat("dd-MM-yyyy");
			long diff = form.parse(BeanUtil.getInstance().getServerDate())
					.getTime()
					- toDate.getTime();
			diff = diff / (1000 * 60 * 60 * 24);
			return (int) diff;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}