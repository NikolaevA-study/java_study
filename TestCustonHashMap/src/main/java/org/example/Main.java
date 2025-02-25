package org.example;

public class Main {
    public static void main(String[] args) {
        CustomHashMap customHashMap = new CustomHashMap<String,String>();
        customHashMap.put("sa","asdad");

        System.out.println(customHashMap.get("sa"));
    }


}