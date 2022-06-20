package dev.mrkresnofatih.africanpenguin.models.dtos;

public class PartnerGetDto {
    private String PartnerId;

    private String PartnerName;

    public PartnerGetDto() {
    }

    public PartnerGetDto(String partnerId, String partnerName) {
        PartnerId = partnerId;
        PartnerName = partnerName;
    }

    public String getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(String partnerId) {
        PartnerId = partnerId;
    }

    public String getPartnerName() {
        return PartnerName;
    }

    public void setPartnerName(String partnerName) {
        PartnerName = partnerName;
    }
}
