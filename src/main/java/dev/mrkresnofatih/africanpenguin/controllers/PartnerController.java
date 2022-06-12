package dev.mrkresnofatih.africanpenguin.controllers;

import dev.mrkresnofatih.africanpenguin.models.ResponseHandler;
import dev.mrkresnofatih.africanpenguin.models.ResponsePackage;
import dev.mrkresnofatih.africanpenguin.models.entities.Partner;
import dev.mrkresnofatih.africanpenguin.services.IPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/partner")
public class PartnerController {
    private final IPartnerService _partnerService;

    @Autowired
    public PartnerController(IPartnerService partnerService) {
        _partnerService = partnerService;
    }

    @PostMapping(path = "/save")
    public ResponsePackage<Partner> SavePartner(@Valid @RequestBody Partner partner){
        var res = _partnerService.SavePartner(partner);
        return new ResponseHandler<Partner>().WrapSuccess(partner);
    }
}
