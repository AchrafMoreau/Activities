package com.activity.Activity.utils;

import com.activity.Activity.model.Participant;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ParticipantConverter implements AttributeConverter<Participant, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Participant attribute) {
        try{
            return objectMapper.writeValueAsString(attribute);
        }catch (Exception e){
            throw new IllegalArgumentException("Error converting Participant to JSON", e);
        }
    }

    @Override
    public Participant convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Participant.class);
        }catch (Exception e){
            throw new IllegalArgumentException("Error converting JSON to Participant", e);
        }
    }
}
