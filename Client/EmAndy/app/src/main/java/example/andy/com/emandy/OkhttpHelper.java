package example.andy.com.emandy;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import example.andy.com.emandy.callback.ErrorCode;
import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.callback.ResponseCode;
import example.andy.com.emandy.entity.BannerEntity;
import example.andy.com.emandy.entity.BaseEntity;
import example.andy.com.emandy.utils.NetworkUtil;
import example.andy.com.emandy.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andy on 16/5/26.
 */
public class OkhttpHelper {

    public static final String banner_url = "http://139.196.80.65:56520/common/banner/list";
    /**
     * 获取频道banner
     *
     * @param mContext
     * @param callback
     */
    public static void getChannelBanner(Context mContext,
                                        RequestCallback callback) {
        _get(mContext, banner_url, callback, BannerEntity.class);
    }

    //创建okHttpClient对象
    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    private static void _get(final Context mContext, String url, final RequestCallback callback, final Class<?> classType, boolean ... params) {

        if (mContext != null){
            if(!NetworkUtil.checkConnectStatus(mContext)){
                ToastUtils.centerToast(mContext, "网络未连接，请检查网络设置");
                callback.onFailure(ErrorCode.NETWORK_ERROR, "网络未连接，请检查网络设置");
                return;
            }
        }

        showDialog(mContext, params);

        //创建一个Request
        Request request = new Request.Builder()
                .header("User-Agent", "idol-app")
                .addHeader("DeviceType", "Android")
                .get()
                .url(url)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                hideDialog(mContext, e);
                callback.onFailure(ErrorCode.RETURN_ERROR, "服务器返回失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                hideDialog(mContext, null);
                String responseBody = response.body().string();
                if (response != null && response.isSuccessful()){
                    checkToken(responseBody, mContext, callback, classType);
                }
            }
        });
    }

    public static void _post(String url, Callback callback) {

    }

    private static LoadingDialog loadingDialog = null;

    /**
     * 加载有时需要显示加载dialog
     * @param context
     * @param params
     */
    private static void showDialog(Context context, boolean... params) {
        loadingDialog = null;
        if (context != null) {
            try {
                if (params != null && params.length > 0 && params[0]) {
                    loadingDialog = new LoadingDialog(context);
                    loadingDialog.show();
                }
            } catch (Exception e) {
                loadingDialog = null;
            }
        }
    }
    private static void hideDialog(Context context, IOException arg0) {
        if (null != loadingDialog) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
                if (null != arg0 && null != context) {
                    ToastUtils.centerToast(context, arg0.toString());
                }
            }
            loadingDialog = null;
        }
    }

    private static void checkToken(String response, Context context,
                                   RequestCallback callback, final Class<?> classType) {
        try {
            BaseEntity fromJson = new Gson().fromJson(response,
                    BaseEntity.class);
            if (ResponseCode.NEED_LOGIN.equals(fromJson.getCode())) {
                ToastUtils.centerToast(context, "登陆信息已失效，请重新登陆");
                callback.onFailure(ErrorCode.NETWORK_ERROR, "登陆信息已失效，请重新登陆");
            } else {
                callback.onSuccess(fromJson(response, classType));
            }

        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(ErrorCode.PARSE_ERROR, "parse异常!");
        }
    }

    /**
     * 泛型Gson转换Json2bean
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    private static <T> T fromJson(String json, Class<T> classOfT){
        Object obj = null;
        try {
            obj = new Gson().fromJson(json, classOfT);
        }catch (Exception e){

        }
        return (T) obj;
    }

}
