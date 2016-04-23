package nz.co.trademe;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.leakcanary.LeakCanary;

public class TradeMeApp extends Application {

    public static final String TAG = TradeMeApp.class.getName();
    private static TradeMeApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

    public TradeMeApp() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    public static TradeMeApp getInstance() {
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return getSharedPreferences("nz.co.trademe.app.preferences",0);
    }


}
