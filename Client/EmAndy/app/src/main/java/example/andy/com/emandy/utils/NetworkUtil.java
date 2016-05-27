package example.andy.com.emandy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Andy on 16/5/27.
 */
public class NetworkUtil {

    /**
     * 判断是否连接网络
     * @return
     */
    public static Boolean checkConnectStatus(Context mContext){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()){
            return true;
        }
        return false;
    }

    public static boolean isWifi(Context mContext){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }

}
