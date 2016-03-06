package com.idreamsky.dreamroom.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity管理类
 */
public class ActivityManager {

    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {}

    public static ActivityManager getAppManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    public void finishAllActivityAndExit() {
        if (null != activityStack) {
            for (int i = 0, size = activityStack.size(); i < size; i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

}
