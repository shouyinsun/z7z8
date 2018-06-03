package z7z8.z7z8Test;

import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.util.*;


public class Test3 {

	public static void main(String[] args) throws DecoderException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		String smsTemplate="阿达山东{ Name   }省地方下{age }发生的发{ mobile }生sadf awfqwe发生的父亲为";
		Map<String,String> map=new HashMap<String, String>();
		map.put("name", "cash ");
		map.put("MobilE", "15167890909 ");
		map.put("AGe", "1000");
		
		for(Map.Entry<String,String> entry:map.entrySet()){
			smsTemplate=smsTemplate.replaceAll("\\{(\\s)*(?i)"+entry.getKey()+"(\\s)*\\}",entry.getValue().trim());
	    }
		
		System.out.println(smsTemplate);
		
		
		//打印数组
		String[] strArr={"nomey","nomey","go","my","home"};
		System.out.println(Arrays.toString(strArr));
		System.out.println(Arrays.deepToString(strArr));
	
		
		String ss="where  name like %";
		ss=ss.replaceFirst("%", "%%");
		ss+="%s";
		System.out.println(String.format(ss,"Zhang san"));
		
		
		Date date=new Date(2017,11,31);
		Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth=calendar.get(Calendar.WEEK_OF_MONTH);
        String s= String.format("%02d",calendar.get(Calendar.MONTH)+1)+String.format("%02d",weekOfMonth);
        System.out.println(s);
        int sss=calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println(sss);
		
        
	}

}
