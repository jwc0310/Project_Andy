package example.andy.com.emandy;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import example.andy.com.emandy.entity.BannerData;

/**
 * banner 循环Adapter
 * Created by Andy on 16/6/12.
 */
class LooperAdapter extends LoopPagerAdapter {

    private ImageLoader imageLoader;

    private int[] imgs = {
            R.drawable.rollpager_img1,
            R.drawable.rollpager_img2,
            R.drawable.rollpager_img3,
            R.drawable.rollpager_img4,
            R.drawable.rollpager_img5
    };

    private List<BannerData> list = new ArrayList<>();
    private LooperAdapter(RollPagerView viewPager){
        super(viewPager);
    }

    public LooperAdapter(RollPagerView viewPager, ImageLoader imageLoader){
        this(viewPager);
        this.imageLoader = imageLoader;
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
        view.setImageResource(imgs[position]);
        //imageLoader.displayImage(list.get(position).getResource(), view);
        return view;
    }

    @Override
    public int getRealCount(){
        //return list.size();
        return imgs.length;
    }
}
