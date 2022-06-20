package dev.mrkresnofatih.africanpenguin.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerCreateDto;
import dev.mrkresnofatih.africanpenguin.models.dtos.PartnerGetDto;
import dev.mrkresnofatih.africanpenguin.models.entities.builders.PartnerBuilder;
import dev.mrkresnofatih.africanpenguin.utilities.DynamoAttributeMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PartnerService implements IPartnerService {
    private final AmazonDynamoDB _amazonDynamoDB;

    @Autowired
    public PartnerService(AmazonDynamoDB amazonDynamoDB) {
        _amazonDynamoDB = amazonDynamoDB;
    }

    @Override
    public PartnerGetDto SavePartner(PartnerCreateDto partnerCreateDto) {
        var record = new PartnerBuilder()
                .SetPartnerId(partnerCreateDto.getPartnerId())
                .SetPartnerName(partnerCreateDto.getPartnerName())
                .SetAutoID()
                .Build();
        Map<String, AttributeValue> map = new DynamoAttributeMapBuilder()
                .PutString("pk", record.getPartitionKey())
                .PutString("sk", record.getSortKey())
                .PutString("partnerId", record.getPartnerId())
                .PutString("partnerName", record.getPartnerName())
                .Build();
        var t = new PutItemRequest()
                .withTableName("apenguintb")
                .withItem(map);
        _amazonDynamoDB.putItem(t);
        return record.ToPartnerGetDto();
    }

    @Override
    public PartnerGetDto GetPartner(String partnerId) {
        var partitionSort = "PAR#" + partnerId;
        var primaryKey = new DynamoAttributeMapBuilder()
                .PutString("pk", partitionSort)
                .PutString("sk", partitionSort)
                .Build();
        var t = new GetItemRequest()
                .withTableName("apenguintb")
                .withKey(primaryKey);
        var response = _amazonDynamoDB.getItem(t);
        var map = response.getItem();
        return new PartnerGetDto(map.get("partnerId").getS(), map.get("partnerName").getS());
    }

    @Override
    public String DeletePartner(String partnerId) {
        var partitionSort = "PAR#" + partnerId;
        var primaryKey = new DynamoAttributeMapBuilder()
                .PutString("pk", partitionSort)
                .PutString("sk", partitionSort)
                .Build();
        var t = new DeleteItemRequest()
                .withTableName("apenguintb")
                .withKey(primaryKey);
        _amazonDynamoDB.deleteItem(t);
        return partnerId;
    }
}
