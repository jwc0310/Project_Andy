package example.andy.com.emandy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;

import example.andy.com.emandy.base.BaseFragment;
import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.entity.BannerData;
import example.andy.com.emandy.entity.BannerEntity;
import example.andy.com.emandy.opensource.OkhttpHelper;
import example.andy.com.emandy.utils.DeviceUtil;
import example.andy.com.emandy.utils.Logger;

/**
 * Created by Andy on 16/6/7.
 */
public class HotFragment extends BaseFragment {

    private View rootView;
    private View headerView;
    private RollPagerView mRollPagerView;
    private LooperAdapter adapter;

    private PullToRefreshListView mPullRefreshGridView;
    private ListView mGridView;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        //避免多次调用onCreateView
        if (rootView == null){
            Logger.e("rootView == null");
            rootView = inflater.inflate(R.layout.fragment_hot, null);
            initView(rootView);
            initData();
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null){
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initData() {

    }

    /**
     * 初始化布局
     * @param view
     */
    private void initView(View view) {

    }

}
