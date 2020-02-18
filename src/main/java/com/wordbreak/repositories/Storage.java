package com.wordbreak.repositories;

import com.google.common.collect.Lists;
import com.wordbreak.entity.Dictionary;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Storage {
    //系统字典存放
    List<Dictionary> DICTIONARIES = new CopyOnWriteArrayList(){{
        add(new Dictionary("i"));
        add(new Dictionary("like"));
        add(new Dictionary("sam"));
        add(new Dictionary("sung"));
        add(new Dictionary("samsung"));
        add(new Dictionary("mobile"));
        add(new Dictionary("ice"));
        add(new Dictionary("cream"));
        add(new Dictionary("icecream"));
        add(new Dictionary("man"));
        add(new Dictionary("go"));
        add(new Dictionary("and"));
        add(new Dictionary("mango"));
    }};
    //用户字典
    List<Dictionary> USER_DICTIONARIES = Lists.newArrayList();
}
