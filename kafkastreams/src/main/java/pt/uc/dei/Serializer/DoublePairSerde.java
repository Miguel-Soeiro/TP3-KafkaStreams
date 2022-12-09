package pt.uc.dei.Serializer;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DoublePairSerde
        implements Serde<DoublePair>, Serializer<DoublePair>, Deserializer<DoublePair> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Nothing to configure
    }

    @Override
    public void close() {
        // Nothing to close
    }

    @Override
    public byte[] serialize(String topic, DoublePair data) {
        try {
            System.out.println("Serializing...");

            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing DoublePair object", e);
        }
    }

    @Override
    public DoublePair deserialize(String topic, byte[] data) {
        try {
            System.out.println("Desserializing...");
            return objectMapper.readValue(data, DoublePair.class);
        } catch (IOException e) {
            throw new SerializationException("Error deserializing DoublePair object", e);
        }
    }

    @Override
    public Serializer<DoublePair> serializer() {
        return this;
    }

    @Override
    public Deserializer<DoublePair> deserializer() {
        return this;
    }
}