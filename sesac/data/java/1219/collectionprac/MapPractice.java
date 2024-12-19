package org.example.collectionprac;

import java.util.HashMap;
import java.util.Set;

public class MapPractice {
    public static void main(String[] args) {
        // Key 값에는 immutable 값이 들어가야 함
        HashMap<String, Integer> map = new HashMap<>();

        // data 삽입
        map.put("Korean", 100);
        map.put("English", 10);

        System.out.println(map);

        // data 수정
        map.put("Korean", 30);
        System.out.println(map);

        // data 조회
        System.out.println(map.get("Korean"));

        System.out.println(map.get("Math")); // Key 값이 없을 때 -> null
        System.out.println(map.getOrDefault("Math", 0)); // Key 값이 없을 때 -> 기본값 지정

        // data 크기
        System.out.println(map.size());

        // 값 가져오기
        Set<String> strings = map.keySet();
        for (String string : strings) {
            Integer value = map.get(string);
            System.out.println(string + value);
        }

    }
}
