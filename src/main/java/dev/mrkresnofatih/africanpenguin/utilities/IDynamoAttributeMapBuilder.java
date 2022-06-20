package dev.mrkresnofatih.africanpenguin.utilities;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.Map;

public interface IDynamoAttributeMapBuilder {
    IDynamoAttributeMapBuilder PutString(String attributeName, String attributeValue);

    IDynamoAttributeMapBuilder PutBool(String attributeName, Boolean attributeValue);

    Map<String, AttributeValue> Build();

    IDynamoAttributeMapBuilder Reset();
}
