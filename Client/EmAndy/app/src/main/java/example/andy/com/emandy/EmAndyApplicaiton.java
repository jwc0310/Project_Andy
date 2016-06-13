package example.andy.com.emandy;

import android.app.Application;

import example.andy.com.emandy.opensource.UILHelper;

/**
 * Created by Andy on 16/5/25.
 */
public class EmandyApplicaiton extends Application {

    public static EmandyApplicaiton appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        //初始化UIL
        UILHelper.init(getApplicationContext());

        //网络状态变化BroadcastReceiver
        //在Manifest中静态注册
    }

}
