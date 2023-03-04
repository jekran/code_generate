package com.cg.utils.freemarker;

public class Singleton {
    private Singleton() {
    }

    /**
     * volatile is since JDK5
     */
    private static volatile Singleton sSingleton;

    public static Singleton getInstance() {
        if (sSingleton == null) {
            synchronized (Singleton.class) {
                // 未初始化，则初始instance变量
                if (sSingleton == null) {
                    sSingleton = new Singleton();
                }
            }
        }
        return sSingleton;
    }
}
