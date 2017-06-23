package com.atguigu.tiankuo.p2p0224.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.tiankuo.p2p0224.utils.UIUtils;

/**
 * 作者：田阔
 * 邮箱：1226147264@qq.com
 * Created by Administrator on 2017/6/23 0023.
 */

public class ProgressView extends View {
    private Paint paint;
    private int paintColor = Color.BLACK;
    private int strokesWidth = UIUtils.dp2px(10);
    private int height;
    private int width;
    private int sweepAngle = 0;


    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(paintColor);
        paint.setStrokeWidth(strokesWidth);
        paint.setAntiAlias(true);
        //三种样式
        //stroke 描边
        //fill 填充
        //stroke and fill 描边和填充
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = getHeight();
        width = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.画出三个部分
        //圆，圆弧，文字

        //圆
        paint.setStrokeWidth(strokesWidth);//画笔的宽度
        paint.setColor(Color.BLUE);//画笔的颜色
        int cX = width / 2;//圆心的X坐标
        int cY = height / 2;//圆心的Y坐标
        int radiu = cX - strokesWidth / 2;//圆的半径
        canvas.drawCircle(cX, cY, radiu, paint);//

        //圆弧
        paint.setColor(Color.RED);
        RectF rectF = new RectF();
        //圆弧的左上定点和右下定点
        rectF.set(strokesWidth / 2, strokesWidth / 2, width - strokesWidth / 2, height - strokesWidth / 2);
        canvas.drawArc(rectF, 0, sweepAngle, false, paint);

        //文字
        paint.setStrokeWidth(0); //设置画笔的宽度
        String str = sweepAngle + "%";
        paint.setTextSize(30);//设置文字的大小
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);//获取文字的宽高
        int textWidth = rect.width(); //文字的宽
        int textHeight = rect.height();//文字的高
        float x = width / 2 - textWidth / 2; //左下顶点的x坐标
        float y = height / 2 + textHeight / 2;//左下顶点的y坐标
        canvas.drawText(str, x, y, paint);

    }

     /*
     * 面试题：
     * invalidate和postInvalidate的区别是什么
     * invalidate是主线程进行强制重绘
     * postInvalidate是分线程进行强制重绘
     * */

    public void setSweepAngle(int progress) {
        ValueAnimator animator = ValueAnimator.ofInt(0, sweepAngle);
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int a = (int) animation.getAnimatedValue();
                ProgressView.this.sweepAngle = a;
                invalidate();
            }
        });
        animator.start();
    }
}
