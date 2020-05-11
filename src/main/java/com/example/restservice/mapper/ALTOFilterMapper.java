package com.example.restservice.mapper;

public interface ALTOFilterMapper<ALTOFilterDTOType, ALTOProjectionType> {
    ALTOProjectionType mapFrom(ALTOFilterDTOType altoFilter);
}
