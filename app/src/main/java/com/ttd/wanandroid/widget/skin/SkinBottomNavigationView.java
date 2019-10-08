package com.ttd.wanandroid.widget.skin;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import skin.support.design.widget.SkinMaterialBottomNavigationView;
import skin.support.widget.SkinCompatBackgroundHelper;

/**
 * Created by wt on 2018/7/4.
 */

public class SkinBottomNavigationView extends SkinMaterialBottomNavigationView{

    private SkinCompatBackgroundHelper mBackgroundTintHelper;

    public SkinBottomNavigationView(@NonNull Context context) {
        super(context);
    }

    public SkinBottomNavigationView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.onSetBackgroundResource(resId);
        }
    }

    @Override
    public void applySkin() {
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }
}
