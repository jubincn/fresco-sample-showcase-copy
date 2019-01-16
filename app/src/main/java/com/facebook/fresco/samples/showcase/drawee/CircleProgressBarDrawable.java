package com.facebook.fresco.samples.showcase.drawee;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.facebook.drawee.drawable.ProgressBarDrawable;

public class CircleProgressBarDrawable extends ProgressBarDrawable {
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mLevel = 0;
    private int maxLevel = 10000;
    private float radius;
    private float strokeWidth;

    public CircleProgressBarDrawable(float radiusPx, float strokeWidth) {
        this.radius = radiusPx;
        this.strokeWidth = strokeWidth;
    }

    @Override
    protected boolean onLevelChange(int level) {
        mLevel = level;
        invalidateSelf();
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        if (getHideWhenZero() && mLevel == 0) {
            return;
        }
        drawBar(canvas, maxLevel, getBackgroundColor());
        drawBar(canvas, mLevel, getColor());
    }

    private void drawBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();

        float centerX = bounds.right / 2f;
        float centerY = bounds.bottom / 2f;
        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth);
        if (level != 0){
            canvas.drawArc(rectF, 0, (float) (level * 360 / maxLevel), false, mPaint);
        }

    }
}
