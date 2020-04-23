package com.ttd.wanandroid.widget.skin;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import skin.support.widget.SkinCompatSupportable;
import skin.support.widget.SkinCompatTextHelper;

/**
 * Created by wt on 2018/7/11.
 */

public class SkinTextView extends TextView implements SkinCompatSupportable{
    private SkinCompatTextHelper mSkinCompatTextHelper;
    public SkinTextView(Context context) {
        super(context);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mSkinCompatTextHelper = SkinCompatTextHelper.create(this);
        mSkinCompatTextHelper.loadFromAttributes(attrs,defStyleAttr);
    }

    @Override
    public void applySkin() {
        if(mSkinCompatTextHelper != null){
            mSkinCompatTextHelper.applySkin();
        }
    }
}
