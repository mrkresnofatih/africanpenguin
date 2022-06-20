package dev.mrkresnofatih.africanpenguin.models.entities.builders;

import dev.mrkresnofatih.africanpenguin.models.entities.Search;

public interface ISearchBuilder {
    ISearchBuilder SetRunning(Boolean isRunning);

    ISearchBuilder Reset();

    ISearchBuilder SetPartitionKey(String partitionKey);

    ISearchBuilder SetSortKey(String sortKey);

    ISearchBuilder SetPartnerId(String partnerId);

    ISearchBuilder SetAutoID();

    Search Build();
}
