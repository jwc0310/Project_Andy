package example.andy.com.emandy.utils;

import android.util.Log;

/**
 * Created by chenjianwei on 16/6/7.
 */
public class Logger {

    private static boolean debug = true;
    private static String tag = "Andy";
    public static void e(String tag, String log){
        if (debug){
            Log.e(tag, log);
        }
    }

    public static void e(String log){
        if (debug){
            Log.e(tag, log);
        }
    }

}
