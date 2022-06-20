package dev.mrkresnofatih.africanpenguin.services;

import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerCreateDto;
import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerGetDto;
import dev.mrkresnofatih.africanpenguin.models.entities.Partner;

public interface IPartnerService {
    PartnerGetDto SavePartner(PartnerCreateDto partnerCreateDto);

    PartnerGetDto GetPartner(String partnerId);

    String DeletePartner(String partnerId);
}
