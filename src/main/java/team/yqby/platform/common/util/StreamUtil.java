package team.yqby.platform.common.util;

import java.io.*;

/**
 * <p>
 * 文件流工具
 * </p>
 * User：jumping Date： 2016/10/16 0016  Version：1.0
 */
public class StreamUtil {
    /**
     * 文件流保存图片文件
     *
     * @param inStream
     * @param filePath
     * @throws IOException
     */
    public static void saveBit(InputStream inStream, String filePath) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        byte[] data = outStream.toByteArray();
        File imageFile = new File(filePath);
        FileOutputStream fileOutStream = new FileOutputStream(imageFile);
        fileOutStream.write(data);
        fileOutStream.close();
        outStream.close();
    }

    public static String chinaToUnicode(String str){
        String result="";
        for (int i = 0; i < str.length(); i++){
            int chr1 = (char) str.charAt(i);
            if(chr1>=19968&&chr1<=171941){
                result+="\\u" + Integer.toHexString(chr1);
            }else{
                result+=str.charAt(i);
            }
        }
        return result;
    }

}
