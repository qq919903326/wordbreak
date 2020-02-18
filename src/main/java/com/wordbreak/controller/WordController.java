package com.wordbreak.controller;


import com.wordbreak.core.api.ApiResult;
import com.wordbreak.core.api.ApiResultGenerator;
import com.wordbreak.service.DictionaryService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hwh
 * @since 2020-01-09
 */
@Log
@RestController
@RequestMapping("/word")
public class WordController {

    @Autowired
    DictionaryService dictionaryService;

    public static ExecutorService FIXED_THREAD_POOL = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    /**
     * 异步查询接口
     * @param str
     * @return
     */
    @GetMapping("/searchSync/{str}")
    public DeferredResult<ApiResult> searchSync(@PathVariable String str){
        //设置超时时间
        DeferredResult<ApiResult> deferredResult = new DeferredResult<>(10*1000L);
        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info("异步执行结束");
            }
        });
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.info("异步执行超时");
            }
        });
        FIXED_THREAD_POOL.execute(new Runnable() {
            @Override
            public void run() {
                deferredResult.setResult(dictionaryService.search(str));
            }
        });
        return deferredResult;
    }

    /**
     *  同步查询接口
     * @param str
     * @return
     */
    @GetMapping("/search/{str}")
    public ApiResult search(@PathVariable String str){
        return dictionaryService.search(str);
    }

    /**
     * 存入字典
     * @param str
     * @return
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody String str){
        return dictionaryService.save(str);
    }
}
