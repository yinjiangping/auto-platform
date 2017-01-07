package team.yqby.platform.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.lang.StringUtils;
import team.yqby.platform.dto.model.res.PayNotifyRes;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 生成XML报文
     *
     * @param object 对象
     * @return 报文
     */
    public static String toXml(Object object) {
        XStream xStream = new XStream(new StaxDriver());
        xStream.autodetectAnnotations(true);
        String xml = xStream.toXML(object);
        return xml;
    }

    public static String streamToStr(HttpServletRequest request) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder stb = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                stb.append(line);
            }
            return StringUtils.trim(stb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

}
