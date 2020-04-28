package com.example.restservice.service;

import java.util.List;
import java.util.Optional;

public interface ALTOResourceBaseService<T> {

    List<T> getAll();
    Optional<T> get(String resourceId, String resourceVersion);
}
