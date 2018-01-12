package z7z8.z7z8Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;


public class HttpClientTest {

	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub

		
		String smsTemplate="你好, 测试短信通知为%s";
		String parameter=" ";
		
		String a[]=parameter.trim().split("\\|");
		
		Boolean b=StringUtils.isBlank(parameter.trim());
		System.out.println(b);
		String smsContent = String.format(smsTemplate,a);
		
		//String smsContent = String.format(smsTemplate, (StringUtils.isBlank(parameter.trim()) ? "" :a));
		
		System.out.println(smsContent);
		
		LocalDateTime l=LocalDateTime.now();
		System.out.println(l);
		
		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		LocalDateTime x = LocalDateTime.of(tomorrow, midnight);
		System.out.println(x);
		
		LocalTime now=LocalTime.now();
		System.out.println(now.getHour());
		System.out.println(now.getMinute());
		System.out.println(now.getSecond());
		
		
		String s = "templateCode==SMS_46165066&&signName==Radius服务&&msg==866834";
		String[] str = s.split("&&");
		for (String s1 : str) {
			System.out.println(s1);
		}
		
		long ll=1496751143000L;
		Timestamp reportTime=new Timestamp(ll);
		System.out.println(reportTime);
		
		 LocalDateTime end=LocalDateTime.now();
         //三天之前
         LocalDateTime start=end.minusDays(3);
         
         Timestamp startTime=Timestamp.from(start.atZone(ZoneId.systemDefault()).toInstant());
         Timestamp endTime=Timestamp.from(end.atZone(ZoneId.systemDefault()).toInstant());
         
         System.out.println(startTime);
         System.out.println(endTime);
         
         int i=10;
         System.out.println(String.format("dsfasf %s", i));
         
         System.out.println(LocalDateTime.now().toLocalDate());
         

	}

}
