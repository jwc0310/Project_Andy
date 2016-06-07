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

        /**
         * 显示frament
         */
        UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(1,"home"));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.home:
                Logger.e("home");
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(1,"home"));
                break;
            case R.id.hot:
                Logger.e("hot");
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(2,"hot"));
                break;
            case R.id.member:
                Logger.e("member");
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(3,"member"));
                break;
            case R.id.live:
                Logger.e("live");
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(4,"live"));
                break;
            case R.id.me:
                Logger.e("me");
                UiUtils.changeFragment(this, R.id.fragment_container, ExampleFragment.newInstance(5,"me"));
                break;
            default:
                Logger.e("break");
                break;
        }
    }
}
