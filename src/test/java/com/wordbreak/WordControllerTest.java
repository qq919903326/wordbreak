package com.wordbreak;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wordbreak.controller.WordController;
import com.wordbreak.core.api.ApiResult;
import com.wordbreak.core.api.ApiResultCodeMsg;
import com.wordbreak.core.api.ApiResultGenerator;
import com.wordbreak.entity.Dictionary;
import com.wordbreak.repositories.Storage;
import com.wordbreak.run.CompanyApplication;
import com.wordbreak.service.DictionaryService;
import com.wordbreak.util.DictionarySearchUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.MockPolicy;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RunWith(PowerMockRunner.class)
@PrepareForTest({WordController.class,ApiResult.class,ApiResultGenerator.class,Storage.class})
@PowerMockIgnore({"java.management.*","java.script.*"})
public class WordControllerTest {
    @Mock
    DictionaryService dictionaryService;
    @InjectMocks
    WordController wordController;
    @Test
    public void testSearchSystemStore(){
        //create test param
        String word = "ilikeicecreamandmango";
        //create mock return data model
        List<String> list = Lists.newArrayList();
        list.add("i like ice cream and mango");
        list.add("i like ice cream and man go");
        list.add("i like icecream and mango");
        list.add("i like icecream and man go");
        ApiResult apiResult = ApiResultGenerator.success(list);
        //mock
        PowerMockito.when(dictionaryService.search(word)).thenReturn(apiResult);
        //execute
        ApiResult result = wordController.searchSystemStore(word);
        //verify
        Assert.assertEquals(apiResult.getCode(), result.getCode());
    }
}
