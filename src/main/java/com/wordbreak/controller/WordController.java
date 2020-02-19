package com.wordbreak.controller;


import com.wordbreak.core.api.ApiResult;
import com.wordbreak.core.api.ApiResultCodeMsg;
import com.wordbreak.core.api.ApiResultGenerator;
import com.wordbreak.entity.Dictionary;
import com.wordbreak.service.DictionaryService;
import com.wordbreak.util.GsonProcessor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author hwh
 * @since 2020-01-09
 */
@Log
@Validated
@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     *  使用系统库
     *  同步查询接口
     * @param str
     * @return
     */
    @GetMapping("/searchSystemStore/{str}")
    public ApiResult searchSystemStore(@PathVariable String str){
        Optional<List<String>> optional = dictionaryService.search(str);
        if(optional.isPresent()){
            return ApiResultGenerator.success(optional.get());
        }
        return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND,new ArrayList<>());
    }

    /**
     *  使用系统库
     *  同步查询接口
     * @param str
     * @return
     */
    @GetMapping("/searchUserStore/{str}")
    public ApiResult searchUserStore(@PathVariable String str){
        Optional<List<String>> optional =  dictionaryService.searchByUser(str);
        if(optional.isPresent()){
            return ApiResultGenerator.success(optional.get());
        }
        return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND,new ArrayList<>());
    }

    /**
     *  使用系统库和自定义库
     *  同步查询接口
     * @param str
     * @return
     */
    @GetMapping("/searchSystemAndUserStore/{str}")
    public ApiResult searchSystemAndUserStore(@PathVariable String str){
        Optional<List<String>> optional = dictionaryService.searchByUserAndSystem(str);
        if(optional.isPresent()){
            return ApiResultGenerator.success(optional.get());
        }
        return ApiResultGenerator.error(ApiResultCodeMsg.NOT_FOUND,new ArrayList<>());
    }

    /**
     * 存入字典
     * 输入格式
     * 	[{"dict":"a"},{"dict":"b"}]
     * @param str
     * @return
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody String str){
        //TODO 检验格式
        Dictionary[] dtos = GsonProcessor.getInstance().fromJson(str,Dictionary[].class);
        dictionaryService.saveUserStore(Arrays.asList(dtos));
        return ApiResultGenerator.success();
    }
}
