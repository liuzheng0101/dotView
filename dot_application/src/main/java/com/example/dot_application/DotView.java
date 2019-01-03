package com.example.dot_application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DotView extends View {
    private List<DotBean> list=new ArrayList<>();
    private Paint paint;
    private Paint rectpaint;
    private Rect rect;
    private Paint checkPaint;
    private int left,right,top,bottom;
    public DotView(Context context) {
        this(context,null);
    }

    public DotView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        //绘画圆点
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        //绘画矩形
        rectpaint=new Paint();
        rectpaint.setStyle(Paint.Style.STROKE);
        rectpaint.setStrokeWidth(10);
        //圆选中变化
        checkPaint=new Paint();
        checkPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (list!=null){
            for (DotBean dotBean:list){
                if (!dotBean.isChecked()){
                    canvas.drawCircle(dotBean.getX(),dotBean.getY(),20,paint);
                }else{
                    canvas.drawCircle(dotBean.getX(),dotBean.getY(),20,checkPaint);
                }
            }
        }
        //绘画矩形
        rect=new Rect(left,top,right,bottom);
        canvas.drawRect(rect,rectpaint);
    }

    /**
     * 添加对象
     * @param dotBean
     */
    public void add(DotBean dotBean){
        if (dotBean!=null){
            list.add(dotBean);
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                left= (int) event.getX();
                top= (int) event.getY();
                invalidate();  //重绘
                break;
            case MotionEvent.ACTION_MOVE:
                right= (int) event.getX();
                bottom= (int) event.getY();
                //选中在矩形中的小圆点
                for (DotBean dotBean:list){
                    if (dotBean.getX()>left&&dotBean.getX()<right&&dotBean.getY()>top&&dotBean.getY()<bottom){
                        dotBean.setChecked(true);
                    }else{
                        dotBean.setChecked(false);
                    }
                }
                invalidate();  //重绘
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
