package org.example.inheritanceprac;

public class Child extends Parent {
    public String childValue = "child Value";

    public Child(String publicValue, String childValue) {
        super(publicValue);
        this.childValue = childValue;
    }

    public void childMethod() {
        System.out.println("child Method");
    }

    // 메서드 오버라이딩
    // 자식 클래스에서 부모 클래스의 메서드 재정의
    @Override
    public void publicMethod() {
        // 나의 부모의 publicMethod 에 접근
        super.publicMethod();
        // 나의 부모의 publicValue 에 접근
        System.out.println(super.publicValue);
        // 나의 부모의 protectedValue 에 접근
        System.out.println(super.protectedValue);
        System.out.println("child public Method");
    }
}
