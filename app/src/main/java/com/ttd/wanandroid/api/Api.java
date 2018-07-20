package com.ttd.wanandroid.api;

import com.ttd.wanandroid.bean.ArchitectureBean;
import com.ttd.wanandroid.bean.ArticleBean;
import com.ttd.wanandroid.bean.BannerBean;
import com.ttd.wanandroid.bean.BaseBean;
import com.ttd.wanandroid.bean.NavigationBean;
import com.ttd.wanandroid.bean.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by wt on 2018/7/11.
 */
public interface Api {
    String HOST = "http://www.wanandroid.com/";

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<UserBean> login(@Field("username") String username, @Field("password") String password);

    /**
     * 文章收藏
     * @param id 文章id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseBean> collect(@Path("id") int id);

    /**
     * 文章取消收藏
     * @param id 文章id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseBean> uncollect(@Path("id") int id);

    /**
     * 主页banner
     * @return
     */
    @GET("banner/json")
    Observable<BannerBean> getBanner();

    /**
     * 主页文章列表
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<ArticleBean> getArticles(@Path("page") int page);

    /**
     * 根据类型获取文章
     * @param page 页面
     * @param id 类型id
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<ArticleBean> getArticlesById(@Path("page") int page, @Query("cid") int id);

    /**
     * 体系树
     * @return
     */
    @GET("tree/json")
    Observable<ArchitectureBean> getArchitectureTree();

    /**
     * 导航数据
     * @return
     */
    @GET("navi/json")
    Observable<NavigationBean> getNavigationData();

}
