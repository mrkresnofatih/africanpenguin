package dev.mrkresnofatih.africanpenguin.utilities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CidrIpSearchUtilsTest {
    @Test
    void itShouldReturnCorrectFreeCidrRangeOnBiggerSlash() {
        var occupiedCidrRanges = List
                .of(
                        "010.000.000.000/16",
                        "010.001.000.000/16",
                        "010.002.000.000/17",
                        "010.002.128.000/17",
                        "010.003.000.000/18",
                        "010.004.000.000/16",
                        "010.005.000.000/17",
                        "010.007.000.000/16"
                );
        var slash = 17;
        var start = "010.000.000.000";
        var stop = "010.008.000.000";
        var calculatedFreeCidr = CidrUtils
                .getFreeCidrFromSlash(occupiedCidrRanges, slash, start, stop);
        assertThat(calculatedFreeCidr)
                .containsExactly(
                        "010.003.128.000/17",
                        "010.005.128.000/17",
                        "010.006.000.000/17",
                        "010.006.128.000/17"
                );
    }

    @Test
    void itShouldReturnCorrectFreeCidrRangeOnMuchBiggerSlash() {
        var occupiedCidrRanges = List
                .of(
                        "010.000.000.000/16",
                        "010.001.000.000/16",
                        "010.002.000.000/17",
                        "010.002.128.000/17",
                        "010.003.000.000/18",
                        "010.004.000.000/16",
                        "010.005.000.000/17",
                        "010.007.000.000/16",
                        "010.009.000.000/16"
                );
        var slash = 16;
        var start = "010.000.000.000";
        var stop = "010.010.000.000";
        var calculatedFreeCidr = CidrUtils
                .getFreeCidrFromSlash(occupiedCidrRanges, slash, start, stop);
        assertThat(calculatedFreeCidr)
                .containsExactly(
                        "010.006.000.000/16",
                        "010.008.000.000/16"
                );
    }

    @Test
    void itShouldReturnCorrectFreeCidrRangeOnSmallerSlash() {
        var occupiedCidrRanges = List
                .of(
                        "010.000.000.000/16",
                        "010.001.000.000/16",
                        "010.002.000.000/17",
                        "010.002.128.000/17",
                        "010.003.000.000/18",
                        "010.004.000.000/16",
                        "010.005.000.000/17",
                        "010.006.000.000/16"
                );
        var slash = 18;
        var start = "010.000.000.000";
        var stop = "010.007.000.000";
        var calculatedFreeCidr = CidrUtils
                .getFreeCidrFromSlash(occupiedCidrRanges, slash, start, stop);
        assertThat(calculatedFreeCidr)
                .containsExactly(
                        "010.003.064.000/18",
                        "010.003.128.000/18",
                        "010.003.192.000/18",
                        "010.005.128.000/18",
                        "010.005.192.000/18"
                );
    }

    @Test
    void itShouldReturnCorrectFreeCidrRangeMismatchStartAndStop() {
        var occupiedCidrRanges = List
                .of(
                        "010.001.000.000/16",
                        "010.002.000.000/17",
                        "010.002.128.000/17",
                        "010.003.000.000/18",
                        "010.004.000.000/16",
                        "010.005.000.000/17",
                        "010.007.000.000/16",
                        "010.009.000.000/16"
                );
        var slash = 17;
        var start = "010.000.000.000";
        var stop = "010.011.000.000";
        var calculatedFreeCidr = CidrUtils
                .getFreeCidrFromSlash(occupiedCidrRanges, slash, start, stop);
        assertThat(calculatedFreeCidr)
                .containsExactly(
                        "010.000.000.000/17",
                        "010.000.128.000/17",
                        "010.003.128.000/17",
                        "010.005.128.000/17",
                        "010.006.000.000/17",
                        "010.006.128.000/17",
                        "010.008.000.000/17",
                        "010.008.128.000/17",
                        "010.010.000.000/17",
                        "010.010.128.000/17"
                );
    }
}
