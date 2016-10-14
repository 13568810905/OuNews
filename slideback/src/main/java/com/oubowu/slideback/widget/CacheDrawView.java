package com.oubowu.slideback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;


/**
 * Created by Oubowu on 2016/9/20 0020 11:19.
 */
public class CacheDrawView extends View {

    private View mCacheView;

    public CacheDrawView(Context context) {
        super(context);
    }

    public void drawCacheView(View cacheView) {
        mCacheView = cacheView;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCacheView != null) {
            mCacheView.draw(canvas);
            Log.e("TAG", "CacheDrawView-29行-onDraw(): ");
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("TAG", "CacheDrawView-37行-onDetachedFromWindow(): ");
        mCacheView = null;
    }
}
