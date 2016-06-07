package example.andy.com.emandy.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import example.andy.com.emandy.dialog.LoadingDialog;

/**
 * Created by Andy on 16/5/26.
 */
public class BaseFragment extends Fragment {

    protected BaseAndyActivity mActivity;
    protected ImageLoader imageLoader;
    protected LoadingDialog loadingDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseAndyActivity) activity;
        imageLoader = ImageLoader.getInstance();
        loadingDialog = new LoadingDialog(activity);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
