package com.rb.fmea.util;

import com.rb.fmea.entities.Fmea;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @version v1.0
 * @ClassName: ObjectUtil
 * @Description: TODO
 * @Author: yyk
 * @Date: 2020/7/2 14:36
 */
public class ObjectUtil<T> {

    /**
     * @Author yyk
     * @Description //TODO obj1旧值，obj2新值
     * @Date 2020/7/2 14:46
     * @Param [obj1, obj2]
     * @return java.util.Map<java.lang.String,java.lang.String[]>
     **/
    public static<T> Map<String,Object[]> compare(T obj1, T obj2) throws IllegalAccessException {
        Map<String, Object[]> result = new HashMap<String, Object[]>();
        Field[] declaredFields = obj1.getClass().getDeclaredFields();
        for (Field field:declaredFields){
            //设置可以访问私有变量
            field.setAccessible(true);
            Object o1 = field.get(obj1);
            Object o2 = field.get(obj2);
            if(!equals(o1,o2)){
                Object[] array={o1,o2};
                result.put(field.getName(),array);
            }
        }
        return result;
    }


    public static boolean equals(Object obj1, Object obj2) {

        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }


    public static void main(String[] args) throws IllegalAccessException {
        Fmea fmea=new Fmea();
        fmea.setState(1);
        fmea.setFinishDate(DateUtil.parseTime(new Date()));
        fmea.setFmeaDesc("aaa");
        fmea.setFmeaName("bbb");
        fmea.setProductId(2);

        Fmea fmea1=new Fmea();
        fmea1.setState(2);
        fmea1.setFinishDate(DateUtil.parseTime(new Date()));
        fmea1.setFmeaDesc("aaa");
        fmea1.setFmeaName("bbbb");
        fmea1.setProductId(0);

        Map<String, Object[]> compare = compare(fmea, fmea1);
        Iterator<Map.Entry<String, Object[]>> iterator = compare.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object[]> next = iterator.next();
            System.out.println(next.getKey());
            Object[] value = next.getValue();
            for(Object o:value){
                System.out.println(o);
            }

        }
    }
}
