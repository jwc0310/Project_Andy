package example.andy.com.emandy.utils;

import android.util.Log;

/**
 * Created by Andy on 16/6/7.
 */
public class Logger {

    /**
     * 开发模式 debug ＝ true;
     * 上线模式 debug ＝ false;
     */
    private static boolean debug = true;
    private static String tag = "Andy";
    public static void e(String tag, String log){
        if (debug){
            Log.e(tag, log);
        }
    }

    public static void e(String log){
        if (log == null){
            log = "";
        }
        if (debug){
            Log.e(tag, log);
        }
    }

}
