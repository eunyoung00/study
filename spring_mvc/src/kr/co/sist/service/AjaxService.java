package kr.co.sist.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONObject;

public class AjaxService {
	public JSONObject createJson() {
		JSONObject json=new JSONObject();
		try {
			json.put("name", URLEncoder.encode("���ü�","UTF-8"));
			json.put("type",URLEncoder.encode("����� �����","UTF-8"));
			json.put("age",27);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}//end catch
		return json;
	}//createJson
	
//	public static void main(String[] arge) {
//		AjaxService as=new AjaxService();
//		System.out.println(as.createJson());
//	}//main
}//class