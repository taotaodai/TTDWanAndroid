package com.ttd.wanandroid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;
import com.ttd.sdk.helper.RxHelper;
import com.ttd.sdk.utils.FileUtils;
import com.ttd.sdk.utils.ResourcesUtils;
import com.ttd.wanandroid.R;
import com.ttd.wanandroid.base.BaseCompatActivity;
import com.ttd.wanandroid.constant.BundleKeyConstant;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Created by wt on 2017/9/27.
 * <p>
 * 图片查看Activity，只有加载图片以及保存图片等简单逻辑，不采取MVP框架模式
 */

public class ImageBrowseActivity extends BaseCompatActivity {
    PhotoView pvPic;
    Toolbar toolbar;
    ProgressBar pbPicBrowse;

    private String mImageUrl;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mImageUrl = bundle.getString(BundleKeyConstant.ARG_KEY_IMAGE_BROWSE_URL);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        pvPic = findViewById(R.id.pv_pic);
        toolbar = findViewById(R.id.toolbar);
        pbPicBrowse = findViewById(R.id.pb_pic_browse);
        findViewById(R.id.fab_save_pic).setOnClickListener(view -> {
            saveImageToLocal(mImageUrl);
        });
        initTitleBar(toolbar,"");
//        StatusBarUtils.setBarColor(this, Color.BLACK);
        pvPic.enable();
        if (mImageUrl.contains("gif")) {
            loadGif();
        }else {
            loadImage();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pic_browse;
    }


    /**
     * 保存图片到本地
     *
     * @param fileName 文件名
     */
    private void saveImageToLocal(final String fileName) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> e) throws Exception {
                e.onNext(Glide.with(ImageBrowseActivity.this)
                        .load(mImageUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get());
                e.onComplete();
            }
        }).compose(RxHelper.<File>rxSchedulerHelper()).subscribe(new Consumer<File>() {
            @Override
            public void accept(File file) throws Exception {
                saveImage(fileName, file);
            }
        });
    }

    /**
     * 加载gif
     */
    private void loadGif() {
        Glide.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(pvPic) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<?
                            super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        //在这里添加一些图片加载完成的操作
                        pbPicBrowse.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 加载静态图片
     */
    private void loadImage() {
        Glide.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .fitCenter()
                .crossFade()
                .into(new GlideDrawableImageViewTarget(pvPic) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                        pbPicBrowse.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 保存图片，并且回调提示
     *
     * @param fileName 文件名
     * @param file     文件file
     */
    private void saveImage(String fileName, File file) {
        FileUtils.saveImage(ImageBrowseActivity.this, fileName, file, new FileUtils
                .SaveResultCallback() {
            @Override
            public void onSavedSuccess() {
                Snackbar.make(pvPic, "保存成功", Snackbar.LENGTH_SHORT).setActionTextColor
                        (ResourcesUtils.getColor(R.color.white)).show();
            }

            @Override
            public void onSavedFailed() {
                Snackbar.make(pvPic, "保存失败", Snackbar.LENGTH_SHORT).setActionTextColor
                        (ResourcesUtils.getColor(R.color.white)).show();
            }
        });
    }
}
