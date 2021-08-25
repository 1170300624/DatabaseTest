package com.example.databasetest;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver implements LifecycleObserver {

    Lifecycle lifecycle;

    public MyObserver(Lifecycle lifecycle){
        this.lifecycle = lifecycle;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void activityStart(){
        Log.d("MyObserver", "activityStart ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void activityStop(){
        Log.d("MyObserver", "activityStop ");
    }

}
