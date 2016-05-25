package example.andy.com.emandy;

import android.app.Application;

/**
 * Created by Andy on 16/5/25.
 */
public class EmAndyApplicaiton extends Application {

    public static EmAndyApplicaiton appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        //初始化UIL
        UILHelper.init(getApplicationContext());

    }

}
