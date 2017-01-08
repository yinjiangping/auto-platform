package team.yqby.platform.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 *
 * @author jumping
 * @version 1.0.0
 * @time 2015/07/02
 */
@Slf4j
public class DateUtil {

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();
    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    /**
     * 日期时间格式 *
     */
    public static final String timePattern = "HHmmss";
    public static final String timesPattern = "yyyy/MM/ddHH:mm:ss";
    public static final String datePattern = "yyyyMMdd";
    public static final String shortDatePattern = "yyMMdd";
    public static final String fullPattern = "yyyyMMddHHmmss";
    public static final String fullPatterns = "yyyyMMddHHmmssSS";
    public static final String partPattern = "yyMMddHHmmss";
    public static final String ticketPattern = "yyyy.MM.dd HH:mm:ss";
    public static final String settlePattern = "yyyy-MM-dd HH:mm:ss";
    public static final String hour_of_minute = "HHmm";
    public static final String timeColPattern = "HH:mm:ss";
    public static final String timeColMinute = "HH:mm";
    public static final String dateFullPattern = "yyyyMMdd HH:mm:ss";
    public static final String year_of_minute = "yyyyMMddHHmm";
    public static final String yearDate = "yyyy-MM-dd HH:mm";
    public static final String shotPattern = "yyyy-MM-dd";

    /**
     * 时间格式转换
     *
     * @param date          时间字符串
     * @param originPattern 原时间格式
     * @param targetPattern 新的时间格式
     * @return
     * @throws java.text.ParseException
     */
    public static String convert(String date, String originPattern, String targetPattern) throws ParseException {
        Date originDate = parse(date, originPattern);
        return format(originDate, targetPattern);
    }

    /**
     * 源日期和（目标日期加上毫秒数）比较大小， 大则返回false ，小返回true
     *
     * @param src    源日期
     * @param target 目的日期
     * @param second 秒数
     * @return
     */
    public static boolean compareDateForSecond(Date src, Date target, int second) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(target);
        targetTime.add(Calendar.SECOND, second);
        Calendar srcTime = Calendar.getInstance();
        srcTime.setTime(src);
        return srcTime.compareTo(targetTime) <= 0;
    }

    public static void main(String[] args) {

    }

    public static String getDataAfter(int minute, int TimeType) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(new Date());
        targetTime.add(TimeType, minute);
        return format(targetTime.getTime(), DateUtil.fullPattern);
    }

    public static String getCurrentAfter(int minute, int TimeType) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(new Date());
        targetTime.add(TimeType, minute);
        return format(targetTime.getTime(), DateUtil.fullPattern);
    }

    public static String getCurrentAfter(Date date, int minute, int TimeType) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(date);
        targetTime.add(TimeType, minute);
        return format(targetTime.getTime(), DateUtil.fullPattern);
    }

    public static String getTimeMinuteAfter(String currentTime, int minute) {
        Calendar targetTime = Calendar.getInstance();
        Date reqTime = null;
        try {
            reqTime = parse(currentTime, fullPattern);
        } catch (Exception e) {
            reqTime = new Date();
        }
        targetTime.setTime(reqTime);
        targetTime.add(Calendar.MINUTE, minute);
        return format(targetTime.getTime(), DateUtil.fullPattern);
    }

    public static String getDateAfter(int hour) {
        Calendar targetTime = Calendar.getInstance();
        targetTime.setTime(new Date());
        targetTime.add(Calendar.HOUR, hour);
        return format(targetTime.getTime(), DateUtil.shotPattern);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrent() {
        return format(new Date(), DateUtil.fullPattern);
    }

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);
        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    /**
     * 使用线程容器来获取SimpleDateFormat
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) {
        try {
            if (StringUtils.isEmpty(dateStr)) {
                return null;
            }
            return getSdf(pattern).parse(dateStr);
        } catch (Exception e) {
            log.error("date parse exception,date:{},error", dateStr, e);
        }
        return new Date();
    }
}
