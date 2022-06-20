package dev.mrkresnofatih.africanpenguin.models.entities.builders;

import dev.mrkresnofatih.africanpenguin.models.entities.Partner;

public interface IPartnerBuilder {
    IPartnerBuilder SetPartnerId(String partnerId);

    IPartnerBuilder SetPartnerName(String partnerName);

    IPartnerBuilder SetPartitionKey(String partitionKey);

    IPartnerBuilder SetSortKey(String sortKey);

    IPartnerBuilder SetAutoID();

    Partner Build();

    IPartnerBuilder Reset();
}
