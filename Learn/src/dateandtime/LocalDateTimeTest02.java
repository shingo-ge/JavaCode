package dateandtime;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * @author shingo_ge
 */
public class LocalDateTimeTest02 {
    public static void main(String[] args) {
        test01();
        System.out.println("--------------------");
        test02();
        System.out.println("--------------------");
        test03();
        System.out.println("--------------------");
        LocalDateTime start = LocalDateTime.of(2020, 10, 5, 20, 12, 50);
        LocalDateTime end = LocalDateTime.of(2019, 4, 5, 10, 32, 50);
        Duration between = Duration.between(start, end);
        System.out.println(between);
        Period until = LocalDate.of(2020, 1, 5).until(LocalDate.of(2021, 10, 8));
        System.out.println(until);
    }

    private static void test03() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 5, 20, 11, 30);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isAfter(LocalDate.of(2019,12,30)));
        System.out.println(LocalTime.now().isBefore(LocalTime.of(20,15,30)));
    }

    private static void test02() {
        LocalDateTime firstDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDate);
        LocalDate lastDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDate);
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);
        LocalDate firstMonday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstMonday);
    }

    private static void test01() {
        LocalDateTime localDateTime = LocalDateTime.of(2009,10,20,12,30,35);
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = localDateTime.plusDays(3).minusHours(5);
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2 = localDateTime1.minusMonths(2);
        System.out.println(localDateTime2);
    }
}
