package com.tamecode.lesson5.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author: LiQiongchao
 * @Date: 2020/8/4 22:40
 */
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ClassLoader classLoader = sc.getClassLoader();
        while (true) {
            System.out.println(classLoader.getClass().getName());
            classLoader = classLoader.getParent();
            if (classLoader == null) {
                break;
            }
        }
        /*
        org.apache.catalina.loader.ParallelWebappClassLoader
        java.net.URLClassLoader
        sun.misc.Launcher$AppClassLoader
        sun.misc.Launcher$ExtClassLoader
         */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
