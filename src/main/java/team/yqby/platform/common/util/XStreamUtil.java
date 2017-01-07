package team.yqby.platform.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class XStreamUtil {

    public static String toXmlWithHead(Object obj, XStream xStream, String charSet) {
        StringBuilder sBuffer = new StringBuilder();
        String xmlHead = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>";
        sBuffer.append(xmlHead).append(toXML(obj, xStream));
        return sBuffer.toString();
    }

    public static String toXML(Object obj, XStream xStream) {
        StringWriter sw = new StringWriter();
        CompactWriter writer = new CompactWriter(sw);
        try {
            xStream.marshal(obj, writer);
        } finally {
            writer.close();
        }
        String str = sw.toString();
        return str.replaceAll("__", "_").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"");
    }

    public static <T> T fromXML(String xml, XStream xStream) {
        if (StringUtils.isBlank(xml)) {
            return null;
        }
        return (T) xStream.fromXML(xml);
    }

    public static <T> T fillByXml(String xml, T bean, XStream xStream) {
        if (null == bean || StringUtils.isBlank(xml)) {
            return null;
        }
        return (T) xStream.fromXML(xml, bean);
    }


    public static void aliasFieldUseUpperCase(Class clazz, XStream xStream) {
        for (Field field : clazz.getDeclaredFields()) {
            aliasUpperCase(field.getName(), clazz, xStream, true, true);
        }
    }


    public static void aliasAttributeUseUpperCase(Class clazz, XStream xStream, boolean isUserCase) {
        for (Field field : clazz.getDeclaredFields()) {
            aliasUpperCase(field.getName(), clazz, xStream, false, isUserCase);
        }
    }

    public static void aliasUseUpperCase(Class clazz, XStream xStream, String... fieldNames) {
        List<String> fields = Arrays.asList(fieldNames);
        for (Field field : clazz.getDeclaredFields()) {
            aliasUpperCase(field.getName(), clazz, xStream, fields.contains(field.getName()), true);
        }
    }

    public static void aliasBusiFields(String packName, XStream xstream) {
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        filePath = filePath.concat(packName.replace(".", "/"));
        packName = packName.concat(".");

        File directory = new File(filePath);
        try {
            if (directory.exists() && directory.isDirectory()) {
                File[] lists = directory.listFiles();
                Class clazz;
                String className;
                for (File file : lists) {
                    if (file.isDirectory()) {
                        continue;
                    }
                    className = StringUtils.removeEnd(packName.concat(file.getName()), ".class");
                    clazz = Class.forName(className);
                    xstream.alias("TEMP", clazz);
                    aliasFieldUseUpperCase(clazz, xstream);
                }
            }
        } catch (ClassNotFoundException e) {
            log.error("注册业务域异常,", e);
        }
    }

    private static void aliasUpperCase(String originName, Class clazz, XStream xStream, boolean isField, boolean isUserCase) {
        String newOriginName = isUserCase ? originName.toUpperCase() : originName;
        if (isField) {
            xStream.aliasField(newOriginName, clazz, originName);
        } else {
            xStream.aliasAttribute(clazz, originName, newOriginName);
        }
    }
}
