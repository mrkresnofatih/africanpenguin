package dev.mrkresnofatih.africanpenguin.services;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import dev.mrkresnofatih.africanpenguin.models.entities.Partner;
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
    public Partner SavePartner(Partner partner) {
        var record = new PartnerBuilder(partner)
                .SetAutoID()
                .Build();
        Map<String, AttributeValue> map = new DynamoAttributeMapBuilder()
                .PutString("pk", record.getPartitionKey())
                .PutString("sk", record.getSortKey())
                .PutString("partnerId", record.getPartnerId())
                .PutString("partnerName", record.getPartnerName())
                .Build();
        var t = new PutItemRequest()
                .withTableName("africanpenguindb")
                .withItem(map);
        _amazonDynamoDB.putItem(t);
        return partner;
    }

    @Override
    public Partner GetPartner(String partnerId) {
        return null;
    }

    @Override
    public void DeletePartner(String partnerId) {

    }
}
