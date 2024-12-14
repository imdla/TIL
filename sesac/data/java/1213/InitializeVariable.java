package org.example;

public class InitializeVariable {
    public static void main(String[] args) {
        V1 v1 = new V1();
        System.out.println(v1.value);

        V2 v2 = new V2();
        System.out.println(v2.value);

        V3 v3 = new V3();
        System.out.println(v3.value);
    }
}

class V1 {
    static int staticValue = 30;

    // 명시적인 초기화
    int value = 10;
    String word = "Text";
}

class V2 {
    int value;
    String word;

    // 기본 생성자 초기화
    public V2() {
        this(10, "Text");
    }

    public V2(int value, String word) {
        this.value = value;
        this.word = word;
    }
}

class V3 {
    static int staticValue;

    int value;
    String word;

    // 클래스 초기화 블록
    static {
        if (true) {
            staticValue = 30;
        } else {
            staticValue = 50;
        }
    }

    // 인스턴스 초기화 블록
    {
        if (true) {
            this.value = 10;
        } else {
            this.value = 12;
        }

        this.word = "Text";
    }
}