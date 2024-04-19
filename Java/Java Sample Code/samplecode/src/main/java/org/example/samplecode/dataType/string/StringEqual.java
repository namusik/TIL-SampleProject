package org.example.samplecode.dataType.string;

import org.thymeleaf.util.StringUtils;

public class StringEqual {
    public static void main(String[] args) {
        // string.equal
        String a = "aa";
        String b = "aa";

        boolean resultA = a.equals(b);

        System.out.println("resultA = " + resultA); // true

        // StringUtils.equal
        String c = "cc";
        String d = null;
        String e = null;

        Boolean resultB = StringUtils.equals(c, d);
        Boolean resultC = StringUtils.equals(d, e);

        System.out.println("resultB = " + resultB); // false
        System.out.println("resultC = " + resultC); // true

        // '=='
        String string1 = "Hello";
        String string2 = "Hello";
        String string3 = new String("Hello");

        boolean areSame1 = string1 == string2;
        boolean areSame2 = string1 == string3;

        System.out.println("areSame1 = " + areSame1);
        System.out.println("areSame2 = " + areSame2);
    }
}
