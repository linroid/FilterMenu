package com.linroid.filtermenu.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by linroid on 15/3/10.
 */
public class FilterMenuDrawable extends Drawable {
    private Context ctx;
    private Paint paint;
    private IconState state = IconState.COLLAPSED;
    private int radius;

    private int lineWidth = 8;
    private float expandProgress = 0;

    public FilterMenuDrawable(Context ctx, int color, int radius) {
        this.ctx = ctx;
        this.radius = radius;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(lineWidth);

    }
    public enum IconState{
        COLLAPSED,
        EXPANDED
    }

    public float getExpandProgress() {
        return expandProgress;
    }

    public void setExpandProgress(float expandProgress) {
        this.expandProgress = expandProgress;
        invalidateSelf();
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (radius*0.8f);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (radius*0.8f);
    }

    @Override
    public void draw(Canvas canvas) {
        //draw three line
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(getBounds(), paint);
//        paint.setColor(Color.WHITE);
        if(expandProgress<=0.5f){
            drawTopLine(canvas, expandProgress);
            drawMiddleLine(canvas, expandProgress);
            drawBottomLine(canvas, expandProgress);
        // draw cancel
        }else{
            drawTopLeftLine(canvas, expandProgress);
            drawBottomLeftLine(canvas, expandProgress);
        }
    }

    private void drawBottomLeftLine(Canvas canvas, float progress) {
        int ly = (int) (getBounds().bottom-getIntrinsicHeight()*progress);
        int ry = (int) (getBounds().top+ getIntrinsicHeight()*progress);
        canvas.drawLine(getBounds().left, ly, getBounds().right, ry, paint);
    }

    private void drawTopLeftLine(Canvas canvas, float progress) {
        int ry = (int) (getBounds().bottom-getIntrinsicHeight()*progress);
        int ly = (int) (getBounds().top+ getIntrinsicHeight()*progress);
        canvas.drawLine(getBounds().left, ly, getBounds().right, ry, paint);
    }


    private void drawTopLine(Canvas canvas, float progress) {
        int y = getBounds().top + (int) (getIntrinsicHeight()* progress) + lineWidth;
        canvas.drawLine(getBounds().left, y, getBounds().left+getIntrinsicWidth(), y, paint);
    }

    private void drawMiddleLine(Canvas canvas, float progress) {
        int y = getBounds().top + getIntrinsicHeight() / 2;
        int len = getIntrinsicWidth() /2;
        int centerX = getBounds().centerX();
        canvas.drawLine(centerX-len/2, y, centerX+len/2, y, paint);
    }
    private void drawBottomLine(Canvas canvas, float progress) {
        int y = getBounds().top + (int) (getIntrinsicHeight() * (1-progress)) - lineWidth;
        int len = getIntrinsicWidth() /4;
        int centerX = getBounds().centerX();
        canvas.drawLine(centerX-len/2, y, centerX+len/2, y, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
