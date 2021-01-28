package com.zedux.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.zedux.R;

public class TextProfile extends View {

    private int circleColor, labelColor, lineColor;
    private float labelSize;

    private String circleText;

    private Paint circlePaint;
    private Paint labelPaint;


    public TextProfile(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        circlePaint = new Paint();
        labelPaint = new Paint();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ProfileTextView, 0, 0);

        try {
            circleText = a.getString(R.styleable.ProfileTextView_circleLabel);
            circleColor = a.getInteger(R.styleable.ProfileTextView_circleColor, 0);
            labelColor = a.getInteger(R.styleable.ProfileTextView_labelColor, 0);
            lineColor = a.getInteger(R.styleable.ProfileTextView_lineColor, 0);
            labelSize = a.getDimension(R.styleable.ProfileTextView_labelSize, 50);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;

        int radius = 0;
        if(viewHeightHalf > viewWidthHalf)
            radius = viewWidthHalf ;
        else
            radius = viewHeightHalf;

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        circlePaint.setColor(circleColor);

        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);


        circlePaint.setColor(lineColor);



        canvas.drawLine(0, viewHeightHalf, this.getMeasuredWidth(), viewHeightHalf, circlePaint);

        labelPaint.setColor(labelColor);
        labelPaint.setTextAlign(Paint.Align.CENTER);

        labelPaint.setTextSize(labelSize);

        Rect bounds = new Rect();
        labelPaint.getTextBounds(circleText, 0, 1, bounds);


        Paint.FontMetrics fm = labelPaint.getFontMetrics();
        float height = fm.descent - fm.ascent;

        int heightAlign = (bounds.height() - (bounds.height()/ 2)) / 2;
        canvas.drawText(circleText, viewWidthHalf, viewHeightHalf + heightAlign, labelPaint);
    }
}
