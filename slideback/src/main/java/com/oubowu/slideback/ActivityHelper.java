package com.oubowu.slideback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by Oubowu on 2016/9/20 3:28.
 */
public class ActivityHelper implements Application.ActivityLifecycleCallbacks {

    private static Stack<Activity> mActivityStack;

    public ActivityHelper() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        if (mActivityDestroyListener != null) {
            mActivityDestroyListener.onDestroy(activity);
        }

        mActivityStack.remove(activity);
    }

    public Activity getPreActivity() {
        if (mActivityStack == null) {
            return null;
        }
        int size = mActivityStack.size();
        if (size < 2) {
            return null;
        }
        return mActivityStack.elementAt(size - 2);
    }

    public void finishAllActivity() {
        if (mActivityStack == null) {
            return;
        }
        for (Activity activity : mActivityStack) {
            activity.finish();
        }
    }

    void setOnActivityDestroyListener(OnActivityDestroyListener listener) {
        mActivityDestroyListener = listener;
    }

    private OnActivityDestroyListener mActivityDestroyListener;

    interface OnActivityDestroyListener {
        void onDestroy(Activity activity);
    }


    public void addActivity(Class clz) {
        if (mActivityStack == null) {
            return;
        }
        Activity activity;
        for (int i = 0; i < mActivityStack.size(); i++) {
            activity = mActivityStack.get(i);
            if (activity.getClass().getName().equals(clz.getName())) {
                mActivityStack.remove(i);
                mActivityStack.add(activity);
                return;
            }
        }

    }

}
