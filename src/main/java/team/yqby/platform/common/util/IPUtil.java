package team.yqby.platform.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 获取本地 ip 地址
 *
 * @author jumping
 * @date 2015/6/29
 */
@Slf4j
public class IPUtil {

    public static String getLocalIP() {
        try {
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            log.error("获取本机IP异常:{}", e);
        }
        return "888.888.888.888";
    }

    /**
     * 获取请求ip地址
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        printHeaders(request);
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StringUtils.isEmpty(ip)?"": StringUtils.split(ip, ",")[0];
    }

    private static void printHeaders(HttpServletRequest req) {
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            log.debug(headerName + ":" + req.getHeader(headerName));
        }
    }
}
