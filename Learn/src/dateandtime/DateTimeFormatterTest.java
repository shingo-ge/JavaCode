package dateandtime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author shingo_ge
 */
public class DateTimeFormatterTest {
    public static void main(String[] args) {
        Long ts = 1574208900000L;
        System.out.println(timestampToString(ts, Locale.CHINA,"Asia/Shanghai"));
        System.out.println(timestampToString(ts,Locale.US,"America/New_York"));
    }

    private static String timestampToString(Long epochMilli, Locale locale, String zoneId) {
        Instant instant = Instant.ofEpochMilli(epochMilli);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT);
        return dateTimeFormatter.withLocale(locale).format(ZonedDateTime.ofInstant(instant, ZoneId.of(zoneId)));
    }
}
