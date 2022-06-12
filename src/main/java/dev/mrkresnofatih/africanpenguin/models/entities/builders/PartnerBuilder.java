package dev.mrkresnofatih.africanpenguin.models.entities.builders;

import dev.mrkresnofatih.africanpenguin.models.entities.Partner;

public class PartnerBuilder implements IPartnerBuilder {
    public PartnerBuilder() {
        partner = new Partner("", "");
    }

    public PartnerBuilder(Partner partner) {
        this.partner = partner;
    }

    private Partner partner;

    @Override
    public IPartnerBuilder Reset() {
        partner = new Partner("", "");
        return this;
    }

    @Override
    public IPartnerBuilder SetPartnerId(String partnerId) {
        partner.setPartnerId(partnerId);
        return this;
    }

    @Override
    public IPartnerBuilder SetPartnerName(String partnerName) {
        partner.setPartnerName(partnerName);
        return this;
    }

    @Override
    public IPartnerBuilder SetPartitionKey(String partitionKey) {
        partner.setPartitionKey(partitionKey);
        return this;
    }

    @Override
    public IPartnerBuilder SetSortKey(String sortKey) {
        partner.setSortKey(sortKey);
        return this;
    }

    @Override
    public IPartnerBuilder SetAutoID() {
        var autoKey = "PAR#" + partner.getPartnerId();
        partner.setPartitionKey(autoKey);
        partner.setSortKey(autoKey);
        return this;
    }

    @Override
    public Partner Build() {
        return this.partner;
    }
}

