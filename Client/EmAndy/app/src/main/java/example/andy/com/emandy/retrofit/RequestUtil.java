package example.andy.com.emandy.retrofit;

import android.content.Context;

import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.utils.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/6/30.
 */
public class RequestUtil {

    private static RetrofitService service = RetrofitHelper.getInstance().getService();

    /**
     * 获取热门游戏
     * @param context
     * @param action
     * @param postion
     * @param from
     * @param callback
     */
    public static void getHotGames(Context context, String action, String postion, String from, final RequestCallback callback){
        /**
         * 此处可以显示一个加载的dialog，其他接口雷同
         * 可以添加一个Boolean 参数 来设定是否显示加载dialog
         */
        Call<HotGames> call = service.getHotGames(action, postion, from);
        if (null == null){
            callback.onFailure(0x1, "call 失败");
            return;
        }
        call.enqueue(new Callback<HotGames>() {
            @Override
            public void onResponse(Call<HotGames> call, Response<HotGames> response) {
                if (response.isSuccessful()){
                    HotGames hotGames = response.body();
                    if (hotGames != null){
                        callback.onSuccess(hotGames.getApk());

                        //打印log
                        for (HotGames.ApkBean bean : hotGames.getApk()){
                            Logger.e(bean.getId());
                            Logger.e(bean.getName());
                            Logger.e(bean.getIconUrl());
                            Logger.e(bean.getApkSize());
                            Logger.e(bean.getDownloadUrl());
                            Logger.e(bean.getDescription());
                            Logger.e(bean.getScreenshotsUrl());
                            Logger.e(bean.getDownloadTimes());
                            Logger.e(bean.getPackageName());
                            Logger.e(bean.getFrom());
                            Logger.e(bean.getVersionName());
                            Logger.e(bean.getCategoryName());
                            Logger.e("\n");
                        }
                    }else {
                        callback.onFailure(0x1,"解析失败");
                    }
                }else {
                    callback.onFailure(response.code(), "返回失败");
                }
            }

            @Override
            public void onFailure(Call<HotGames> call, Throwable t) {
                callback.onFailure(0x1, "网络问题");
            }
        });
    }

    /**
     * 获取热搜
     * @param context
     * @param action
     * @param callback
     */
    public static void getHostSearch(Context context, String action, final RequestCallback callback){

        Call<HotSearch> call = service.getHotSearch(action);
        call.enqueue(new Callback<HotSearch>() {
            @Override
            public void onResponse(Call<HotSearch> call, Response<HotSearch> response) {
                if (response.isSuccessful()){
                    HotSearch hotSearch = response.body();
                    if (hotSearch != null){
                        callback.onSuccess(hotSearch);
                    }
                }
            }

            @Override
            public void onFailure(Call<HotSearch> call, Throwable t) {

            }
        });
    }



}
