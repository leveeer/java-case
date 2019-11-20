package com.jxau.file;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class FileTest {
    //从控制台录入一个文件夹路径，打印该文件夹下的所有.java文件
    public static void main(String[] args) {

        File file = isDir();
        getFile(file);
    }


    public static File isDir(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个文件夹路径：");
        while (true){
            String line = sc.nextLine();
            //将录入的路径封装成一个File对象
            File file = new File(line);
            //输入的路径不存在
            if (!file.exists()){
                System.out.println("您输入的文件夹不存在，请重新输入");
            }else if (!file.isDirectory()){
                //输入的路径不是文件夹
                System.out.println("您输入的不是一个文件夹，请重新输入");
            }else {
                //存在且是文件夹，则返回File对象
                return file;
            }
        }
    }

    public static void getFile(File file){
        //获取file文件夹下所有文件和文件夹，使用匿名内部类创建FileFilter子类对象
        File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                //如果是文件夹或以.java结尾的文件则返回放入File数组中
                return pathname.isDirectory() || pathname.getName().endsWith(".java");
            }
        });

        for (File files : listFiles) {          //遍历File数组
            if (files.isFile()){                //如果是文件
                System.out.println(files);      //输出文件路径
            }else if (files.isDirectory()){     //如果是文件夹
                getFile(files);                 //递归调用，继续查找
            }
        }
    }
}
