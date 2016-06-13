package example.andy.com.emandy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.ArrayList;
import example.andy.com.emandy.utils.NetworkUtil;

/**
 * 观察者模式使用
 * 静态注册广播，添加观察者
 * Created by Andy on 16/6/12.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private static NetworkChangeReceiver myReceiver;

    /**
     * 网络是否可用
     */
    private static Boolean networkAvailable = false;
    /**
     * 可用时网络类型
     */
    private static NetworkUtil.NetType netType;

    private static BroadcastReceiver getReceiver(){
        if (myReceiver == null){
            myReceiver = new NetworkChangeReceiver();
        }
        return myReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();

        if (!NetworkUtil.isNetworkAvaliable(context)){
            networkAvailable = false;
            Toast.makeText(context, "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected(), Toast.LENGTH_LONG).show();

        }else{
            netType = NetworkUtil.getAPNType(context);
            networkAvailable = true;
            Toast.makeText(context, "mobile:"+mobileInfo.isConnected()+"\n"+"wifi:"+wifiInfo.isConnected()
                    +"\n"+"active:"+activeInfo.getTypeName(), Toast.LENGTH_LONG).show();
        }

        notifyObserver();

    }

    /**
     *
     */
    private static ArrayList<NetworkChangeObserver> networkChangeObserverArrayList;

    /**
     * 添加网络状态改变观察者
     * @param observer
     */
    public static void registerObserver(NetworkChangeObserver observer){
        if (null == networkChangeObserverArrayList) {
            networkChangeObserverArrayList = new ArrayList<>();
        }
        networkChangeObserverArrayList.add(observer);
    }

    /**
     *
     * @param observer
     */
    public static void unRegisterObserver(NetworkChangeObserver observer){
        if (null != networkChangeObserverArrayList) {
            networkChangeObserverArrayList.remove(observer);
        }
    }

    /**
     * 广播到来，通知观察者
     */
    private void notifyObserver() {
        if (null == networkChangeObserverArrayList){
            return;
        }

        for (int i = 0; i < networkChangeObserverArrayList.size(); i++){
            if (networkAvailable){
                networkChangeObserverArrayList.get(i).onConnect(netType);
            }else {
                networkChangeObserverArrayList.get(i).onDisConnect();
            }
        }

    }

}
