package com.ttd.wanandroid.widget.skin.support;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.ttd.wanandroid.widget.skin.SkinCompatCircleImageView;

import skin.support.app.SkinLayoutInflater;

/**
 * Created by ximsfei on 2017/3/5.
 */

public class SkinCircleImageViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(Context context, final String name, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "de.hdodenhof.circleimageview.CircleImageView":
                view = new SkinCompatCircleImageView(context, attrs);
                break;
        }
        return view;
    }
}
