package com.wordbreak;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

public class DictionarySearch {

//    static String dictionary[] = { "i", "like", "sam", "sung", "samsung", "mobile", "ice", "cream", "man go"};
    static String dictionary[] = { "i", "like", "sam", "sung", "samsung","mobile", "ice","cream", "icecream", "man", "go", "mango"};

    @Test
    public void test(){
        String word = "ilikesamsungmobile";
//        String word = "ilikeicecreamandmango";
//        String word = "ilikeicecreammango";
        search(word);
    }
    private static void search(String word) {
        dictionarySearchUtil(word, "");
    }
    private static void dictionarySearchUtil(String word, String result) {
        int length = word.length();
        for (int i = 1; i <= length; i++) {
            String prefix = word.substring(0, i);
            Optional<String> re = dictionaryContains(prefix);
            if (re.isPresent()) {
                if (i == length) {
                    result += re.get();
                    System.out.println(result);
                    return;
                }
                String lestword = word.substring(i, length);
                dictionarySearchUtil(lestword, result + prefix + " ");
            }
        }
    }
    private static Optional dictionaryContains(String prefix) {
        Optional<String> str = Arrays.stream(dictionary).filter(s ->
                s.replaceAll(" +", "").equals(prefix.replaceAll(" +", ""))
        ).findAny();
        return str;
    }

//    public static void main(String[] args) {
//        String word = "ilikeicecreamandmango";
//        ArrayList<String> list = new ArrayList();
//        for(int sub = 0;sub<word.length();sub++){
//            for(int i = sub+1;i<=word.length();i++){
//                list.add(word.substring(sub,i));
//            }
//            if(sub+1 >= word.length()){
//                continue ;
//            }
//            word = word.substring(sub+1,word.length());
//        }
//        System.out.println(list.toString());
//    }
}