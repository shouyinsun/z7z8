package z7z8.z7z8Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.HttpException;

public class DateFormatTest {

    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");

	public static void main(String[] args) throws HttpException, IOException {

		/*
		 * String content="hello world 徐超!";
		 * 
		 * content=URLEncoder.encode(content, "ISO8859-1");
		 * System.out.println(content);
		 * 
		 * content= URLEncoder.encode(content, "GBK");
		 * System.out.println(content);//hello+world+%D0%EC%B3%AC%21
		 */
	/*	Long s = System.currentTimeMillis();
		System.out.println(s);
		Date date = new Date(s);
		System.out.println(date);

		java.sql.Date date2 = new java.sql.Date(s);
		System.out.println(date2);*/
		
		String dateStr=sdf.format(new Date());
		System.out.println(dateStr);
		
		/*java.sql.Date date=new java.sql.Date((new Date()).getTime());
		System.out.println("date:"+date);*/
		Timestamp timestamp=new Timestamp((new Date()).getTime());
		System.out.println("timestamp: "+timestamp);
		
		
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, -2);
        System.out.println(nowTime.getTime());
        System.out.println(nowTime.getTimeInMillis());
        System.out.println(new Timestamp(nowTime.getTimeInMillis()));
        
        Date d=new Date(1480916751613L);
        System.out.println(d);
        
        Long t=System.currentTimeMillis();
        System.out.println(t);

		String str = "2010-07-02 00:00:00";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		/*String str = "20170331132401";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");*/
		
		java.util.Date d2 = null;
		try {
			d2 = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(d2);
		java.sql.Date date3 = new java.sql.Date(d2.getTime());
		System.out.println(date3);

	}

}
