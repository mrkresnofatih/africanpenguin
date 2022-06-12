package dev.mrkresnofatih.africanpenguin.services;

import dev.mrkresnofatih.africanpenguin.models.entities.Partner;

public interface IPartnerService {
    Partner SavePartner(Partner partner);

    Partner GetPartner(String partnerId);

    void DeletePartner(String partnerId);
}
