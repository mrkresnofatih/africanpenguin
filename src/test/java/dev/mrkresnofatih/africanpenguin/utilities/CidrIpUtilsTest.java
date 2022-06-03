package dev.mrkresnofatih.africanpenguin.utilities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CidrIpUtilsTest {
    @Test
    void itShouldReturnCorrectStartingIpOnSlash2() {
        var givenFormattedCidr = "010.000.000.000/02";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "000.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash2() {
        var givenFormattedCidr = "000.000.000.000/01";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "127.255.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash4() {
        var givenFormattedCidr = "010.000.000.000/04";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "000.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash4() {
        var givenFormattedCidr = "128.000.000.000/04";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "143.255.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash6() {
        var givenFormattedCidr = "010.000.000.000/06";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "000.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash6() {
        var givenFormattedCidr = "036.000.000.000/06";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "039.255.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash8() {
        var givenFormattedCidr = "010.000.000.000/08";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash8() {
        var givenFormattedCidr = "036.000.000.000/08";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.255.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash10() {
        var givenFormattedCidr = "010.000.000.000/10";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash10() {
        var givenFormattedCidr = "036.192.000.000/10";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.255.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash12() {
        var givenFormattedCidr = "010.000.000.000/12";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash12() {
        var givenFormattedCidr = "036.048.000.000/12";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.063.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash14() {
        var givenFormattedCidr = "010.000.000.000/14";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.000.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash14() {
        var givenFormattedCidr = "036.052.000.000/14";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.055.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash16() {
        var givenFormattedCidr = "010.192.000.000/16";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash16() {
        var givenFormattedCidr = "036.052.000.000/16";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.255.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash18() {
        var givenFormattedCidr = "010.192.000.000/18";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash18() {
        var givenFormattedCidr = "036.052.000.000/18";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.063.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash20() {
        var givenFormattedCidr = "010.192.000.000/20";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash20() {
        var givenFormattedCidr = "036.052.000.000/20";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.015.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash22() {
        var givenFormattedCidr = "010.192.000.000/22";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.000.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash22() {
        var givenFormattedCidr = "036.052.012.000/22";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.015.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash24() {
        var givenFormattedCidr = "010.192.084.000/24";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.084.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash24() {
        var givenFormattedCidr = "036.052.012.000/24";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.012.255";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash26() {
        var givenFormattedCidr = "010.192.084.000/26";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.084.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash26() {
        var givenFormattedCidr = "036.052.012.128/26";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.012.191";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash28() {
        var givenFormattedCidr = "010.192.084.000/28";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.084.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash28() {
        var givenFormattedCidr = "036.052.012.048/28";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.012.063";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash30() {
        var givenFormattedCidr = "010.192.084.000/30";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.084.000";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash30() {
        var givenFormattedCidr = "036.052.012.068/30";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.012.071";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectStartingIpOnSlash32() {
        var givenFormattedCidr = "010.192.084.010/32";
        var calculatedStartingIp = CidrUtils.getStartingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "010.192.084.010";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }

    @Test
    void itShouldReturnCorrectEndingIpOnSlash32() {
        var givenFormattedCidr = "036.052.012.068/32";
        var calculatedStartingIp = CidrUtils.getEndingIpOfCidr(givenFormattedCidr);
        var expectedStartingIp = "036.052.012.068";
        assertThat(calculatedStartingIp).isEqualTo(expectedStartingIp);
    }
}
