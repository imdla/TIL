package org.example.javaIntro;

import org.example.javaIntro.tmp.DefaultModifier;

public class Main {
    public static void main(String[] args) {
        PrivateModifier pm = new PrivateModifier();

        System.out.println(pm.publicNum);
//        System.out.println(pm.privateNum);
        System.out.println(pm.getPrivateNum());
        pm.setPrivateNum(100);
        System.out.println(pm.getPrivateNum());

        System.out.println(pm.defaultNum);

        pm.publicHi();
//        pm.privateHi();
        pm.sayHi();

        DefaultModifier dm = new DefaultModifier();
        System.out.println(dm.publicString);
//        System.out.println(dm.defaultString);
    }
}
