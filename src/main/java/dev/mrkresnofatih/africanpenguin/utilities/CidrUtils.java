package dev.mrkresnofatih.africanpenguin.utilities;

import java.util.Arrays;
import java.util.Objects;
import java.lang.Math;

public class CidrUtils {
    public static String getNextIp(String ip) {
        var segments = ip.split("[./]");
        var targetDigitForIncrement = 3;
        for (int index = 3; index >= 0; index--) {
            if (!segments[index].equals("255")) {
                break;
            }
            targetDigitForIncrement = index - 1;
        }

        var result = "";
        for (int id = 0; id < targetDigitForIncrement; id++) {
            result = String.format("%s.%s", result, segments[id]);
        }

        var incrementSegment = Integer.parseInt(segments[targetDigitForIncrement]) + 1;
        result = String.format("%s.%03d", result, incrementSegment);

        for (int idx = targetDigitForIncrement; idx < 3; idx++) {
            result = String.format("%s.%s", result, "000");
        }

        result = result.replaceFirst(".", "");

        return result.toString();
    }

    public static String getTotalHostsFromCidr(String formattedCidr) {
        var segments = formattedCidr.split("[./]");
        var slash = segments[4];
        var numberOfHosts = (long) Math.pow(2, 32-Integer.parseInt(slash));
        return String.valueOf(numberOfHosts);
    }
    public static String getStartingIpOfCidr(String formattedCidr) {
        var segments = formattedCidr.split("[./]");
        var slash = segments[4];
        var identicalStartingDigits = Integer.parseInt(slash) / 8;
        return switch (identicalStartingDigits) {
            case 0 -> "000.000.000.000";
            case 1 -> String.format("%s.000.000.000", segments[0]);
            case 2 -> String.format("%s.%s.000.000", segments[0], segments[1]);
            case 3 -> String.format("%s.%s.%s.000", segments[0], segments[1], segments[2]);
            default -> String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], segments[3]);
        };
    }

    public static String getEndingIpOfCidr(String formattedCidr) {
        var segments = formattedCidr.split("[./]");
        var slash = segments[4];
        var identicalStartingDigits = Integer.parseInt(slash) / 8;
        return switch (identicalStartingDigits) {
            case 0 -> String.format("%s.255.255.255", getNextSegment(slash, segments[0]));
            case 1 -> String.format("%s.%s.255.255", segments[0], getNextSegment(slash, segments[1]));
            case 2 -> String.format("%s.%s.%s.255", segments[0], segments[1], getNextSegment(slash, segments[2]));
            case 3 -> String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], getNextSegment(slash, segments[3]));
            default -> String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], segments[3]);
        };
    }

    private static String getNextSegment(String slash, String segment) {
        var slashPlus = Integer.parseInt(slash) % 8;
        return getStringIntegerIsEven(segment) ?
                calculateNextSegmentEven(segment, slashPlus) :
                calculateNextSegmentOdd(segment);
    }

    private static String calculateNextSegmentEven(String segment, int slashPlus) {
        var newSegment = (Integer.parseInt(segment)) + (256/Math.pow(2, slashPlus)) - 1;
        return String.format("%03d", (int) newSegment);
    }

    private static String calculateNextSegmentOdd(String segment) {
        var newSegment = (Integer.parseInt(segment)) + 1;
        return String.format("%03d", newSegment);
    }

    private static Boolean getStringIntegerIsEven(String stringInteger) {
        return (Integer.parseInt(stringInteger) % 2 == 0);
    }

    public static Boolean checkValidRawCidrString(String candidateRawCidrString) {
        if (Objects.isNull(candidateRawCidrString)) {
            return false;
        }

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
        if (Objects.isNull(candidateFormattedCidrString)) {
            return false;
        }

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

    public static String convertFormattedToRawCidrString(String formattedCidrString) {
        var isValidFormattedCidrString = checkValidFormattedCidrString(formattedCidrString);
        if (!isValidFormattedCidrString) {
            return null;
        }

        var segments = Arrays
                .stream(formattedCidrString.split("[./]"))
                .map(Integer::parseInt)
                .map(p -> Integer.toString(p))
                .toList();

        return String.format("%s.%s.%s.%s/%s",
                segments.get(0),
                segments.get(1),
                segments.get(2),
                segments.get(3),
                segments.get(4));
    }

    public static String convertRawToFormattedCidrString(String rawCidrString) {
        var isValidRawCidrString = checkValidRawCidrString(rawCidrString);
        if (!isValidRawCidrString) {
            return null;
        }

        var stringSegments = rawCidrString.split("[./]");
        var segments = Arrays
                .stream(Arrays.copyOfRange(stringSegments, 0, 4))
                .map(Integer::parseInt)
                .map(p -> String.format("%03d", p))
                .toList();
        var slashSegment = String.format("%02d", Integer.parseInt(stringSegments[4]));
        return String.format("%s.%s.%s.%s/%s",
                segments.get(0),
                segments.get(1),
                segments.get(2),
                segments.get(3),
                slashSegment);
    }

    private static Integer tryParseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
