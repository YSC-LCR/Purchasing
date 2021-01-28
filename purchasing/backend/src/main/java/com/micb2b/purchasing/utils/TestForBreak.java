package com.micb2b.purchasing.utils;

public class TestForBreak {

    public static void main(String [] args){
        System.out.println("start");

        int x = 3;

        for (int i=0;i<=5;i++){
            if(i==3){
                continue;
            }
            System.out.println(i);
        }

        System.out.println("end");
    }
}
