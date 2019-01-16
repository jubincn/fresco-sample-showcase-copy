package com.facebook.fresco.samples.showcase.drawee;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.fresco.samples.showcase.utils.ArcUtils;

public class CircleProgressBarDrawable extends ProgressBarDrawable {
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mLevel = 0;
    private int maxLevel = 10000;


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

        RectF rectF = new RectF((float) (bounds.right * .4), (float) (bounds.bottom * .4),
                (float) (bounds.right * .6), (float) (bounds.bottom * .6));

        float x = bounds.right / 2f;
        float y = bounds.bottom / 2f;
        PointF center = new PointF(x, y);

        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        if (level != 0) {
            ArcUtils.drawArc(canvas, center, 50, 0, (float) (level * 360 / maxLevel), mPaint );
        }
    }
}
