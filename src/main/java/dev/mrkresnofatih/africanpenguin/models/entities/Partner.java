package dev.mrkresnofatih.africanpenguin.models.entities;

import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerGetDto;

import javax.validation.constraints.NotBlank;

public class Partner extends DynamoEntity {
    public Partner() {
        super();
    }

    public Partner(String partitionKey, String sortKey) {
        super(partitionKey, sortKey);
    }

    public Partner(String partitionKey, String sortKey,
                   String partnerId, String partnerName) {
        super(partitionKey, sortKey);
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

    public PartnerGetDto ToPartnerGetDto() {
        return new PartnerGetDto(PartnerId, PartnerName);
    }


}
