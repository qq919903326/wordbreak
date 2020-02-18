package com.wordbreak.service.impl;

import com.wordbreak.constant.Constant;
import com.wordbreak.core.api.ApiResult;
import com.wordbreak.core.api.ApiResultCodeMsg;
import com.wordbreak.core.api.ApiResultGenerator;
import com.wordbreak.entity.Dictionary;
import com.wordbreak.service.DictionaryService;
import com.wordbreak.util.DictionarySearchUtil;
import com.wordbreak.util.GsonProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {
    /**
     * 查询字典是否存在数据
     * @param str
     * @return
     */
    @Override
    public ApiResult search(String str) {
        List<String> result = DictionarySearchUtil.dpSearch(str);
        if(result.isEmpty()){
            log.info("字符串中含有单词不存在字典");
            return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND);
        }
        return ApiResultGenerator.success(result);
    }

    /**
     * 存
     * @param str
     * @return
     */
    @Override
    public ApiResult save(String str){
        Map<String,String> map = GsonProcessor.getInstance().fromJson(str,Map.class);
        Optional<Dictionary> dictionaryOptional = DictionarySearchUtil.dictionaryContains(map.get("str"));
        if(dictionaryOptional.isPresent()){
            log.info("字符串已存在字典");
            return ApiResultGenerator.error(ApiResultCodeMsg.EXIST_FOUND);
        }
        log.info("保存成功");
        Constant.DICTIONARIES.add(new Dictionary(map.get("str")));
        return ApiResultGenerator.success();
    }
}
