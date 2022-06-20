package dev.mrkresnofatih.africanpenguin.models.entities.builders;

import dev.mrkresnofatih.africanpenguin.models.entities.Search;

public class SearchBuilder implements ISearchBuilder {
    public SearchBuilder() {
        search = new Search("", "", "", false);
    }

    public SearchBuilder(Search search) {
        this.search = search;
    }

    private Search search;

    @Override
    public ISearchBuilder SetRunning(Boolean isRunning) {
        search.setRunning(isRunning);
        return this;
    }

    @Override
    public ISearchBuilder SetPartnerId(String partnerId) {
        search.setPartnerId(partnerId);
        return this;
    }

    @Override
    public ISearchBuilder Reset() {
        search = new Search("", "", "", false);
        return this;
    }

    @Override
    public ISearchBuilder SetPartitionKey(String partitionKey) {
        search.setPartitionKey(partitionKey);
        return this;
    }

    @Override
    public ISearchBuilder SetSortKey(String sortKey) {
        search.setSortKey(sortKey);
        return this;
    }

    @Override
    public ISearchBuilder SetAutoID() {
        var partitionKey = "PAR#" + search.getPartnerId();
        var sortKey = "SCH#" + search.getPartnerId();
        search.setPartitionKey(partitionKey);
        search.setSortKey(sortKey);
        return this;
    }

    @Override
    public Search Build() {
        return search;
    }
}
