package example.andy.com.emandy.retrofit;

import retrofit2.Retrofit;
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
}
