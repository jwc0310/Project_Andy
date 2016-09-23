package mvprxr.http;

import java.util.List;

import mvprxr.entity.HttpResult;
import mvprxr.entity.Subject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
/**
 * Created by Administrator on 2016/9/23.
 */
public interface RxMvpService {

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

}
