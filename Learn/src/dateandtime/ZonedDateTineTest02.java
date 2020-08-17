package dateandtime;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author shingo_ge
 * 某航线从北京飞到纽约需要13小时20分钟，请根据北京起飞日期和时间计算到达纽约的当地日期和时间。
 */
public class ZonedDateTineTest02 {
    public static void main(String[] args) {
        LocalDateTime departFromBeijing = LocalDateTime.of(2019, 9, 10, 13, 0, 0);
        int hours = 13;
        int minutes = 20;
        LocalDateTime arriveInNewYork = calculateArrivalTime(departFromBeijing,hours,minutes);
        System.out.println(departFromBeijing + "---->" + arriveInNewYork);
    }

    private static LocalDateTime calculateArrivalTime(LocalDateTime departFromBeijing, int hours, int minutes) {
        return departFromBeijing.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"))
                .plusHours(hours).plusMinutes(minutes).toLocalDateTime();
    }
}
