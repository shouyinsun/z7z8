package z7z8.z7z8Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		String reg="\\d{6}";
		String test="84989";
		System.out.println(test.matches(reg));
		// TODO Auto-generated method stub
		/*String regex = "\\{(\\d+)(.+?)\\}";
		String ss = "{2}aaaa{0}xxxxxx{1:hhhhh}";

		// String smsTemplate = ss.replaceAll(regex, "%s");
		
		 * String[] xx=ss.split(regex); for(String s:xx){ System.out.println(s);
		 * }
		 

		String str = "rrwerqq84461376qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf";
		Pattern p = Pattern.compile("qq(.*?)qq");
		Matcher m = p.matcher(str);
		ArrayList<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(0));
		}
		for (String s : strs) {
			System.out.println(s);
		}*/
		
		
		/*String s="";
		String[] xx=s.split("\\|");
		System.out.println(xx.length);*/
		
		String parameter="12122|3432|54342|23423";
        String smsTemplate="您好：您尾号{2}的银行卡在{0}申请提现{1}元。预计{3}点前到账。若非本人操作请及时联系拍拍贷。" ;
        String smsContent="您好：您尾号{2}的银行卡在{0}申请提现{1}元。预计{3}点前到账。若非本人操作请及时联系拍拍贷。";
        String arr[]=parameter.trim().split("\\|");
        Pattern p = Pattern.compile("\\{(\\d+)(:.+?)?\\}");
        Matcher m = p.matcher(smsTemplate);
        ArrayList<String> placeHoldOrder = new ArrayList<>();
        while (m.find()) {
            placeHoldOrder.add(m.group(1));
        }
        if(placeHoldOrder.size()>0){
            if(placeHoldOrder.size()>arr.length){
                //bizResult.setResultCode(InvokeResult.PARAM_NUM_ERROR.getCode());
                //bizResult.setResultMessage(InvokeResult.PARAM_NUM_ERROR.getMessage());
                return ;
            }
            String[] orderArr=new String[placeHoldOrder.size()];
            for(int i=0;i<=placeHoldOrder.size()-1;i++){
                int order=Integer.parseInt(placeHoldOrder.get(i));
                orderArr[i]=arr[order];
            }
            String regex = "\\{(.+?)\\}";
            smsTemplate=smsTemplate.replaceAll(regex, "%s");
            smsContent = String.format(smsTemplate,orderArr);
            System.out.println(smsContent);
        }
        
        System.out.println();
		
		
	}

}
