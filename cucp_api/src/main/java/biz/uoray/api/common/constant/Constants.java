package biz.uoray.api.common.constant;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class Constants {

    private Constants() {
    }

    public static final ZoneId ZONE_TIME = ZoneId.of("UTC");

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM月dd日");

}
