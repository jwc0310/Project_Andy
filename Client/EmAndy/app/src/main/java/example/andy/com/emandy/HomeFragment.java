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
public class HomeFragment extends BaseFragment {

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
            rootView = inflater.inflate(R.layout.frament_home, null);
            initView(rootView);
            initData();
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null){
            parent.removeView(rootView);
        }
        return rootView;
    }

    /**
     * 初始化布局
     * @param view
     */
    private void initView(View view) {
        initHeader();
        mPullRefreshGridView = (PullToRefreshListView) rootView.findViewById(R.id.pull_refresh_grid);
        mGridView = mPullRefreshGridView.getRefreshableView();
        mGridView.addHeaderView(headerView);

    }

    /**
     * 初始化头部
     */
    private void initHeader(){
        headerView = LayoutInflater.from(mActivity).inflate(R.layout.home_grid_header, null);
        mRollPagerView = (RollPagerView)headerView.findViewById(R.id.roll_view_pager);
        int width = DeviceUtil.getScreenWidth(mActivity);
        int height = (int) (width / 2.678);

        ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) mRollPagerView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;

        //mRollPagerView.setLayoutParams(layoutParams);

        mRollPagerView.setPlayDelay(3000);
        mRollPagerView.setAnimationDurtion(500);
        adapter = new LooperAdapter(mRollPagerView, imageLoader);
        mRollPagerView.setAdapter(adapter);
        mRollPagerView.setHintView(new IconHintView(mActivity, R.drawable.point_focus, R.drawable.point_normal));

    }

    /**
     * 初始化数据
     */
    private void initData() {
        initBanner();
    }

    private void initBanner() {
        OkhttpHelper.getChannelBanner(mActivity, new RequestCallback<BannerEntity>() {

            @Override
            public void onSuccess(BannerEntity response) {

                final ArrayList<BannerData> list = response.getData();
                Log.e("Andy", "success");

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.init(list);
                    }
                });
            }

            @Override
            public void onFailure(int errorCode, String errorReason) {
                Log.e("Andy", errorCode + " " + errorReason);
            }
        });
    }

}
