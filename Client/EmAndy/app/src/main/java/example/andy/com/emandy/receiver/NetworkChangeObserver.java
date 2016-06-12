package example.andy.com.emandy.receiver;

import example.andy.com.emandy.utils.NetworkUtil;

/**
 * 观察者需要继承该类
 * 检测网络状态改变的观察者
 * Created by Andy on 16/6/12.
 */
public class NetworkChangeObserver {

    /**
     * 网络连接时通知
     * 通知网络类型
     * @param type
     */
    public void onConnect(NetworkUtil.NetType type){

    }

    /**
     * 通知网络断开
     */
    public void onDisConnect(){

    }

}
