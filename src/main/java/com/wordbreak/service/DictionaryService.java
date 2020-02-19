package com.wordbreak.service;

import com.wordbreak.core.api.ApiResult;
import com.wordbreak.entity.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryService {
    Optional<List<String>> search(String str);
    Optional<List<String>> searchByUser(String str);
    Optional<List<String>> searchByUserAndSystem(String str);
    void saveUserStore(List<Dictionary> dtos);
}
