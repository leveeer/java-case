package com.jxau.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class FileDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个文件路径：");
        String line = sc.nextLine();
        File file = new File(line);
        File[] listFiles = file.listFiles(new JavaFilter());
        for (File listFile : listFiles) {
            System.out.println(listFile);
        }
    }
}

class JavaFilter implements FilenameFilter{

    @Override
    public boolean accept(File dir, String name) {
        File file = new File(dir,name);
        if(file.isDirectory())
            return false;
        else
            return name.endsWith(".java");
    }
}