package com.wordbreak.service.impl;

import com.wordbreak.core.api.ApiResult;
import com.wordbreak.core.api.ApiResultCodeMsg;
import com.wordbreak.core.api.ApiResultGenerator;
import com.wordbreak.entity.Dictionary;
import com.wordbreak.repositories.Storage;
import com.wordbreak.service.DictionaryService;
import com.wordbreak.util.DictionarySearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {
    /**
     * 使用系统字典查询
     * 查询字典是否存在数据
     * @param str
     * @return
     */
    @Override
    public ApiResult search(String str) {
        List<String> result = DictionarySearchUtil.dpSearch(str,Storage.DICTIONARIES);
        if(result.isEmpty()){
            log.info("字符串中含有单词不存在字典");
            return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND,new ArrayList<>());
        }
        return ApiResultGenerator.success(result);
    }
    /**
     * 使用自定义字典查询
     * 查询字典是否存在数据
     * @param str
     * @return
     */
    @Override
    public ApiResult searchByUser(String str) {
        List<String> result = DictionarySearchUtil.dpSearch(str,Storage.USER_DICTIONARIES);
        if(result.isEmpty()){
            log.info("字符串中含有单词不存在字典");
            return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND,new ArrayList<>());
        }
        return ApiResultGenerator.success(result);
    }
    /**
     * 使用两个字典库查询
     * 查询字典是否存在数据
     * @param str
     * @return
     */
    @Override
    public ApiResult searchByUserAndSystem(String str) {
        List<String> result = DictionarySearchUtil.dpSearch(str,Storage.USER_DICTIONARIES,Storage.DICTIONARIES);
        if(result.isEmpty()){
            log.info("字符串中含有单词不存在字典");
            return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND,new ArrayList<>());
        }
        return ApiResultGenerator.success(result);
    }

    /**
     * 存
     * @param dtos
     * @return
     */
    @Override
    public ApiResult saveUserStore(List<Dictionary> dtos){
        dtos.forEach(l->{
            Storage.USER_DICTIONARIES.add(l);
        });
        return ApiResultGenerator.success();
    }
}
