package dateandtime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author shingo_ge
 */
public class ZonedDateTineTest {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now();
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(zbj);
        System.out.println(zny);
    }
}
