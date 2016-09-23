package example.andy.com.emandy.retrofit;

import example.andy.com.emandy.utils.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lk on 16/6/29.
 */
public class RetrofitHelper {

    public static final String BASE_URL = "http://www.microvirt.com.cn/";

    private Retrofit retrofit;
    private RetrofitService service;

    private RetrofitHelper(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    private static RetrofitHelper mRetrofitHelper = null;

    //静态工厂方法
    public static RetrofitHelper getInstance(){
        if (mRetrofitHelper == null){
            mRetrofitHelper = new RetrofitHelper();
        }
        return mRetrofitHelper;
    }

    public RetrofitService getService() {
        return service;
    }


    /**------------------------------------------------------------------------------------------**/


    /**********************************************************************************************/


    /**
     * get products
     */
    public void getProducts(){
        Call<HotGames> call = getService().getHotGames("GetHomeRecommend", "0", "官方");
        call.enqueue(new Callback<HotGames>() {
            @Override
            public void onResponse(Call<HotGames> call, Response<HotGames> response) {
                Logger.e("success !");
                if (response.isSuccessful()){
                    HotGames products = response.body();
                    if (products != null){
                        for (HotGames.ApkBean bean : products.getApk()){
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
                    }else{
                        Logger.e(" is null !");
                    }
                }else {
                    Logger.e(" is failed !");
                }
            }

            @Override
            public void onFailure(Call<HotGames> call, Throwable t) {
                Logger.e("failure !" + "***" + t.getMessage());
            }
        });
    }
}
