package example.andy.com.emandy.callback;

/**
 * Created by Andy on 16/5/27.
 */
public interface RequestCallback<T> {

    void onSuccess(T response);

    void onFailure(int errorCode, String errorReason);

}
