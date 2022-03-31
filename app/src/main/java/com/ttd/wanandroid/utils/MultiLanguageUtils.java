package com.ttd.wanandroid.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;

import com.ttd.sdk.bean.Language;
import com.ttd.sdk.utils.AppManager;
import com.ttd.sdk.utils.AppUtils;
import com.ttd.wanandroid.ui.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * @author wt
 * @time 2021/10/29
 */
public class MultiLanguageUtils {
    public static void changeLanguage(Language language) {
        changeAppLanguage(AppUtils.getContext(), language, true);
    }

    /**
     * @param persistence 是否持久化
     */
    public static void changeAppLanguage(Context context, Language language, boolean persistence) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = convertToLocale(language);
        setLanguage(context, locale, configuration);
        resources.updateConfiguration(configuration, metrics);
        if (persistence) {
            saveLanguageSetting(language);
        }
    }

    private static void setLanguage(Context context, Locale locale, Configuration configuration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
            configuration.setLocales(new LocaleList(locale));
            context.createConfigurationContext(configuration);
        } else {
            configuration.setLocale(locale);
        }
    }


    /**
     * 判断sp中和app中的多语言信息是否相同
     */
    public static boolean isSameWithSetting(Context context) {
        Locale locale = getAppLocale(context);
        Language language = SPDataUtils.getInstance().getAppLanguage();

        return locale.toString().equalsIgnoreCase(language.abbreviation);
    }


    /**
     * 保存多语言信息到sp中
     */
    public static void saveLanguageSetting(Language language) {
        SPDataUtils.getInstance().setAppLanguage(language);
    }

    /**
     * 获取应用语言
     */
    public static Locale getAppLocale(Context context) {
        Locale local;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            local = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            local = context.getResources().getConfiguration().locale;
        }
        return local;
    }

    /**
     * 获取系统语言
     */
    public static LocaleListCompat getSystemLanguage() {
        Configuration configuration = Resources.getSystem().getConfiguration();
        return ConfigurationCompat.getLocales(configuration);
    }

    //注册Activity生命周期监听回调，此部分一定加上，因为有些版本不加的话多语言切换不回来
    //registerActivityLifecycleCallbacks(callbacks);
    public static Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull @NotNull Activity activity, @Nullable Bundle savedInstanceState) {
            Language language = SPDataUtils.getInstance().getAppLanguage();
            //强制修改应用语言
            if (!isSameWithSetting(activity)) {
                changeAppLanguage(activity, language, false);
            }
        }

        @Override
        public void onActivityStarted(@NonNull @NotNull Activity activity) {
        }

        @Override
        public void onActivityResumed(@NonNull @NotNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull @NotNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull @NotNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull @NotNull Activity activity, @NonNull @NotNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull @NotNull Activity activity) {

        }
    };

    /**
     * 判断应用于系统语言是否相同
     */
    @SuppressWarnings("unused")
    public static boolean isSameLocal(Locale appLocale, Language language) {
        return appLocale.toString().equals(language.abbreviation);
    }

    private static Locale convertToLocale(Language language) {
        Locale locale;
        if (language == Language.ZH) {
            locale = Locale.CHINA;
        } else if (language == Language.EN) {
            locale = Locale.US;
        } else if (language == Language.JP) {
            locale = Locale.JAPAN;
        } else if (language == Language.KR) {
            locale = Locale.KOREA;
        } else if(language == Language.RU){
            locale = new Locale("ru","RU");
        }else if(language == Language.ES){
            locale = new Locale("es","ES");
        }else {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        return locale;
    }

    public static void changeAppLanguage(Language language) {
        MultiLanguageUtils.changeLanguage(language);
        AppManager.getAppManager().finishAllActivity();
        MainActivity.Companion.startChangeLanguage(AppUtils.getContext());
    }
}
