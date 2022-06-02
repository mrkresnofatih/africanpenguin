package dev.mrkresnofatih.africanpenguin.utilities;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TimeUtils {
    public static Long GetUtcNowMilliseconds() {
        return Instant.now().toEpochMilli();
    }

    public static Long GetUtcLaterMilliseconds(int minutes) {
        return Instant.now().plus(minutes, ChronoUnit.MINUTES).toEpochMilli();
    }
}
