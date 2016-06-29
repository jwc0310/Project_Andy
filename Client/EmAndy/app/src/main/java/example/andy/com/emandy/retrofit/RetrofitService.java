package example.andy.com.emandy.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lk on 16/6/29.
 */
public interface RetrofitService {

    @GET("new_market")
    Call<Names> getResult(@Query("action") String action, @Query("keyword") String keyword);

}
