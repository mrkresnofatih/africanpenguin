package dev.mrkresnofatih.africanpenguin.services;

import dev.mrkresnofatih.africanpenguin.models.dtos.SearchGetDto;

public interface ISearchService {
    SearchGetDto InitSearch(String partnerId);

    SearchGetDto SaveSearch(String partnerId, Boolean running);

    SearchGetDto GetSearch(String partnerId);

    SearchGetDto UpdateSearch(String partnerId, Boolean running);

    String DeleteSearch(String partnerId);
}
