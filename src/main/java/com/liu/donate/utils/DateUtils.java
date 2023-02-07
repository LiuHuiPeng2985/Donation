package com.liu.donate.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *@description:   时间处理工具类
 *@author:        zhushubiao
 *@email:         617374631@qq.com
 *@date:          2020/2/19
 *@time:          14:19
 */
public class DateUtils {

    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
