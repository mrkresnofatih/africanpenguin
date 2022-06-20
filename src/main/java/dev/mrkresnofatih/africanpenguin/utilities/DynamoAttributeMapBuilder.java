package dev.mrkresnofatih.africanpenguin.utilities;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

public class DynamoAttributeMapBuilder implements IDynamoAttributeMapBuilder {
    private Map<String, AttributeValue> _attributeValueMap;

    public DynamoAttributeMapBuilder() {
        _attributeValueMap = new HashMap<>();
    }

    @Override
    public IDynamoAttributeMapBuilder Reset() {
        _attributeValueMap = new HashMap<>();
        return this;
    }

    @Override
    public IDynamoAttributeMapBuilder PutString(String attributeName, String attributeValue) {
        var attribute = new AttributeValue().withS(attributeValue);
        _attributeValueMap.put(attributeName, attribute);
        return this;
    }

    @Override
    public IDynamoAttributeMapBuilder PutBool(String attributeName, Boolean attributeValue) {
        var attribute = new AttributeValue().withBOOL(attributeValue);
        _attributeValueMap.put(attributeName, attribute);
        return this;
    }

    @Override
    public Map<String, AttributeValue> Build() {
        return _attributeValueMap;
    }
}
