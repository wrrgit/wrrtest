package com.wechat.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
 
/**
 * 关于android中的.9图（二）——利用java代码引用设置.9图
 * https://blog.csdn.net/kroclin/article/details/40797975
 * @author wangqx
 *
 */
public class MyView extends View {
 
	private NinePatch mNinePatch;//画9图的
	private Paint mPaint;//画笔
	private Rect mRect;//矩形
	private Bitmap mBitmap;//bitmap，引入9图
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mRect = new Rect(100, 100, 450, 450);
		mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_black_bg);
		mNinePatch = new NinePatch(mBitmap, mBitmap.getNinePatchChunk(), null);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mNinePatch.draw(canvas, mRect, mPaint);
	}
 
}
