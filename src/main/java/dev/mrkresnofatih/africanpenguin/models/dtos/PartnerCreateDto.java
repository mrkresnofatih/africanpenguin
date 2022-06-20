package dev.mrkresnofatih.africanpenguin.models.dtos;

import javax.validation.constraints.NotBlank;

public class PartnerCreateDto {
    public PartnerCreateDto(){}

    public PartnerCreateDto(String partnerId, String partnerName) {
        PartnerId = partnerId;
        PartnerName = partnerName;
    }

    @NotBlank
    private String PartnerId;

    @NotBlank
    private String PartnerName;

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
