package z7z8.z7z8Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result="{\"isAdmin\":false,\"userId\":null,\"jobNumber\":null,\"loginName\":null,\"realName\":null,\"email\":null,\"rightIds\":null}";
		JSONObject jsonobject = JSON.parseObject(result);
		System.out.println((String)jsonobject.get("realName"));
		
		System.out.println(System.currentTimeMillis()+200000);
		
		
        LocalDateTime endTime=LocalDateTime.now();
        System.out.println(endTime);
        System.out.println(endTime.plusSeconds(1));
        
        
        List<String> listStr=new  ArrayList<String>();
        List<String> listStr2=new  ArrayList<String>();
        
        listStr.add("aaa");
        listStr.add("bbb");
        listStr.add("ccc");
        listStr.add("ddd");
        listStr.add("eee");
        
        listStr2.addAll(listStr.subList(2,5));
        
        System.out.println(jsonobject.toJSON(listStr2));
        
        


	}

}
