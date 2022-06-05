package dev.mrkresnofatih.africanpenguin.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.lang.Math;

public class CidrUtils {
    public static List<String> getFreeCidrFromSlash(List<String> occupiedCidrRanges, int slash) {
        var freeCIDRs = new ArrayList<String>();
        for (int index = 0; index < occupiedCidrRanges.size() - 1; index++) {
            var currentCidr = occupiedCidrRanges.get(index);
            var nextCidr = occupiedCidrRanges.get(index + 1);

            var startOfCurrentCidr = getStartingIpOfCidr(currentCidr);
            var endOfCurrentCidr = getEndingIpOfCidr(currentCidr);
            var startOfNextCidr = getStartingIpOfCidr(nextCidr);

            var mismatchWithNextStart = !getNextIp(endOfCurrentCidr).equals(startOfNextCidr);

            while (mismatchWithNextStart) {
                var slashOfCurrentCidr = _getSlashFromCidr(currentCidr);
                if (slash <= slashOfCurrentCidr) {
                    var supposedCidr = _getCidrFromStartingIp(startOfCurrentCidr, slash);
                    var endingIpOfSupposedCidr = getEndingIpOfCidr(supposedCidr);
                    var nextIpOfEndingIpOfSupposedCidr = getNextIp(endingIpOfSupposedCidr);

                    var draftCidr = _getCidrFromStartingIp(nextIpOfEndingIpOfSupposedCidr, slash);
                    var startOfDraft = getStartingIpOfCidr(draftCidr);
                    var endOfDraft = getEndingIpOfCidr(draftCidr);

                    var startOfDraftFitsSlot = getIpIsBetween(startOfDraft, endOfCurrentCidr, startOfNextCidr);
                    var endOfDraftFitsSlot = getIpIsBetween(endOfDraft, endOfCurrentCidr, startOfNextCidr);

                    if (startOfDraftFitsSlot && endOfDraftFitsSlot) {
                        freeCIDRs.add(draftCidr);
                        currentCidr = draftCidr;
                        startOfCurrentCidr = startOfDraft;
                        endOfCurrentCidr = endOfDraft;
                        mismatchWithNextStart = !getNextIp(endOfCurrentCidr).equals(startOfNextCidr);
                    } else {
                        break;
                    }
                } else {
                    var nextIpOfEndingIpOfCurrentCidr = getNextIp(endOfCurrentCidr);

                    var draftCidr = _getCidrFromStartingIp(nextIpOfEndingIpOfCurrentCidr, slash);
                    var startOfDraft = getStartingIpOfCidr(draftCidr);
                    var endOfDraft = getEndingIpOfCidr(draftCidr);

                    var startOfDraftFitsSlot = getIpIsBetween(startOfDraft, endOfCurrentCidr, startOfNextCidr);
                    var endOfDraftFitsSlot = getIpIsBetween(endOfDraft, endOfCurrentCidr, startOfNextCidr);

                    if (startOfDraftFitsSlot && endOfDraftFitsSlot) {
                        freeCIDRs.add(draftCidr);
                        currentCidr = draftCidr;
                        startOfCurrentCidr = startOfDraft;
                        endOfCurrentCidr = endOfDraft;
                        mismatchWithNextStart = !getNextIp(endOfCurrentCidr).equals(startOfNextCidr);
                    } else {
                        break;
                    }
                }

            }
        }
        return freeCIDRs;
    }

    private static String _getCidrFromStartingIp(String startingIp, int slash) {
        return String.format("%s/%02d", startingIp, slash);
    }

    public static Boolean getIpIsBetween(String ip, String bottomIp, String topIp) {
        var pseudoValueBottom = _getPseudoValueOfIp(bottomIp);
        var pseudoValueTop = _getPseudoValueOfIp(topIp);
        var pseudoValueIp = _getPseudoValueOfIp(ip);
        return (pseudoValueIp > pseudoValueBottom && pseudoValueIp < pseudoValueTop);
    }

    private static Long _getPseudoValueOfIp(String ip) {
        var segments = ip.split("[./]");
        var pseudoValue = 0L;
        var segmentCounter = 0;
        for (var segment : segments) {
            pseudoValue += Long.parseLong(segment)*((long) Math.pow(1000, 3 - segmentCounter));
            segmentCounter++;
        }
        return pseudoValue;
    }

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
        var slash = _getSlashFromCidr(formattedCidr);
        var numberOfHosts = (long) Math.pow(2, 32-slash);
        return String.valueOf(numberOfHosts);
    }

    private static Integer _getSlashFromCidr(String formattedCidr) {
        var slashInString = formattedCidr.substring(16);
        return Integer.parseInt(slashInString);
    }

    public static String getStartingIpOfCidr(String formattedCidr) {
        var segments = formattedCidr.split("[./]");
        var slash = _getSlashFromCidr(formattedCidr);
        var identicalStartingDigits = slash / 8;
        var slashHasRemainder = (slash % 8 != 0);
        return switch (identicalStartingDigits) {
            case 0 -> slashHasRemainder ?
                    String.format("%s.000.000.000", segments[0]) :
                    "000.000.000.000";
            case 1 -> slashHasRemainder ?
                    String.format("%s.%s.000.000", segments[0], segments[1]) :
                    String.format("%s.000.000.000", segments[0]);
            case 2 -> slashHasRemainder ?
                    String.format("%s.%s.%s.000", segments[0], segments[1], segments[2]) :
                    String.format("%s.%s.000.000", segments[0], segments[1]);
            case 3 -> slashHasRemainder ?
                    String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], segments[3]) :
                    String.format("%s.%s.%s.000", segments[0], segments[1], segments[2]);
            default -> String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], segments[3]);
        };
    }

    public static String getEndingIpOfCidr(String formattedCidr) {
        var segments = formattedCidr.split("[./]");
        var slash = _getSlashFromCidr(formattedCidr);
        var identicalStartingDigits = slash / 8;
        return switch (identicalStartingDigits) {
            case 0 -> String.format("%s.255.255.255", _getNextSegment(slash, segments[0]));
            case 1 -> String.format("%s.%s.255.255", segments[0], _getNextSegment(slash, segments[1]));
            case 2 -> String.format("%s.%s.%s.255", segments[0], segments[1], _getNextSegment(slash, segments[2]));
            case 3 -> String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], _getNextSegment(slash, segments[3]));
            default -> String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], segments[3]);
        };
    }

    private static String _getNextSegment(int slash, String segment) {
        var slashPlus = slash % 8;
        return _getStringIntegerIsEven(segment) ?
                _calculateNextSegmentEven(segment, slashPlus) :
                _calculateNextSegmentOdd(segment);
    }

    private static String _calculateNextSegmentEven(String segment, int slashPlus) {
        var newSegment = (Integer.parseInt(segment)) + (256/Math.pow(2, slashPlus)) - 1;
        return String.format("%03d", (int) newSegment);
    }

    private static String _calculateNextSegmentOdd(String segment) {
        var newSegment = (Integer.parseInt(segment)) + 1;
        return String.format("%03d", newSegment);
    }

    private static Boolean _getStringIntegerIsEven(String stringInteger) {
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
            var parsed = _tryParseInteger(segment);
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

    private static Integer _tryParseInteger(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
