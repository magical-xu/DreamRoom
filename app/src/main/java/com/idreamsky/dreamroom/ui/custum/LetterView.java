package com.idreamsky.dreamroom.ui.custum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.idreamsky.dreamroom.callback.LetterOnTouchCallBack;

/**
 * Created by magical on 2016/4/22.
 */
public class LetterView extends View {

    private LetterOnTouchCallBack letterOnTouchCallBack;

    private Paint paint;

    private int choose;

    private TextView oTextView;

    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    // ----------------构造函数-----------------
    public LetterView(Context context) {
        this(context, null);
    }

    public LetterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        choose = -1;
        paint = new Paint();
        // 设置抗锯齿
        paint.setAntiAlias(true);
    }

    /**
     * 重写onDraw方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 获取对象高度
        int height = getHeight();
        // 获取对象宽度
        int width = getWidth();

        // 获取字母长度
        int aLength = LETTERS.length;
        // 计算每一个字母的高度
        int singleHeight = height / aLength;

        for (int i = 0; i < aLength; i++) {

            // 判断选中状态
            if (i == choose) {
                // 设置文字大小
                paint.setTextSize(22);
                // 设置文字颜色
                paint.setColor(Color.RED);

            } else {
                // 设置文字大小
                paint.setTextSize(22);
                // 设置文字颜色
                paint.setColor(Color.BLUE);
            }
            float x = width / 2 - paint.measureText(LETTERS[i]) / 2;

            float y = singleHeight * (i + 1);

            canvas.drawText(LETTERS[i], x, y, paint);

        }
    }

    // 重写触摸事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // 点击的Y坐标
        float y = event.getY();

        final LetterOnTouchCallBack callBack = letterOnTouchCallBack;
        // 记录点击的位置。
        final int position = (int) (y / getHeight() * LETTERS.length);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                // 将choose置为原始值
                choose = -1;
                // 申请重绘
                invalidate();
                // 隐藏显示屏幕中心传过来的显示首字母的View
                if (oTextView != null) {
                    oTextView.setVisibility(View.GONE);
                }
                break;

            default:

                // 在我们的选择区域中间
                if (position >= 0 && position < LETTERS.length) {
                    // 将选中的字母传回去
                    if (callBack != null) {
                        callBack.onTouchingLetterChanged(LETTERS[position]);
                    }
                    // 如果TextView不为空，设置文字，并且显示
                    if (oTextView != null) {
                        oTextView.setText(LETTERS[position]);
                        oTextView.setVisibility(View.VISIBLE);
                    }
                    // 记录选择的位置
                    choose = position;
                    invalidate();
                }
        }

        return true;
    }

    // 设置监听

    public void setOnLetterCallBack(LetterOnTouchCallBack letterOnTouchCallBack) {
        this.letterOnTouchCallBack = letterOnTouchCallBack;
    }

    // 设置中间的文字
    public void setTextView(TextView oTextView) {
        this.oTextView = oTextView;
    }
}
