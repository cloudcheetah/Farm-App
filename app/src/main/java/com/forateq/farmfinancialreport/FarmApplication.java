package com.forateq.farmfinancialreport;

import android.app.Application;
import android.view.View;

import com.activeandroid.ActiveAndroid;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Vallejos Family on 2/4/2016.
 */
public class FarmApplication extends Application {
    private static FarmApplication mInstance;
    public static Deque<View> viewDeque = new ArrayDeque<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ActiveAndroid.initialize(this);
    }

    public static synchronized FarmApplication getInstance() {
        return mInstance;
    }


}
