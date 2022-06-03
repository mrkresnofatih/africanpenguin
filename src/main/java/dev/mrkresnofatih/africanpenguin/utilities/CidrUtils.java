package dev.mrkresnofatih.africanpenguin.utilities;

import java.util.Objects;

public class CidrUtils {
    public static Boolean checkValidRawCidrString(String candidateRawCidrString) {
        var segments = candidateRawCidrString.split("[./]");

        // valid cidr string must have 5 segments
        var isSegmentLengthFive = (segments.length == 5);
        if (!isSegmentLengthFive) {
            return false;
        }

        // valid cidr must have valid segments ranges
        var segmentCounter = 0;
        for (String segment : segments) {
            segmentCounter++;

            // Check if they are int-parsable
            var parsed = tryParseInteger(segment);
            if (Objects.isNull(parsed)) {
                return false;
            }

            // Check if they are 8 bits (0 - 255)
            var is8Bit = (parsed >= 0 && parsed < 256);
            if (!is8Bit) {
                return false;
            }

            // Skip if not last segment
            if (segmentCounter != 5) {
                continue;
            }

            // Last Segment should be (1 - 32)
            return (parsed > 0 && parsed <= 32);
        }

        return true;
    }

    public static Boolean checkValidFormattedCidrString(String candidateFormattedCidrString) {
        var formattedCidrStringIs18Characters = (candidateFormattedCidrString.length() == 18);
        if (!formattedCidrStringIs18Characters) {
            return false;
        }

        var segments = candidateFormattedCidrString.split("[./]");
        for (int i = 0; i < 5; i++) {
            var isSegment3Characters = (segments[i].length() == 3);
            var isSegment2Characters = (segments[i].length() == 2);
            if (i != 4 && !isSegment3Characters) {
                return false;
            }
            if (i == 4 && !isSegment2Characters) {
                return false;
            }
        }

        return checkValidRawCidrString(candidateFormattedCidrString);
    }

    private static Integer tryParseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
