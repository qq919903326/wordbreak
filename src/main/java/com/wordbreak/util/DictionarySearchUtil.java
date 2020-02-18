package com.wordbreak.util;

import com.wordbreak.constant.Constant;
import com.wordbreak.entity.Dictionary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DictionarySearchUtil {

    //dp查找
    public static List<String> dpSearch(String word) {
       return dictionarySearchUtil(word);
    }
    //递归查找
    public static String recursiveSearch(String word) {
       return dictionarySearchUtil(word, "");
    }
    //递归算法
    public static String dictionarySearchUtil(String word, String result) {
        int length = word.length();
        for (int i = 1; i <= length; i++) {
            String prefix = word.substring(0, i);
            Optional<Dictionary> re = dictionaryContains(prefix);
            if (re.isPresent()) {
                if (i == length) {
                    result += re.get().getDict();
                    return result;
                }
                String lestword = word.substring(i, length);
                dictionarySearchUtil(lestword, result + prefix + " ");
            }
        }
        return "-1";
    }
    //dp算法
    public static List<String> dictionarySearchUtil(String s) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList(){{add("");}};
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            LinkedList<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                String prefix = s.substring(j, i);
                Optional<Dictionary> re = dictionaryContains(prefix);
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
    public static Optional dictionaryContains(String prefix) {
        Optional<Dictionary> str = Constant.DICTIONARIES.stream().filter(s ->
                s.getDict().replaceAll(" +", "").equals(prefix.replaceAll(" +", ""))
        ).findAny();
        return str;
    }
}