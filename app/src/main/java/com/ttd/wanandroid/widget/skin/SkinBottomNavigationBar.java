package com.ttd.wanandroid.widget.skin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.utils.Utils;
import com.ttd.wanandroid.R;

import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

/**
 * Created by wt on 2018/7/4.
 */

public class SkinBottomNavigationBar extends BottomNavigationBar implements SkinCompatSupportable{

    private int mActiveColor = INVALID_ID;
    private int mInActiveColor = INVALID_ID;

    public SkinBottomNavigationBar(Context context) {
        super(context);
    }

    public SkinBottomNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomNavigationBar, 0, 0);
        if(typedArray.hasValue(R.styleable.BottomNavigationBar_bnbActiveColor)){
            mActiveColor = typedArray.getColor(R.styleable.BottomNavigationBar_bnbActiveColor, Utils.fetchContextColor(context, R.attr.colorAccent));
        }
        if(typedArray.hasValue(R.styleable.BottomNavigationBar_bnbInactiveColor)){
            mInActiveColor = typedArray.getColor(R.styleable.BottomNavigationBar_bnbInactiveColor, Color.LTGRAY);
        }

        typedArray.recycle();
        applyActiveColor();
        applyInActiveColor();
    }

    public SkinBottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public SkinBottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void applySkin() {
        applyActiveColor();
        applyInActiveColor();
    }

    private void applyActiveColor(){
        setActiveColor(SkinCompatResources.getColor(getContext(), mActiveColor));
    }
    private void applyInActiveColor(){
        setInActiveColor(SkinCompatResources.getColor(getContext(), mInActiveColor));
    }
}
