package example.andy.com.emandy;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Andy on 16/5/26.
 */
public class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected ImageLoader imageLoader;
    protected LoadingDialog loadingDialog;

    protected boolean isLoading = false;
    protected boolean hasInit = false;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
        imageLoader = ImageLoader.getInstance();
        loadingDialog = new LoadingDialog(activity);
    }

    public boolean canLoadData() {
        return true;
    }

    /**
     * <功能简述> 公共初始化
     */
    public void initData() {
    }

    public boolean isEmpityData() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void setViewMargin(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
            view.setLayoutParams(params);
        }
    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setViewPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

}
