// package com.example.suryavanshi.piegraph;
package com.github.yamill.piegraph;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class PieGraph extends View {

    private Paint piePaint;
    private RectF rectF;
    private float[] data;
    private int chartWidth;
    private String[] colors;




    public PieGraph(Context context) {
        super(context);
        init(context);
    }


    public PieGraph(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);


    }

    public PieGraph(Context context, AttributeSet attrs,
                   int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public  void init(Context context){
        setFocusable(true);

        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.FILL);


        float[] data = {5, 3, 12, 7, 5};
        String[] colors = {"#F44336","#2196F3","#FFEB3B", "#4CAF50", "#FF9800"};
        int chartWidth = 200;
        setData(data, colors, chartWidth);
    }


    private float[] pieSegment(){
        System.out.println("Segmenting Piegraph");

        float[] segValues = new float[this.data.length];
        float Total = getTotal();

        for (int i = 0; i < this.data.length; i++){

            segValues[i] = (this.data[i]/Total) * 360;
        }

        return segValues;
    }


    private float getTotal(){

        float total = 0;

        for (float val : this.data){
            total +=val;
        }
        System.out.println("The total is ");
        System.out.println(total);
        return total;
    }

    @Override
    protected void onDraw(Canvas canvas){
            if (data != null){




            int top = 0;
            int left = 0;
                DisplayMetrics metrics = getResources().getDisplayMetrics();

            float endBottom = (float) (chartWidth * metrics.density);

                float endRight = (float) (chartWidth * metrics.density);


            System.out.println("left");
            System.out.println(left);
            System.out.println(top);
            System.out.println(endBottom);
            System.out.println(endRight);

            rectF = new RectF(left, top, endRight, endBottom);

            float[] segment = pieSegment();

                piePaint.setColor(Color.RED);

            float segStartPoint = 270;

            for (int i = 0; i < segment.length; i++){

                Random rnd = new Random();
                int color = Color.parseColor(this.colors[i]);
                piePaint.setColor(color);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, piePaint);
                segStartPoint += segment[i];
            }
        }
    }

    public void setData(float[] data,String[] colors,int chartWidth){

        this.data = data;
        this.chartWidth = chartWidth;
        this.colors = colors;
        invalidate();
    }
}
