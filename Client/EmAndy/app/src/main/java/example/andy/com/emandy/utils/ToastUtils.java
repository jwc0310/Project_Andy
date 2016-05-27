package example.andy.com.emandy.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import example.andy.com.emandy.R;

/**
 * Created by robert on 15/7/28.
 */
public class ToastUtils {
    public static void toast(Context ctx, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void centerToast(Context ctx, String msg) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        if (!TextUtils.isEmpty(msg)) {
            toast.show();
        }
    }

    public static void toast(Context ctx, int msgId) {
        Toast.makeText(ctx, msgId, Toast.LENGTH_SHORT).show();
    }

    public static void centerToast(Context ctx, int msgId) {
        Toast toast = Toast.makeText(ctx, msgId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void createNetError(Activity context) {
        createCustomToast(context, "亲的网络不给力哦，快点检查下！");
    }

    public static void createServerError(Activity context) {
        createCustomToast(context, "爱豆娘躺了(ノД`)");
    }

    public static void createEndData(Activity context) {
        createCustomToast(context, "没有啦QAQ");
    }

    public static void pullUpWithoutData(Activity context) {
        createCustomToast(context, "暂时没有更多了哟～");
    }

    public static void pullDownWithoutData(Activity context) {
        createCustomToast(context, "努力刷新中");
    }

    public static void createCustomToast(Activity context, String s) {
        if (context == null)
            return;
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_main, null);
        TextView text = (TextView) layout.findViewById(R.id.toast_text);
        text.setText(s);
        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
