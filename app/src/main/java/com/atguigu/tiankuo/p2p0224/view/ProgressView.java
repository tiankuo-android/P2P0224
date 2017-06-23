package com.atguigu.tiankuo.p2p0224.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private int strokesWidth = UIUtils.dp2px(20);
    private int height;
    private int width;


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
        int cX = width / 2;
        int cY = height / 2;
        int radiu = cX - strokesWidth / 2;
        canvas.drawCircle(cX,cY,radiu,paint);
    }
}
