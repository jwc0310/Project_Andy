package example.andy.com.emandy;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * 适用于mDataList.size比较大的情况
 * 用mViewList作为缓存列表，从container删除就加入list，加入container从list中删除
 *
 * 当size比较小的时候可以用静态的，参考rollviewpager
 * Created by Andy on 16/5/27.
 */
public class BasePagerAdapter extends PagerAdapter {

    private ArrayList<?> mDataList;
    private List<View> mViewList;
    public BasePagerAdapter(ArrayList<?> dataList){
        this.mDataList = dataList;
        mViewList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        View itemView;
        if (mViewList.isEmpty()){
            itemView = new View(container.getContext());
        }else{
            itemView = mViewList.remove(0);
        }

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        View itemView = (View) object;
        container.removeView(itemView);
        mViewList.add(itemView);
    }
}
