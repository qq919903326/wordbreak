package com.wordbreak;

import com.google.common.collect.Lists;
import com.wordbreak.core.api.ApiResult;
import com.wordbreak.core.api.ApiResultGenerator;
import com.wordbreak.repositories.Storage;
import com.wordbreak.service.DictionaryService;
import com.wordbreak.util.DictionarySearchUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DictionaryService.class,ApiResult.class,ApiResultGenerator.class,Storage.class})
@PowerMockIgnore({"java.management.*","java.script.*"})
public class DictionaryServiceTest {
    @InjectMocks
    DictionaryService dictionaryService;
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
        //mock
        PowerMockito.when(DictionarySearchUtil.dpSearch(word,Storage.DICTIONARIES)).thenReturn(optional);
        //execute
        Optional<List<String>> result = dictionaryService.search(word);
        //verify
        Assert.assertEquals(result.get(), optional.get());
    }
}
