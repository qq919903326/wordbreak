package com.wordbreak.service;

import com.wordbreak.core.api.ApiResult;

public interface DictionaryService {
    ApiResult search(String str);
    public ApiResult save(String str);
}
