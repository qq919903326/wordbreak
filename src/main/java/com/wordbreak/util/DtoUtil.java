package com.wordbreak.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DtoUtil {
    static Set<Class> classes = new HashSet<>();
    public static <T,A> T replaceDto(A obj,Class<T> tClass) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        T result = tClass.newInstance();
        for (Field f : fields){
            f.setAccessible(true);
            String fileName = f.getName();
            Class type = f.getType();
            Field tF = tClass.getDeclaredField(fileName);
            if(type == byte[].class){
                String fVal = new String((byte[])f.get(obj));
                tF.set(result,fVal);
            }else if(classes.contains(type)){
                Object typeObj = f.get(obj);
                Object fval = replaceDto(typeObj, tF.getType());
                tF.set(result,fval);
            }else if(type == List.class){
//                List objs = (List) f.get(obj);
//                List val = new ArrayList();
//                for (Object o : objs){
//                    Class<List> listClass = (Class<List>) tF.getType();
//                    Type type1 = listClass.getG
//                    replaceDto(o, )
//                }
//                tF.set(result,val);
            }else{
                tF.set(result,f.get(obj));
            }
        }
        return result;
    }
}
