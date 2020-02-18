package com.wordbreak;

import com.wordbreak.controller.WordController;
import com.wordbreak.core.api.ApiResultCodeMsg;
import com.wordbreak.run.CompanyApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanyApplication.class)
public class DictionarySearchControllerTest {
    @Autowired
    WordController wordController;
    @Test
    public void search(){
        String word = "ilikeicecreaandmmango";
        Assert.assertEquals(ApiResultCodeMsg.SUCCESS.getCode(), wordController.searchSystemStore(word).getCode());
    }
}
