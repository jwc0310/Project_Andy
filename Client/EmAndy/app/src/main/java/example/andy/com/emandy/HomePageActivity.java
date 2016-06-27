package example.andy.com.emandy;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import example.andy.com.emandy.base.BaseAndyActivity;
import example.andy.com.emandy.utils.Logger;
import example.andy.com.emandy.utils.UiUtils;

/**
 * Created by Andy on 16/6/6.
 */
public class HomePageActivity extends BaseAndyActivity implements View.OnClickListener{

    private TextView home, hot, member, live, me;
    private HomeFragment homeFragment;
    private HotFragment hotFragment;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_page);
        hiddenTitleBar(true);
        initView();
    }

    private void initView(){

        home = (TextView) findViewById(R.id.home);
        home.setOnClickListener(this);
        hot = (TextView) findViewById(R.id.hot);
        hot.setOnClickListener(this);
        member = (TextView) findViewById(R.id.member);
        member.setOnClickListener(this);
        live = (TextView) findViewById(R.id.live);
        live.setOnClickListener(this);
        me = (TextView) findViewById(R.id.me);
        me.setOnClickListener(this);
        homeFragment = new HomeFragment();
        hotFragment = new HotFragment();
        /**
         * 显示frament
         */
        UiUtils.changeFragment(this, R.id.fragment_container, homeFragment);
        home.setTextColor(getResources().getColor(R.color.idolproj_red_a));
    }
    private void clear(){
        home.setTextColor(getResources().getColor(R.color.idolproj_black_a));
        hot.setTextColor(getResources().getColor(R.color.idolproj_black_a));
        member.setTextColor(getResources().getColor(R.color.idolproj_black_a));
        live.setTextColor(getResources().getColor(R.color.idolproj_black_a));
        me.setTextColor(getResources().getColor(R.color.idolproj_black_a));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.home:
                Logger.e("home");
                clear();
                home.setTextColor(getResources().getColor(R.color.idolproj_red_a));
                UiUtils.changeFragment(this, R.id.fragment_container, homeFragment);
                break;
            case R.id.hot:
                Logger.e("hot");
                clear();
                hot.setTextColor(getResources().getColor(R.color.idolproj_red_a));
                UiUtils.changeFragment(this, R.id.fragment_container, hotFragment);
                break;
            case R.id.member:
                Logger.e("member");
                clear();
                member.setTextColor(getResources().getColor(R.color.idolproj_red_a));
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(3,"member"));
                break;
            case R.id.live:
                Logger.e("live");
                clear();
                live.setTextColor(getResources().getColor(R.color.idolproj_red_a));
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(4,"live"));
                break;
            case R.id.me:
                Logger.e("me");
                clear();
                me.setTextColor(getResources().getColor(R.color.idolproj_red_a));
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(5,"me"));
                break;
            default:
                Logger.e("break");
                break;
        }
    }
}
