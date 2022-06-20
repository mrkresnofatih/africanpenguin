package dev.mrkresnofatih.africanpenguin.controllers;

import dev.mrkresnofatih.africanpenguin.models.ResponseHandler;
import dev.mrkresnofatih.africanpenguin.models.ResponsePackage;
import dev.mrkresnofatih.africanpenguin.models.dtos.SearchGetDto;
import dev.mrkresnofatih.africanpenguin.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/search")
public class SearchController {
    private final ISearchService _searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        _searchService = searchService;
    }

    @GetMapping(path = "/init/{partnerId}")
    public ResponsePackage<SearchGetDto> SaveSearch(@PathVariable String partnerId) {
        var res = _searchService.InitSearch(partnerId);
        return new ResponseHandler<SearchGetDto>().WrapSuccess(res);
    }

    @GetMapping(path = "/get/{partnerId}")
    public ResponsePackage<SearchGetDto> GetSearch(@PathVariable String partnerId) {
        var res = _searchService.GetSearch(partnerId);
        return new ResponseHandler<SearchGetDto>().WrapSuccess(res);
    }

    @GetMapping(path = "/run/{partnerId}")
    public ResponsePackage<SearchGetDto> RunSearch(@PathVariable String partnerId) {
        var res = _searchService.UpdateSearch(partnerId, true);
        return new ResponseHandler<SearchGetDto>().WrapSuccess(res);
    }

    @GetMapping(path = "/stop/{partnerId}")
    public ResponsePackage<SearchGetDto> StopSearch(@PathVariable String partnerId) {
        var res = _searchService.UpdateSearch(partnerId, false);
        return new ResponseHandler<SearchGetDto>().WrapSuccess(res);
    }

}
