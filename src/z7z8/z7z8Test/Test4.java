package z7z8.z7z8Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		 	String smsTemplate="您好：您尾号{2}的银行卡在{0}申请提现{1}元。预计{3}点前到账。若非本人操作请及时联系拍拍贷。" ;
	        Pattern p = Pattern.compile("\\{(\\d+)(:.+?)?\\}");
	        Matcher m = p.matcher(smsTemplate);
	        System.out.println(m.groupCount());
	        int arrCnt=0;
	        while (m.find()) {
	           arrCnt++;
	        }
	        System.out.println(arrCnt);
	        
	        
	        String email="xuchao.an@sina.com";
	        String regExp="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
	        Pattern p2 = Pattern.compile(regExp);
	        Matcher m2 = p2.matcher(email);
	        System.out.println( m2.matches());
	        
	        Long l=System.currentTimeMillis();
	        System.out.println(l);
	        Timestamp stamp=new Timestamp(l);
	        System.out.println(stamp);
	        String timestamp =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date(l));
	        System.out.println(timestamp);

	}

}
