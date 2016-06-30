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

    /**
     * 热门游戏 HotGames
     * e.g.
     * action=GetHomeRecommend2&position=0&from=官方
     * @param action
     * @param position
     * @param from
     * @return
     */
    @GET("new_market")
    Call<HotGames> getHotGames(@Query("action") String action, @Query("position") String position, @Query("from") String from);

    /**
     * 热搜 HotSearch
     * e.g.
     * action=HotSearch
     * @param action
     * @return
     */
    @GET("market/360list")
    Call<HotSearch> getHotSearch(@Query("action") String action);



}
