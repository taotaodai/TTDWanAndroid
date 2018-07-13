package com.ttd.wanandroid.api;

import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.BannerBean;
import com.ttd.wanandroid.bean.BaseBean;
import com.ttd.wanandroid.bean.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by wt on 2018/7/11.
 */
public interface Api {
    String HOST = "http://www.wanandroid.com/";

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<UserBean> login(@Field("username") String username, @Field("password") String password);

    @POST("lg/collect/{id}/json")
    Observable<BaseBean> collect(@Path("id") int id);

    @GET("banner/json")
    Observable<BannerBean> getBanner();

    @GET("article/list/{page}/json")
    Observable<ArticleBean> getArticles(@Path("page") int page);
}
