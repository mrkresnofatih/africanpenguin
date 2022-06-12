package dev.mrkresnofatih.africanpenguin.models.entities;

public abstract class DynamoEntity {
    private String PartitionKey;

    private String SortKey;

    public DynamoEntity() {
    }

    public DynamoEntity(String primaryKey, String sortKey) {
        PartitionKey = primaryKey;
        SortKey = sortKey;
    }

    public String getPartitionKey() {
        return PartitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        PartitionKey = partitionKey;
    }

    public String getSortKey() {
        return SortKey;
    }

    public void setSortKey(String sortKey) {
        SortKey = sortKey;
    }
}
