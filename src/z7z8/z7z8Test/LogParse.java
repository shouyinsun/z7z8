package z7z8.z7z8Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cash
 * @description
 * @date 2021/7/31 11:32 下午
 */
public class LogParse {
    public static void main(String[] args) {
        String path ="/Users/cash/Desktop/data_20210728_191804.txt";
        File file = new File(path);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String s;
            Pattern p1 = Pattern.compile("shopkeepId:(.*?),missionId");
            Pattern p2 = Pattern.compile("missionId:(.*?) 超过");
            Pattern p3 = Pattern.compile("玩法任务个数:(.*?)n\",\"environment\"");
            while(null !=(s = br.readLine())){
                String localAgentId="";
                String missionId="";
                String num="";
                //{"traceId":"0b0141c316274905876563205e24fa","xlog":"2021-07-29 02:29:44,722 INFO  c.a.m.m.o.OrderReplayOpsImpl - OrderReplayOpsImpl.java - recallOrderToListener, recallRefundOrderToListener, shopkeepId:2702696777, bizOrderId:1360647588707865593,refundOrderId:1360647588707865593\n","environment":"minitb","appName":"mmc-mission","level":"INFO","timestamp":"1627496984722","__topic__":"","__source__":"33.49.134.39","__tag__:__receive_time__":"1627496989","__time__":"1627496984"}
                Matcher m1 = p1.matcher(s);
                if(m1.find()){
                    localAgentId =m1.group(1);
                }

                Matcher m2 = p2.matcher(s);
                if(m2.find()){
                    missionId =m2.group(1);
                }

                Matcher m3 = p3.matcher(s);
                if(m3.find()){
                    num =m3.group(1);
                }
                String str =localAgentId+"="+missionId+"="+num;
                System.out.println(str.replace("\\",""));
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
