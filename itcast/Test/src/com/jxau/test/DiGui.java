package com.jxau.test;

public class DiGui {
    public static int function(int v){
        if (v > 1){
            return v * function(v - 1);
        }else {
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(function(6));
    }
}
