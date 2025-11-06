package com.thegentle.oldhealth.utils;

//线程绑定的变量
public class CurrentHolder {
    private static final ThreadLocal<Integer> currentUser = new ThreadLocal<>();
    //设置当前用户
    public static void setCurrentUser(Integer ID) {
        currentUser.set(ID);
    }
    //获取当前用户
    public static Integer getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}
