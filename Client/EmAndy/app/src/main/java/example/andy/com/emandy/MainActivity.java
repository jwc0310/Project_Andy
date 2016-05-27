package example.andy.com.emandy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;
import java.util.List;

import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.entity.BannerData;
import example.andy.com.emandy.entity.BannerEntity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private RollPagerView mRollPagerView;
    private Button button;
    private LooperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextTitle("首页");
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews(){
        button = (Button) findViewById(R.id.testServer);
        button.setOnClickListener(this);
        initRollPager();
    }

    //初始化rollpager
    private void initRollPager(){
        mRollPagerView = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollPagerView.setPlayDelay(1000);
        mRollPagerView.setAnimationDurtion(500);
        adapter = new LooperAdapter(mRollPagerView);
        mRollPagerView.setAdapter(adapter);
        mRollPagerView.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
    }

    private void initData(){
        OkhttpHelper.getChannelBanner(mContext, new RequestCallback<BannerEntity>(){

            @Override
            public void onSuccess(BannerEntity response) {

                final ArrayList<BannerData> list = response.getData();
                Log.e("Andy", "success");
                for (BannerData data : response.getData()){
                    Log.e("Andy", data.getId());
                    Log.e("Andy", data.getTitle());
                    Log.e("Andy", data.getContent());
                    Log.e("Andy", data.getUrl());
                    Log.e("Andy", data.getSortIndex());
                    Log.e("Andy", data.getResource());
                    Log.e("Andy", "---------------");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.init(list);
                    }
                });
            }

            @Override
            public void onFailure(int errorCode, String errorReason) {
                Log.e("Andy", errorCode + " "+errorReason);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.testServer){

        }
    }

    private class LooperAdapter extends LoopPagerAdapter{
        private int[] imgs = {
                R.drawable.rollpager_img1,
                R.drawable.rollpager_img2,
                R.drawable.rollpager_img3,
                R.drawable.rollpager_img4,
                R.drawable.rollpager_img5
        };

        private List<BannerData> list = new ArrayList<>();
        public LooperAdapter(RollPagerView viewPager){
            super(viewPager);
        }
        public void init(ArrayList<BannerData> datas){
            list.clear();
            list.addAll(datas);
            notifyDataSetChanged();
        }

        @Override
        public View getView(ViewGroup container, int position){
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageLoader.displayImage(list.get(position).getResource(), view);
            return view;
        }

        @Override
        public int getRealCount(){
            return list.size();
        }
    }
}
