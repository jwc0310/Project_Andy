package example.andy.com.emandy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
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
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import example.andy.com.emandy.base.BaseActivity;
import example.andy.com.emandy.callback.RequestCallback;
import example.andy.com.emandy.entity.BannerData;
import example.andy.com.emandy.entity.BannerEntity;
import example.andy.com.emandy.entity.BaseEntity;
import example.andy.com.emandy.entity.LevelGrowEntity;
import example.andy.com.emandy.module_vitamio.PlayerActivity;
import example.andy.com.emandy.module_vitamio.VideoViewDemo;
import example.andy.com.emandy.opensource.OkhttpHelper;
import example.andy.com.emandy.retrofit.RetrofitHelper;
import example.andy.com.pulltorefresh.LauncherActivity;
import io.vov.vitamio.demo.VitamioListActivity;
/**
 * 自动滑动的banner
 * okhttp的测试
 * pulltorefresh
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

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
    private List<Map<String, Object>> dlList;
    private ToolbarAdapter tbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTextTitle("首页");
        setContentView(R.layout.activity_entry);
        initViews();
        initData();

    }

    private void initViews() {
        button = (Button) findViewById(R.id.testServer);
        button.setOnClickListener(this);
        initToolbarAndDrawer();
        initRollPager();
        initPullToRefresh();
    }

    private void initToolbarAndDrawer() {
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

        dlList = new ArrayList<>();
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("name", "Vitamio");
        tmp.put("intent", new Intent(MainActivity.this, Vitamio.class));
        dlList.add(tmp);

        Map<String, Object> tmp1 = new HashMap<>();
        tmp1.put("name", "PlayerActivity");
        tmp1.put("intent", new Intent(MainActivity.this, PlayerActivity.class));
        dlList.add(tmp1);
        Map<String, Object> tmp2 = new HashMap<>();
        tmp2.put("name", "VideoViewDemo");
        tmp2.put("intent", new Intent(MainActivity.this, VideoViewDemo.class));
        dlList.add(tmp2);
        Map<String, Object> tmp3 = new HashMap<>();
        tmp3.put("name", "VitamioListActivity");
        tmp3.put("intent", new Intent(MainActivity.this, VitamioListActivity.class));
        dlList.add(tmp3);

        Map<String, Object> tmp4 = new HashMap<>();
        tmp4.put("name", "HomePageActivity");
        tmp4.put("intent", new Intent(MainActivity.this, HomePageActivity.class));
        dlList.add(tmp4);

        Map<String, Object> tmp5 = new HashMap<>();
        tmp5.put("name", "LauncherActivity");
        tmp5.put("intent", new Intent(MainActivity.this, LauncherActivity.class));
        dlList.add(tmp5);

        //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        tbAdapter = new ToolbarAdapter(mContext, dlList);
        lvLeftMenu.setAdapter(tbAdapter);
        lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity((Intent) dlList.get(position).get("intent"));
            }
        });
    }

    //初始化PullToRefresh
    private void initPullToRefresh() {
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
    private void initRollPager() {
        mRollPagerView = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollPagerView.setPlayDelay(1000);
        mRollPagerView.setAnimationDurtion(500);
        adapter = new LooperAdapter(mRollPagerView);
        mRollPagerView.setAdapter(adapter);
        mRollPagerView.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
    }

    private void initData() {
        OkhttpHelper.getChannelBanner(mContext, new RequestCallback<BannerEntity>() {

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
                Log.e("Andy", errorCode + " " + errorReason);
            }
        });
    }

    int suffix = 1;

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.testServer) {

            RetrofitHelper.getInstance().getProducts();
//            Log.e("Andy", "click");
//            Log.e("Andy", Environment.getExternalStorageDirectory().getAbsolutePath());
//            Log.e("Andy", Environment.getExternalStorageState());
//            Log.e("Andy", Environment.getDataDirectory().getAbsolutePath());
//            Log.e("Andy", Environment.getDownloadCacheDirectory().getAbsolutePath());
//            Log.e("Andy", Environment.getRootDirectory().getAbsolutePath());
//            final File path;
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                path = Environment.getExternalStorageDirectory();
//            }else{
//                Log.e("Andy","没有SD卡");
//                path = Environment.getRootDirectory();
//            }
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    traversalFiles(path);
//                }
//            }).start();

        }
    }

    //遍历指定目录下所有文件 打印mp3, mp4结尾
    private void traversalFiles(File root){

        File files[] = root.listFiles();
        if (files != null && files.length != 0){
            for (File f : files){
                if (f.isDirectory()){
                    traversalFiles(f);
                }else{
                    String filename = f.getName();
                    if (filename.trim().toLowerCase().endsWith(".mp4") || filename.trim().toLowerCase().endsWith(".mp3"))
                        Log.e("Andy", f.getAbsolutePath());
                }
            }
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

        private List<Map<String, Object>> data;
        private Context context;
        private LayoutInflater inflater;

        public ToolbarAdapter(Context context, List<Map<String, Object>> data){
            this.data = data;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        public void updateAdapter(){
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            final Map<String, Object> item = data.get(position);
            if (null == convertView){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.view_toolbar_item, null);

                holder.name = (TextView) convertView.findViewById(R.id.classname);
                holder.arrow = (ImageView) convertView.findViewById(R.id.arrow);
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity((Intent)item.get("intent"));
//                    }
//                });
                convertView.setTag(holder);

            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(item.get("name").toString());
            return convertView;
        }

        private class ViewHolder{
            TextView name;
            ImageView arrow;
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
