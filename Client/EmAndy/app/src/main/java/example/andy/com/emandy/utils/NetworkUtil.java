package example.andy.com.emandy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

/**
 * 检测网络状态工具包
 * Created by Andy on 16/5/27.
 */
public class NetworkUtil {

    /**
     * 网络连接类型
     */
    public static enum NetType{
        WIFI, CMNET, CMWAP, NONE_NET, GWAP_3, GNET_3, UNIWAP, UNINET, CTWAP, CTNET
    }

    /**
     * 判断网络是否可用
     * @param mContext
     * @return
     */
    public static boolean isNetworkAvaliable(Context mContext){
        ConnectivityManager mgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = mgr.getAllNetworkInfo();
        if (null != infos){
            for (int i = 0; i< infos.length; i++){
                if (infos[i].getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

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

    /**
     * 判断是否是wifi连接
     * @param mContext
     * @return
     */
    public static boolean isWifiConnected(Context mContext){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI){
            return true;
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * @return netType 返回类型
     * @throws
     * @方法名: getAPNType
     * @说 明: 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
     * @参 数: @param context
     * @参 数: @return
     */
    public static NetType getAPNType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE_NET;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            // 中国移动
            if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault())
                    .equals(APNNet.CMNET)) {
                return NetType.CMNET;
            } else if (networkInfo.getExtraInfo()
                    .toUpperCase(Locale.getDefault()).equals(APNNet.CMWAP)) {
                return NetType.CMWAP;
                // 中国联通
            } else if (networkInfo.getExtraInfo()
                    .toLowerCase(Locale.getDefault()).equals(APNNet.GNET_3)) {
                return NetType.GNET_3;
            } else if (networkInfo.getExtraInfo()
                    .toLowerCase(Locale.getDefault()).equals(APNNet.GWAP_3)) {
                return NetType.GWAP_3;
            } else if (networkInfo.getExtraInfo()
                    .toLowerCase(Locale.getDefault()).equals(APNNet.UNIWAP)) {
                return NetType.UNIWAP;
            } else if (networkInfo.getExtraInfo()
                    .toLowerCase(Locale.getDefault()).equals(APNNet.UNINET)) {
                return NetType.UNINET;
                // 中国联通
            } else if (networkInfo.getExtraInfo()
                    .toLowerCase(Locale.getDefault()).equals(APNNet.CTNET)) {
                return NetType.CTNET;
            } else if (networkInfo.getExtraInfo()
                    .toLowerCase(Locale.getDefault()).equals(APNNet.CTWAP)) {
                return NetType.CTNET;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE_NET;
    }

    /**
     *
     *
     */
    public static class APNNet {
        /**
         * 　 中国移动cmwap
         */
        public static String CMWAP = "cmwap";
        /**
         * 中国移动cmnet
         */
        public static String CMNET = "cmnet";
        // 中国联通3GWAP设置 中国联通3G因特网设置 中国联通WAP设置 中国联通因特网设置
        // 3gwap 3gnet uniwap uninet
        /**
         * 3G wap 中国联通3gwap APN
         */
        public static String GWAP_3 = "3gwap";

        /**
         * 3G net 中国联通3gnet APN
         */
        public static String GNET_3 = "3gnet";
        /**
         * uni wap 中国联通uni wap APN
         */
        public static String UNIWAP = "uniwap";
        /**
         * uni net 中国联通uni net APN
         */
        public static String UNINET = "uninet";

        /**
         * 中国电信 net APN
         */
        public static String CTNET = "ctnet";
        /**
         * 中国电信 net APN
         */
        public static String CTWAP = "ctwap";


    }

}
