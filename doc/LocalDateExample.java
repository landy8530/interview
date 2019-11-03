package com;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

/**
 * @author landyl
 * @create 15:08 09/30/2019
 */
public class LocalDateExample {

    public static void main(String[] args) {
        // 当前日期
        LocalDate today = LocalDate.now();
        System.out.println("当前日期=" + today);

        // 通过传入的参数创建LocalDate对象
        LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("指定日期=" + firstDay_2014);

        // 根据有效输入创建日期，以下代码会抛异常，无效输入，2014年2月没有29日
        // LocalDate feb29_2014 = LocalDate.of(2014, Month.FEBRUARY, 29);
        // Exception in thread "main" java.time.DateTimeException:
        // Invalid date 'February 29' as '2014' is not a leap year

        // 获取不同时区的日期 "Asia/Kolkata", you can get it from ZoneId javadoc
        LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("当前印度标准日期=" + todayKolkata);

        LocalDate shanghai = LocalDate.now(ZoneId.of("CTT", ZoneId.SHORT_IDS));
        System.out.println("当前上海标准日期=" + shanghai);

        // java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
        // LocalDate todayIST = LocalDate.now(ZoneId.of("IST"));

        // 从基准日期获取日期  例如： 01/01/1970
        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("基准日期的第365天= " + dateFromBase);

        //2014年的第一百天
        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
        System.out.println("2014年的第一百天=" + hundredDay2014);
    }

}
