package team.yqby.platform.common.util;




import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;

/**
 * Base64加解密工具
 * @author jumping
 * @version 1.0.0
 * @time 2015/07/07
 */
public class MD5Util
{
	
	//生产
	private static final String KEY = "train@tisson.cn";
	
	private final static String[] hexDigits = {
      "0", "1", "2", "3", "4", "5", "6", "7", 
      "8", "9", "a", "b", "c", "d", "e", "f"}; 

	public static String byteArrayToHexString(byte[] b){
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++){
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b){
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin){
		String resultString = null;
			try {
				resultString=new String(origin);
				MessageDigest md = MessageDigest.getInstance("MD5");
				resultString=byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
			}catch (Exception ex){}
			return resultString;
		}
	
	public static String MD5EncodeNoCode(String origin){
		String resultString = null;
			try {
				resultString=new String(origin);
				MessageDigest md = MessageDigest.getInstance("MD5");
				resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
			}catch (Exception ex){}
			return resultString;
		}


	/**
	 * 验证MD5是否一致
	 * 
	 * @version: 1.00
	 * @history: 2011-12-8 下午4:51:19 [created]
	 * @author Shengxin Tang 唐圣欣
	 * @param splitInput 拼接的字段
	 * @param input 传入的sig
	 * @return
	 * @see
	 */
	public static boolean checkMD5(String splitInput,String input){
		
		/*if(Charset.isEmpty(input)){
			return true;
		}*/
		if (MD5Encode(splitInput+KEY).equals(input)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 方法的描述: 验证MD5是否一致
	 * @author: jmx  
	 * @version: Feb 24, 2012 2:20:54 PM
	 * @param splitInput
	 * @param key
	 * @param input
	 * @return
	 * @return boolean
	 */
	public static boolean checkMD5(String splitInput,String key,String input){
		
		/*if(Charset.isEmpty(input)){
			return true;
		}*/
		if(StringUtils.isEmpty(key)){
			key = "";
		}
		if (MD5Encode(splitInput+key).equals(input)) {
			return true;
		}
		return false;
	}

}
