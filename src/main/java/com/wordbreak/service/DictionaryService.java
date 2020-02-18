package com.wordbreak.service;

import com.wordbreak.core.api.ApiResult;
import com.wordbreak.entity.Dictionary;

import java.util.List;

public interface DictionaryService {
    ApiResult search(String str);
    ApiResult searchByUser(String str);
    ApiResult searchByUserAndSystem(String str);
    ApiResult  saveUserStore(List<Dictionary> dtos);
}
