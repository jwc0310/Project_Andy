package example.andy.com.emandy;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import example.andy.com.emandy.callback.ErrorCode;
import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.callback.ResponseCode;
import example.andy.com.emandy.entity.BannerEntity;
import example.andy.com.emandy.entity.BaseEntity;
import example.andy.com.emandy.entity.LevelGrowEntity;
import example.andy.com.emandy.utils.NetworkUtil;
import example.andy.com.emandy.utils.SecurityUtils;
import example.andy.com.emandy.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Andy on 16/5/26.
 */
public class OkhttpHelper {
    private static final String key = "QcZs3U5bpMdPCszaw3IbR8aA88FZsuLdLuVB2max9/mEO+YW7hNoHpW3nelGSyQpWzZSMwfbLEoVRVWEuqoPng==";

    private static final String HOST = "http://139.196.80.65:56520";

    public static final String banner_url = "http://139.196.80.65:56520/common/banner/list";
    /**
     * 他人成长体系
     */
    public static String GET_OTHER_MILESTONE = HOST + "/user/info/other/milestone";
    /**
     * 直播间点赞
     */
    public static String LIVE_LIKE = HOST + "/live/like";

    /**
     * 获取用户信息
     */
    public static String GET_USER_INFO = HOST + "/user/info/detail";

    /**
     * 动态详情
     */
    public static String REQUESTDYNAMICDETAIL = HOST + "/user/dynamic";

    /**
     * 取消关注
     */
    public static String CANCLE_FOCUS = REQUESTDYNAMICDETAIL + "/focus/del";

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
    /**
     * 获取他人成长体系
     */
    public static void getMilestone(Context mContext, String userId, String token,
                                    int pageInvertedIndex, int pageSize, RequestCallback callback) {
        StringBuffer sbff = new StringBuffer();
        if (token.equals("") || token.length() == 0) {
            sbff.append(GET_OTHER_MILESTONE).append("?").append("userId=")
                    .append(userId).append("&pageInvertedIndex=")
                    .append(pageInvertedIndex).append("&pageSize=")
                    .append(pageSize);
        } else {
            sbff.append(GET_OTHER_MILESTONE).append("?").append("userId=")
                    .append(userId).append("&token=").append(token).append("&pageInvertedIndex=")
                    .append(pageInvertedIndex).append("&pageSize=")
                    .append(pageSize);
        }
        _get(mContext, sbff.toString(), callback, LevelGrowEntity.class);
    }

    /**
     * 直播间点赞
     */
    public static void zanOnLive(Context mContext, String id, String token,
                                 RequestCallback callback) {
        Map<String, String> maps = new TreeMap<>();
        maps.put("id", id);
        maps.put("token", token);
        _post(mContext, LIVE_LIKE, maps, callback, BaseEntity.class);
    }

    /**
     * 修改用户信息
     */
    /**
     * <功能简述> 修改用户信息
     *
     * @param mContext
     * @param name
     * @param value
     * @param callBack
     */
    public static void ModifyUserInfo(Context mContext, String name,
                                      String value, RequestCallback callBack) {
        Map<String, String> maps = new TreeMap<>();
        maps.put(name, value);
        //maps.put("token", "");
        maps.put("token", "484b22f92ca14547b101550989e83c82");
        _put(mContext, GET_USER_INFO, maps, callBack, BaseEntity.class);
    }

    /**
     * <功能简述> 取消关注
     *
     * @param mContext
     * @param attentionUserId
     * @param callback
     */
    public static void cancleFocus(Context mContext, String attentionUserId,
                                   RequestCallback callback) {

        Map<String, String> maps = new TreeMap<>();
        maps.put("attentionUserId", attentionUserId);
        maps.put("token", "484b22f92ca14547b101550989e83c82");

        _del(mContext, CANCLE_FOCUS, maps, callback, BaseEntity.class);

    }


    /**
     *
     * 上边调用本地函数
     *
     * 下边为本地函数
     *
     */
    //创建okHttpClient对象
    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    /**
     * 有header的get请求
     * @param mContext
     * @param url
     * @param callback
     * @param classType
     * @param params
     */
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

    private static void _put(final Context mContext, String url, Map<String,String> maps, final RequestCallback callback, final Class<?> classType, boolean ... params) {
        if (mContext != null){
            if(!NetworkUtil.checkConnectStatus(mContext)){
                ToastUtils.centerToast(mContext, "网络未连接，请检查网络设置");
                callback.onFailure(ErrorCode.NETWORK_ERROR, "网络未连接，请检查网络设置");
                return;
            }
        }

        showDialog(mContext, params);
        //增加八位随机数 防止请求重复提交
        maps.put("repeatSubmi", new Random().nextInt(99999999) + "");
        addSignature(maps);
        RequestBody requestBody = buildRequestBody(maps);

        //创建一个Request
        Request request = new Request.Builder()
                .header("User-Agent", "idol-app")
                .addHeader("DeviceType", "Android")
                .put(requestBody)
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
                Log.e("Andy", responseBody);
                if (response != null && response.isSuccessful()){
                    checkToken(responseBody, mContext, callback, classType);
                }else{
                    Log.e("Andy", "response is null");
                }
            }
        });
    }

    private static void _post(final Context mContext, String url, Map<String,String> maps, final RequestCallback callback, final Class<?> classType, boolean ... params) {
        if (mContext != null){
            if(!NetworkUtil.checkConnectStatus(mContext)){
                ToastUtils.centerToast(mContext, "网络未连接，请检查网络设置");
                callback.onFailure(ErrorCode.NETWORK_ERROR, "网络未连接，请检查网络设置");
                return;
            }
        }

        showDialog(mContext, params);
        //增加八位随机数 防止请求重复提交
        maps.put("repeatSubmi", new Random().nextInt(99999999) + "");
        addSignature(maps);
        RequestBody requestBody = buildRequestBody(maps);

        //创建一个Request
        Request request = new Request.Builder()
                .header("User-Agent", "idol-app")
                .addHeader("DeviceType", "Android")
                .post(requestBody)
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
                Log.e("Andy", responseBody);
                if (response != null && response.isSuccessful()){
                    checkToken(responseBody, mContext, callback, classType);
                }else{
                    Log.e("Andy", "response is null");
                }
            }
        });
    }

    //del参数不能在entity body中传递 ？？？？
    private static void _del(final Context mContext, String url, Map<String,String> maps, final RequestCallback callback, final Class<?> classType, boolean ... params) {
        if (mContext != null){
            if(!NetworkUtil.checkConnectStatus(mContext)){
                ToastUtils.centerToast(mContext, "网络未连接，请检查网络设置");
                callback.onFailure(ErrorCode.NETWORK_ERROR, "网络未连接，请检查网络设置");
                return;
            }
        }

        showDialog(mContext, params);
        //增加八位随机数 防止请求重复提交
        maps.put("repeatSubmi", new Random().nextInt(99999999) + "");
        addSignature(maps);
        RequestBody requestBody = buildRequestBody(maps);

        StringBuilder sbff = new StringBuilder();
        sbff.append(url).append("?");

        for (Map.Entry<String, String> m : maps.entrySet()) {
            sbff.append(m.getKey()).append("=").append(m.getValue()).append("&");
        }

        //创建一个Request
        Request request = new Request.Builder()
                .header("User-Agent", "idol-app")
                .addHeader("DeviceType", "Android")
                .delete()
                .url(sbff.toString())
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
                Log.e("Andy", responseBody);
                if (response != null && response.isSuccessful()){
                    checkToken(responseBody, mContext, callback, classType);
                }else{
                    Log.e("Andy", "response is null");
                }
            }
        });
    }

    private static void addSignature(Map<String, String> maps) {
        StringBuffer sbff = new StringBuffer();
        for (Map.Entry<String, String> m : maps.entrySet()) {
            sbff.append(m.getKey()).append(m.getValue());
        }

        String keyStr = sbff.toString().toUpperCase() + key;
        String sign = SecurityUtils.md5Encode32(keyStr);
        maps.put("signature", sign);
    }

    //map 转换为 requestbody
    private static RequestBody buildRequestBody(Map<String, String> map){
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null){
            for (String key : map.keySet()){
                Log.e("Andy", "key = "+key +" ,value = "+map.get(key));
                builder.add(key, map.get(key));
            }
        }

        return builder.build();
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
