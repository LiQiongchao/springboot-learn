package com.tamecode.lesson5;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/4 20:17
 */
public class Main {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        while (true) {
            System.out.println(classLoader.getClass().getName());
            classLoader = classLoader.getParent();
            if (classLoader == null) {
                break;
            }
        }

        ClassLoader systemClasLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClasLoader.getClass().getName());
    }

    /*
    sun.misc.Launcher$AppClassLoader
    sun.misc.Launcher$ExtClassLoader
    sun.misc.Launcher$AppClassLoader
    *
    * */

}
