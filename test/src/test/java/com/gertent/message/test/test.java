package com.gertent.message.test;

import org.junit.Test;

import java.util.*;

/**
 * @Description
 */
public class test {
    public static void main(String[] args) {
        HashMap<Long,String> map =new HashMap<>();
        map.put(1L,"a");
        map.put(2L,"b");
        System.out.println(map.values());
        List<String> list = new ArrayList<>(map.values());
        for (String str : list){
            System.out.println(str);
        }

        Set<Map.Entry<Long, String>> entries = map.entrySet();
        for (Map.Entry<Long, String> entry: entries){
            System.out.println(entry.getKey()+"********>"+entry.getValue());
        }
        System.out.println(list.size());
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(str.equals("a")){
                iterator.remove();
            }
        }
        System.out.println(list.size());

        Iterator<Map.Entry<Long, String>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<Long,String> en = iterator1.next();
            System.out.println(en.getKey()+"-------->"+en.getValue());
        }


    }
    @Test
    public void test1(){
        Random r = new Random();
        for (int i=0;i<50;i++){
            System.out.println(r.nextFloat());
        }
    }
}
