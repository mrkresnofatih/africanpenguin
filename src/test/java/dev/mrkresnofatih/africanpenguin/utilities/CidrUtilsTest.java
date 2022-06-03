package dev.mrkresnofatih.africanpenguin.utilities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CidrUtilsTest {
    @Test
    void itShouldReturnFalseWhenSegmentsNot5RawCidrStringIsInvalid() {
        var rawCidr = "256.198.0/24";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen1stSegmentRawCidrStringIsInvalid() {
        var rawCidr = "256.198.0.0/24";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen2ndSegmentRawCidrStringIsInvalid() {
        var rawCidr = "10.257.0.0/24";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen3rdSegmentRawCidrStringIsInvalid() {
        var rawCidr = "10.198.258.0/24";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen4thSegmentRawCidrStringIsInvalid() {
        var rawCidr = "10.198.18.261/24";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen5thSegmentRawCidrStringIsInvalid() {
        var rawCidr = "10.198.0.0/33";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnTrueWhenRawCidrStringIsValid() {
        var rawCidr = "10.198.0.0/16";
        var validity = CidrUtils.checkValidRawCidrString(rawCidr);
        assertThat(validity).isTrue();
    }

    @Test
    void itShouldReturnFalseWhenFormattedCidrStringHasSegmentIncorrectLength() {
        var rawCidr = "0010.198.00.000/16";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen1stSegmentFormattedCidrStringIsInvalid() {
        var rawCidr = "10.198.000.000/16";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen2ndSegmentFormattedCidrStringIsInvalid() {
        var rawCidr = "010.98.000.000/16";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen3rdSegmentFormattedCidrStringIsInvalid() {
        var rawCidr = "010.198.20.000/16";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen4thSegmentFormattedCidrStringIsInvalid() {
        var rawCidr = "010.198.20.00/16";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnFalseWhen5thSegmentFormattedCidrStringIsInvalid() {
        var rawCidr = "010.198.20.000/8";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isFalse();
    }

    @Test
    void itShouldReturnTrueWhenFormattedCidrStringIsValid() {
        var rawCidr = "010.198.000.000/16";
        var validity = CidrUtils.checkValidFormattedCidrString(rawCidr);
        assertThat(validity).isTrue();
    }

    @Test
    void itShouldReturnValidRawString() {
        var formattedCidr = "010.194.000.000/08";
        var rawCidr = CidrUtils.convertFormattedToRawCidrString(formattedCidr);

        var isValidRawCidr = CidrUtils.checkValidRawCidrString(rawCidr);
        var expectedRawCidr = "10.194.0.0/8";
        assertThat(rawCidr).isEqualTo(expectedRawCidr);
        assertThat(isValidRawCidr).isTrue();
    }

    @Test
    void itShouldReturnValidFormattedString() {
        var rawCidr = "10.192.0.0/8";
        var formattedCidr = CidrUtils.convertRawToFormattedCidrString(rawCidr);

        var isValidFormattedCidr = CidrUtils.checkValidFormattedCidrString(formattedCidr);
        var expectedFormattedCidr = "010.192.000.000/08";
        assertThat(formattedCidr).isEqualTo(expectedFormattedCidr);
        assertThat(isValidFormattedCidr).isTrue();
    }
}