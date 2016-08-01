package jorchi.datepickertest;

import android.app.Application;

/**
 * Created by yml on 16/7/29.
 */
public class MyApplication extends Application{
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}
