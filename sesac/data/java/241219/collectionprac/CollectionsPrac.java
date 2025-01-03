package org.example.collectionprac;

import java.util.*;

public class CollectionsPrac {
    public static void main(String[] args) {
//        - String타입의 ArrayList를 만들고, 주어진 메서드들을 연습하시오.
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add(0, "z");
        System.out.println(list);

        System.out.println(list.get(0));
        list.set(0, "hello");
        System.out.println(list);

        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.contains("hello"));
        System.out.println(list.remove(0));
        System.out.println(list);

//        - Key와 Value가 모두 String 타입인 HashMap을 만들고, 주어진 메서드들을 연습하시오.
        Map<String, String> map = new HashMap<>();
        map.put("john", "1");
        map.put("mia", "2");
        map.put("jenny", "3");
        System.out.println(map);

        System.out.println(map.get("mia"));
        System.out.println(map.getOrDefault("bruk", "0"));
        System.out.println(map.size());
        System.out.println(map.containsKey("john"));
        System.out.println(map.containsValue("1"));
        System.out.println(map.remove("john"));
        System.out.println(map);

        System.out.println(map.keySet());
        for (String string : map.keySet()) {
            System.out.println(string + " : " + map.get(string));
        }

//        - String 타입의 HashSet을 만들고, 주어진 메서드들을 연습하시오.
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");
        set.add("a");
        System.out.println(set);

        List<String> lst = new ArrayList<>();
        lst.add("hello");
        lst.add("world");
        lst.add("java");

        set.addAll(lst);
        System.out.println(set);

        System.out.println(set.size());
        System.out.println(set.isEmpty());
        System.out.println(set.contains("hello"));
        System.out.println(set.remove("hello"));
        System.out.println(set);

//        - {30, 20, 10}이 들어있는 ArrayList를 만들고, 합계와 평균을 계산하시오
        List<Integer> nums = new ArrayList<>();
        nums.add(30);
        nums.add(20);
        nums.add(10);
        System.out.println(nums);

        // 합계
        int numsSum = 0;
        int cnt = 0;
        for (Integer num : nums) {
            numsSum += num;
            cnt += 1;
        }
        System.out.println(numsSum);

        // 평균
        int numAvg = numsSum / cnt;
        System.out.println(numAvg);
    }
}
