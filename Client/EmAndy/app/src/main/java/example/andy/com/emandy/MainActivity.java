package example.andy.com.emandy;

/**
 * 自动滑动的banner
 * okhttp的测试
 * pulltorefresh
 */

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.entity.BannerData;
import example.andy.com.emandy.entity.BannerEntity;
import example.andy.com.emandy.entity.BaseEntity;
import example.andy.com.emandy.entity.LevelGrowEntity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    static final int MENU_MANUAL_REFRESH = 0;
    static final int MENU_DISABLE_SCROLL = 1;
    static final int MENU_SET_MODE = 2;
    static final int MENU_DEMO = 3;

    private RollPagerView mRollPagerView;
    private Button button;
    private LooperAdapter adapter;
    private PullToRefreshListView mPullToRefreshListView;
    private LinkedList<String> mListItems;
    private ArrayAdapter<String> mAdapter;
    private ListView actualListView;

    //toolbar 和 drawerLayout
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextTitle("首页");
        setContentView(R.layout.activity_entry);
        initViews();
        initData();
    }

    private void initViews(){
        button = (Button) findViewById(R.id.testServer);
        button.setOnClickListener(this);
        initToolbarAndDrawer();
        initRollPager();
        initPullToRefresh();
    }

    private void initToolbarAndDrawer(){
        toolbar = (Toolbar) findViewById(R.id.tb_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);

        toolbar.setTitle(R.string.toolbar_title);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);
    }

    //初始化PullToRefresh
    private void initPullToRefresh(){
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

        //refresh
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });

        //end of list listener
        mPullToRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                Toast.makeText(MainActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
            }
        });

        actualListView = mPullToRefreshListView.getRefreshableView();

        mListItems = new LinkedList<>();
        mListItems.addAll(Arrays.asList(mStrings));

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mListItems);

        actualListView.setAdapter(mAdapter);

        registerForContextMenu(actualListView);

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
    int suffix = 1;
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.testServer){

        }
    }

    private void kk(){
        OkhttpHelper.getMilestone(mContext, "213778", "", 0, 30, new RequestCallback<LevelGrowEntity>(){

            @Override
            public void onSuccess(LevelGrowEntity response) {

            }

            @Override
            public void onFailure(int errorCode, String errorReason) {

            }
        });
        OkhttpHelper.zanOnLive(mContext, "201603282211010410000000005", "484b22f92ca14547b101550989e83c82", new RequestCallback<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity response) {
                Log.e("Andy", response.getCode()+" "+response.getDesc());
            }

            @Override
            public void onFailure(int errorCode, String errorReason) {
                Log.e("Andy", errorCode + " "+errorReason);
            }
        });

        OkhttpHelper.ModifyUserInfo(mContext, "nickName", "chen4321"+(suffix++), new RequestCallback<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity response) {
                Log.e("Andy", response.getCode()+" "+response.getDesc());
            }

            @Override
            public void onFailure(int errorCode, String errorReason) {
                Log.e("Andy", errorCode + " "+errorReason);
            }
        });

        OkhttpHelper.cancleFocus(mContext, "213771".trim(), new RequestCallback<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity response) {
                Log.e("Andy", response.getCode()+" "+response.getDesc());
            }

            @Override
            public void onFailure(int errorCode, String errorReason) {
                Log.e("Andy", errorCode + " "+errorReason);
            }
        });
    }

    private class ToolbarAdapter extends BaseAdapter {

        List<Map<String, Object>> data;

        public ToolbarAdapter(List<Map<String, Object>> data){
            this.data = data;
        }

        public void updateAdapter(){
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
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

    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");
            mAdapter.notifyDataSetChanged();

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshListView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

    private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler" };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_MANUAL_REFRESH, 0, "Manual Refresh");
        menu.add(0, MENU_DISABLE_SCROLL, 1,
                mPullToRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
                        : "Enable Scrolling while Refreshing");
        menu.add(0, MENU_SET_MODE, 0, mPullToRefreshListView.getMode() == PullToRefreshBase.Mode.BOTH ? "Change to MODE_PULL_DOWN"
                : "Change to MODE_PULL_BOTH");
        menu.add(0, MENU_DEMO, 0, "Demo");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle("Item: " + mListItems.get(info.position));
        menu.add("Item 1");
        menu.add("Item 2");
        menu.add("Item 3");
        menu.add("Item 4");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem disableItem = menu.findItem(MENU_DISABLE_SCROLL);
        disableItem
                .setTitle(mPullToRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
                        : "Enable Scrolling while Refreshing");

        MenuItem setModeItem = menu.findItem(MENU_SET_MODE);
        setModeItem.setTitle(mPullToRefreshListView.getMode() == PullToRefreshBase.Mode.BOTH ? "Change to MODE_FROM_START"
                : "Change to MODE_PULL_BOTH");

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case MENU_MANUAL_REFRESH:
                new GetDataTask().execute();
                mPullToRefreshListView.setRefreshing(false);
                break;
            case MENU_DISABLE_SCROLL:
                mPullToRefreshListView.setScrollingWhileRefreshingEnabled(!mPullToRefreshListView
                        .isScrollingWhileRefreshingEnabled());
                break;
            case MENU_SET_MODE:
                mPullToRefreshListView.setMode(mPullToRefreshListView.getMode() ==
                        PullToRefreshBase.Mode.BOTH ? PullToRefreshBase.Mode.PULL_FROM_START
                        : PullToRefreshBase.Mode.BOTH);
                break;
            case MENU_DEMO:
                mPullToRefreshListView.demo();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
