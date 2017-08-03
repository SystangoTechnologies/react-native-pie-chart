package com.github.yamill.piegraph;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.renderscript.Float2;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


public class PieChartModule extends SimpleViewManager<View> {

    Activity mActivity;
    float[] seriesArray;
    String[] sliceColorArray;
    int chartWidth;

    public PieChartModule(ReactApplicationContext reactContext, Activity activity) {
        mActivity = activity;
    }


    @ReactProp(name = "chart_wh")
    public void setChart_wh(PieGraph view, double chart_wh) {
        chartWidth = (int)chart_wh;
        view.setData(seriesArray,sliceColorArray, chartWidth);
    }

    @ReactProp(name = "series")
    public void setSeries(PieGraph view, ReadableArray series) {

        seriesArray = new float[series.size()];

        for (int i=0;i<series.size();i++) {
            seriesArray[i] = Float.valueOf(""+series.getDouble(i));
        }

        view.setData(seriesArray,sliceColorArray, chartWidth);
    }

    @ReactProp(name = "sliceColor")
    public void setSliceColor(PieGraph view,  ReadableArray sliceColor) {

        sliceColorArray = new String[sliceColor.size()];

        for (int i=0;i<sliceColor.size();i++) {
            sliceColorArray[i] = String.valueOf(sliceColor.getString(i));
        }

        view.setData(seriesArray,sliceColorArray, chartWidth);
    }


    @Override
    public String getName() {
        return "PieChartModule";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        PieGraph pie = new PieGraph(reactContext);//(PieGraph) findViewById(R.id.pie_chart);
        int chartWidth = 150;
        pie.setData(seriesArray,sliceColorArray, chartWidth);

        return pie;
    }

}
