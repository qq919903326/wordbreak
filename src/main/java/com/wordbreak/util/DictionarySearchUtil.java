package com.wordbreak.util;

import com.wordbreak.entity.Dictionary;
import com.wordbreak.repositories.Storage;

import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DictionarySearchUtil {
    //dp查找
    public static Optional<List<String>> dpSearch(String word,List<Dictionary>... list) {
       List<String> result = dictionarySearchUtil(word,list);
       if(result.isEmpty())
           return Optional.empty();
       return Optional.of(result);
    }
    //dp算法
    public static List<String> dictionarySearchUtil(String s,List<Dictionary>... dictionaries) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList(){{add("");}};
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                String prefix = s.substring(j, i);
                Optional<Dictionary> re = dictionaryContains(prefix,dictionaries);
                if (dp[j].size() > 0 && re.isPresent()) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + re.get().getDict());
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }
    //判断是否存在
    public static Optional dictionaryContains(String prefix,List<Dictionary>... dictionarys) {
        List<Dictionary> list = Stream.of(dictionarys).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        Optional<Dictionary> dictionaryOptional = list.stream().filter(s ->
                s.getDict().replaceAll(" +", "").equals(prefix.replaceAll(" +", ""))
        ).findAny();
        return dictionaryOptional;
    }
}
