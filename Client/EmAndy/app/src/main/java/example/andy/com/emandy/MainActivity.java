package example.andy.com.emandy;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;

public class MainActivity extends BaseActivity {

    RollPagerView mRollPagerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextTitle("首页");
        setContentView(R.layout.activity_main);
        initRollPager();
    }

    //初始化rollpager
    private void initRollPager(){
        mRollPagerView = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollPagerView.setPlayDelay(1000);
        mRollPagerView.setAnimationDurtion(500);
        mRollPagerView.setAdapter(new LooperAdapter(mRollPagerView));
        mRollPagerView.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
    }

    private class LooperAdapter extends LoopPagerAdapter{
        private int[] imgs = {
                R.drawable.rollpager_img1,
                R.drawable.rollpager_img2,
                R.drawable.rollpager_img3,
                R.drawable.rollpager_img4,
                R.drawable.rollpager_img5
        };

        public LooperAdapter(RollPagerView viewPager){
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position){
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount(){
            return imgs.length;
        }
    }
}
