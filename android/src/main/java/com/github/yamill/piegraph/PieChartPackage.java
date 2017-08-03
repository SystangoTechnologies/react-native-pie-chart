package com.github.yamill.piegraph;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.common.logging.FLog;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
// import com.facebook.react.bridge.WritableMap;
// import com.facebook.react.common.ReactConstants;
// import com.facebook.react.modules.core.DeviceEventManagerModule;


public class PieChartPackage implements ReactPackage {

    Activity mActivity;

    public  PieChartPackage(Activity activity){
        mActivity = activity;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        List<ViewManager> list = new ArrayList<>();
        list.add(new PieChartModule(reactContext, mActivity));
        System.out.println("Reached :");
        System.out.print(list);
        return list;
    }

}
