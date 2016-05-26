package example.andy.com.emandy;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andy on 16/5/26.
 */
public class OkhttpHelper {

    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    public static void _get(String url) {
        //创建okHttpClient对象

        //创建一个Request
        final Request request = new Request.Builder()
                .addHeader("User-Agent", "idol-app")
                .addHeader("DeviceType", "Android")
                .url(url)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    public static void _post(String url, Callback callback) {

    }

}
