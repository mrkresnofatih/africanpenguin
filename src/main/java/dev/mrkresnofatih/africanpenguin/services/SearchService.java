package dev.mrkresnofatih.africanpenguin.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import dev.mrkresnofatih.africanpenguin.models.dtos.SearchGetDto;
import dev.mrkresnofatih.africanpenguin.models.entities.builders.SearchBuilder;
import dev.mrkresnofatih.africanpenguin.utilities.DynamoAttributeMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchService implements ISearchService {
    private final AmazonDynamoDB _amazonDynamoDB;

    @Autowired
    public SearchService(AmazonDynamoDB amazonDynamoDB) {
        _amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public SearchGetDto InitSearch(String partnerId) {
        return SaveSearch(partnerId, false);
    }

    @Override
    public SearchGetDto SaveSearch(String partnerId, Boolean running) {
        var record = new SearchBuilder()
                .SetPartnerId(partnerId)
                .SetRunning(running)
                .SetAutoID()
                .Build();
        Map<String, AttributeValue> map = new DynamoAttributeMapBuilder()
                .PutString("pk", record.getPartitionKey())
                .PutString("sk", record.getSortKey())
                .PutBool("running", record.getRunning())
                .PutString("partnerId", record.getPartnerId())
                .Build();
        var t = new PutItemRequest()
                .withTableName("apenguintb")
                .withItem(map);
        _amazonDynamoDB.putItem(t);
        return record.ToSearchGetDto();
    }

    @Override
    public SearchGetDto GetSearch(String partnerId) {
        var partitionKey = "PAR#" + partnerId;
        var sortKey = "SCH#" + partnerId;
        var primaryKey = new DynamoAttributeMapBuilder()
                .PutString("pk", partitionKey)
                .PutString("sk", sortKey)
                .Build();
        var t = new GetItemRequest()
                .withTableName("apenguintb")
                .withKey(primaryKey);
        var response = _amazonDynamoDB.getItem(t);
        var map = response.getItem();
        return new SearchGetDto(map.get("partnerId").getS(), map.get("running").getBOOL());
    }

    @Override
    public SearchGetDto UpdateSearch(String partnerId, Boolean running) {
        var found = GetSearch(partnerId);
        found.setRunning(running);
        return SaveSearch(partnerId, running);
    }

    @Override
    public String DeleteSearch(String partnerId) {
        var partitionKey = "PAR#" + partnerId;
        var sortKey = "SCH#" + partnerId;
        var primaryKey = new DynamoAttributeMapBuilder()
                .PutString("pk", partitionKey)
                .PutString("sk", sortKey)
                .Build();
        var t = new DeleteItemRequest()
                .withTableName("apenguintb")
                .withKey(primaryKey);
        _amazonDynamoDB.deleteItem(t);
        return partnerId;
    }
}
