package com.wordbreak;

import com.wordbreak.controller.WordController;
import com.wordbreak.core.api.ApiResultCodeMsg;
import com.wordbreak.entity.Dictionary;
import com.wordbreak.run.CompanyApplication;
import com.wordbreak.service.DictionaryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanyApplication.class)
public class DictionaryServiceTest {
    @Autowired
    DictionaryService dictionaryService;
    @Test
    public void search(){
        String word = "ilikeicecreamandmango";
        List<String> list = (List<String>) dictionaryService.search(word).getData();
        list.forEach(System.out::println);
        Assert.assertNotEquals(new ArrayList<>(),list);
    }
    @Test
    public void searchUser(){
        List<Dictionary> in = new ArrayList(){{
            add(new Dictionary("a"));
            add(new Dictionary("ab"));
            add(new Dictionary("cde"));
            add(new Dictionary("qwer"));
            add(new Dictionary("ghj"));
            add(new Dictionary("tyui"));
            add(new Dictionary("op"));
            add(new Dictionary("jkl"));
            add(new Dictionary("xcv"));
            add(new Dictionary("zz"));
            add(new Dictionary("bnm"));
        }};
        dictionaryService.saveUserStore(in);
        String word = "abcdeqweraghjop";//存在情况
        List<String> list = (List<String>) dictionaryService.searchByUser(word).getData();
        list.stream().forEach(System.out::println);
        Assert.assertNotEquals(new ArrayList<>(),list);
        word = "abcdeqweraghjopzb";//不存在情况
        list = (List<String>) dictionaryService.searchByUser(word).getData();
        list.stream().forEach(System.out::println);
        Assert.assertNotEquals(new ArrayList<>(),list);
    }
    @Test
    public void searchUserAndSystem(){
        List<Dictionary> in = new ArrayList(){{
            add(new Dictionary("a"));
            add(new Dictionary("ab"));
            add(new Dictionary("cde"));
            add(new Dictionary("qwer"));
            add(new Dictionary("ghj"));
            add(new Dictionary("tyui"));
            add(new Dictionary("op"));
            add(new Dictionary("jkl"));
            add(new Dictionary("xcv"));
            add(new Dictionary("zz"));
            add(new Dictionary("bnm"));
        }};
        dictionaryService.saveUserStore(in);
        String word = "abcdeqweraghjopilikeicecreamandmango";//存在情况
        List<String> list = (List<String>) dictionaryService.searchByUserAndSystem(word).getData();
        list.stream().forEach(System.out::println);
        Assert.assertNotEquals(new ArrayList<>(),list);
        word = "abcdeqweraghjopzbilikeicecreamandmango";//不存在情况
        list = (List<String>) dictionaryService.searchByUserAndSystem(word).getData();
        list.stream().forEach(System.out::println);
        Assert.assertNotEquals(new ArrayList<>(),list);
    }
}
