package dev.mrkresnofatih.africanpenguin.controllers;

import dev.mrkresnofatih.africanpenguin.models.ResponseHandler;
import dev.mrkresnofatih.africanpenguin.models.ResponsePackage;
import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerCreateDto;
import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerGetDto;
import dev.mrkresnofatih.africanpenguin.services.IPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponsePackage<PartnerGetDto> SavePartner(@Valid @RequestBody PartnerCreateDto partnerCreateDto){
        var res = _partnerService.SavePartner(partnerCreateDto);
        return new ResponseHandler<PartnerGetDto>().WrapSuccess(res);
    }

    @GetMapping(path = "/get/{partnerId}")
    public ResponsePackage<PartnerGetDto> GetPartner(@PathVariable String partnerId) {
        var res = _partnerService.GetPartner(partnerId);
        return new ResponseHandler<PartnerGetDto>().WrapSuccess(res);
    }

    @GetMapping(path = "/delete/{partnerId}")
    public ResponsePackage<String> DeletePartner(@PathVariable String partnerId) {
        var res = _partnerService.DeletePartner(partnerId);
        return new ResponseHandler<String>().WrapSuccess(res);
    }
}
