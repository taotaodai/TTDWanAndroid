package com.ttd.wanandroid.utils;

import static com.ttd.wanandroid.constant.Constant.SPKey.LANGUAGE;
import static com.ttd.wanandroid.constant.Constant.SPKey.TARGET_LANGUAGE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.ttd.sdk.bean.Language;

import java.util.List;

/**
 * @author wt
 * @time 2021/10/29
 */
public class SPDataUtils {
    @SuppressWarnings("SpellCheckingInspection")
    private static final String SP_NAME = "guozhan";

    @SuppressWarnings("unused")
    public final static String SP_LANGUAGE = "SP_LANGUAGE";
    @SuppressWarnings("unused")
    public final static String SP_COUNTRY = "SP_COUNTRY";
    public final static String SP_LAST_UPDATE_HINT_DATE = "SP_LAST_UPDATE_DATE";
    public final static String SP_MAIN_MALL_PAGE = "SP_MAIN_MALL_PAGE";
    public final static String SP_SUPPORTED_LANGUAGE = "SP_SUPPORTED_LANGUAGE";

    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static SPDataUtils instance;

    public void initContext(Context context) {
        mContext = context.getApplicationContext();
    }

    public static SPDataUtils getInstance() {
        if (instance == null) {
            instance = new SPDataUtils();
        }
        return instance;
    }

    private SPDataUtils() {
    }

    public String getTargetLanguage() {
        return get(SP_NAME, TARGET_LANGUAGE, "");
    }

    public void setTargetLanguage(String language) {
        put(SP_NAME, TARGET_LANGUAGE, language);
    }

    public void setAppLanguage(Language language) {
        put(SP_NAME, LANGUAGE, language.abbreviation);
    }

    public Language getAppLanguage() {
        String language = get(SP_NAME, LANGUAGE, Language.ZH.abbreviation);
        Language lang = Language.convert(language);
        if (lang == null) {
            return Language.ZH;
        }
        return Language.convert(language);
    }

    public String get(String name, String key, String defaultValue) {
        SharedPreferences pre = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        return pre.getString(key, defaultValue);
    }

    public String get(String key, String defaultValue) {
        return get(SP_NAME, key, defaultValue);
    }

    public void put(String name, String key,
                    String content) {
        SharedPreferences pre = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString(key, content);
        editor.apply();
        editor.commit();
    }

    public void put(String key, String content) {
        put(SP_NAME, key, content);
    }

    public void put(String key, long l) {
        SharedPreferences pre = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putLong(key, l);
        editor.apply();
        editor.commit();
    }

    public long getLong(String key) {
        SharedPreferences pre = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return pre.getLong(key, 0);
    }

//    public void setSupportedLanguage(List<BaseUserInfo.Language> languages) {
//        put(SP_SUPPORTED_LANGUAGE, new Gson().toJson(languages));
//    }

    public List<Language> getSupportedLanguage() {
        return Language.languageList();
    }
}
