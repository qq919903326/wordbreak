package com.wordbreak.service.impl;

import com.wordbreak.entity.Dictionary;
import com.wordbreak.repositories.Storage;
import com.wordbreak.service.DictionaryService;
import com.wordbreak.util.DictionarySearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<List<String>> search(String str) {
        return DictionarySearchUtil.dpSearch(str,Storage.DICTIONARIES);
    }
    /**
     * 使用自定义字典查询
     * 查询字典是否存在数据
     * @param str
     * @return
     */
    @Override
    public Optional<List<String>> searchByUser(String str) {
        return DictionarySearchUtil.dpSearch(str,Storage.USER_DICTIONARIES);
    }
    /**
     * 使用两个字典库查询
     * 查询字典是否存在数据
     * @param str
     * @return
     */
    @Override
    public Optional<List<String>> searchByUserAndSystem(String str) {
        return DictionarySearchUtil.dpSearch(str,Storage.USER_DICTIONARIES,Storage.DICTIONARIES);
    }

    /**
     * 存
     * @param dtos
     * @return
     */
    @Override
    public void saveUserStore(List<Dictionary> dtos){
        dtos.forEach(l->{
            Storage.USER_DICTIONARIES.add(l);
        });
    }
}
