package com.example.restservice.serializer;

import com.example.restservice.entities.HashPIDNetworkMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class HashPIDNetworkMapSerializer implements JsonSerializer<HashPIDNetworkMap> {

    @Override
    public JsonElement serialize(HashPIDNetworkMap hashPIDNetworkMap, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", hashPIDNetworkMap.getName());
        return jsonObject;
    }
}
