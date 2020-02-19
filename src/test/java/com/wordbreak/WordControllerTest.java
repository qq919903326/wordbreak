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

import java.util.*;
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

        Optional<List<String>> optional = Optional.of(list);
        ApiResult apiResult = ApiResultGenerator.success(optional.get());
        //mock
        PowerMockito.when(dictionaryService.search(word)).thenReturn(optional);
        //execute
        ApiResult result = wordController.searchSystemStore(word);
        //verify
        Assert.assertEquals(apiResult.getCode(), result.getCode());
    }
    @Test
    public void testSearchUserStore(){
        //init user store
        /**
         * [{"dict":"a"},{"dict":"ab"},{"dict":"ert"},{"dict":"qwer"}]
         */
        Dictionary[] dictionaries = {new Dictionary("a"),new Dictionary("ab"),new Dictionary("ert"),new Dictionary("qwer")};
        Storage.USER_DICTIONARIES.addAll(Arrays.asList(dictionaries));
        //create test param
        String word = "aqweraab";
        //create mock return data model
        List<String> list = Lists.newArrayList();
        list.add("a qwer a ab");

        Optional<List<String>> optional = Optional.of(list);
        ApiResult apiResult = ApiResultGenerator.success(list);
        //mock
        PowerMockito.when(dictionaryService.searchByUser(word)).thenReturn(optional);
        //execute
        ApiResult result = wordController.searchUserStore(word);
        //verify
        Assert.assertEquals(apiResult.getCode(), result.getCode());
    }

    @Test
    public void testSearchSystemAndUserStore(){
        //init user store
        /**
         * [{"dict":"a"},{"dict":"ab"},{"dict":"ert"},{"dict":"qwer"}]
         */
        Dictionary[] dictionaries = {new Dictionary("a"),new Dictionary("ab"),new Dictionary("ert"),new Dictionary("qwer")};
        Storage.USER_DICTIONARIES.addAll(Arrays.asList(dictionaries));
        //create test param
        String word = "aqweraabilikeicecreamandmango";
        //create mock return data model
        List<String> list = Lists.newArrayList();
        list.add("a qwer a ab i like ice cream and mango");
        list.add("a qwer a ab i like ice cream and man go");
        list.add("a qwer a ab i like icecream and mango");
        list.add("a qwer a ab i like icecream and man go");

        Optional<List<String>> optional = Optional.of(list);
        ApiResult apiResult = ApiResultGenerator.success(list);
        //mock
        PowerMockito.when(dictionaryService.searchByUserAndSystem(word)).thenReturn(optional);
        //execute
        ApiResult result = wordController.searchSystemAndUserStore(word);
        //verify
        Assert.assertEquals(apiResult.getCode(), result.getCode());
    }
}
