package z7z8.z7z8Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTst {

	public static void main(String[] args) {
		// Calendar cal = Calendar.getInstance();
		// 当前年份
		/*
		 * int currentYear = cal.get(Calendar.YEAR); // TODO Auto-generated
		 * method stub cal.clear(); cal.set(Calendar.YEAR, currentYear);
		 * cal.add(Calendar.DATE, 1); System.out.println(cal.getTime());
		 * 
		 * cal.clear(); cal.set(Calendar.YEAR, currentYear);
		 * cal.add(Calendar.WEEK_OF_MONTH, 1);
		 * System.out.println(cal.getTime());
		 * 
		 * cal.clear(); cal.set(Calendar.YEAR, currentYear);
		 * cal.add(Calendar.MONTH, 1); System.out.println(cal.getTime());
		 * 
		 * cal.clear(); cal.set(Calendar.YEAR, currentYear);
		 * cal.add(Calendar.YEAR, 1); System.out.println(cal.getTime());
		 * 
		 * cal.setTime(new Date()); cal.add(Calendar.MONTH, 2);
		 * System.out.println(cal.getTime());
		 */

		/*
		 * Calendar calendar=Calendar.getInstance(); Date date=new Date(2017, 4,
		 * 31); calendar.setTime(date); int
		 * weekOfMonth=calendar.get(Calendar.WEEK_OF_MONTH); String
		 * s=String.format
		 * ("%02d",calendar.get(Calendar.MONTH)+1)+String.format("%02d"
		 * ,weekOfMonth); System.out.println(s);
		 */

		/*
		 * Calendar cal=Calendar.getInstance(); Calendar
		 * cal2=Calendar.getInstance(); Date date=new Date(2017, 4, 31);
		 * cal.setTime(date); Date date2=new Date(2015, 8, 31);
		 * cal2.setTime(date2); int
		 * month=cal.get(Calendar.MONTH)-cal2.get(Calendar.MONTH);
		 * System.out.println(month);
		 */

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2017); //
		c.set(Calendar.MONTH, 3); //

		System.out.println(c.getActualMaximum(Calendar.WEEK_OF_MONTH));

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS ");
		System.out.println(sdf.format(date));

		String time="20170331132401";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		Date d;
		try {
			d = format.parse(time);
			Timestamp timestamp = new Timestamp(d.getTime());
			System.out.println(timestamp);
		} catch (Exception e) {
			//continue;
		}
		
		long millis=System.currentTimeMillis();
		System.out.println(millis);
		
		Timestamp timestamp=new Timestamp(millis);
		System.out.println(timestamp);

		

	}

}
