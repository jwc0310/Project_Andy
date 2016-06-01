package example.andy.com.emandy.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

import example.andy.com.emandy.CallBack;
import example.andy.com.emandy.LoadingDialog;
import example.andy.com.emandy.R;
import example.andy.com.emandy.customs.SystemBarTintManager;

/**
 * Created by robert on 15/7/24.
 */
public class BaseActivity extends AppCompatActivity {
    protected Activity mContext;
    protected TextView title;
    protected LinearLayout contentLayout;
    protected Button rightButton;
    protected ImageView leftButton;
    protected ImageView rightImageButton;
    protected ViewGroup nagigationBar;
    protected LoadingDialog loadingDialog;
    protected SystemBarTintManager tintManager;
    protected float DefaultAlpha = 0.0F;
    protected boolean isLoading = false;

    protected ImageLoader imageLoader;

    public boolean isLoading() {
        return isLoading;
    }

    public void setTitleSize(int size) {
        title.setTextSize(size);
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    protected void onStop() {
        super.onStop();
        if (loadingDialog.isShowing())
            loadingDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        imageLoader = ImageLoader.getInstance();
        initContentView();
        // initSysTitle();
        initTitleBar();
        hiddenAllView();
        loadingDialog = new LoadingDialog(mContext);
        // setViewMargin(contentLayout);
    }

    public void setLeftButtonHidder(boolean hidden) {
        if (hidden) {
            leftButton.setVisibility(View.GONE);
        } else {
            leftButton.setVisibility(View.VISIBLE);
        }
    }

    public void setRightButtonHidder(boolean hidden) {
        if (hidden) {
            rightButton.setVisibility(View.GONE);
        } else {
            rightButton.setVisibility(View.VISIBLE);
        }
    }

    public void setRightImageButtonHidder(boolean hidden) {
        if (hidden) {
            rightImageButton.setVisibility(View.GONE);
        } else {
            rightImageButton.setVisibility(View.VISIBLE);
        }
    }

    public void hiddenTitleBar(Boolean isHidden) {
        if (isHidden) {
            nagigationBar.setVisibility(View.GONE);
            // clearContentLayputMargin();
        } else {
            nagigationBar.setVisibility(View.VISIBLE);
        }
    }

    public void setTextTitleColor(int color) {
        title.setTextColor(color);
    }

    public void setTextTitle(String titleText) {
        title.setVisibility(View.VISIBLE);
        title.setText(titleText);
    }

    protected void setNavigationBarColor(int color) {
        nagigationBar.setBackgroundColor(color);
    }

    public void setRightButton(String btnText, int IconID,
                               final CallBack callBack) {
        rightButton.setVisibility(View.VISIBLE);
        rightImageButton.setVisibility(View.GONE);
        if (IconID > 0) {
            rightButton.setBackgroundResource(IconID);
        }
        if (!TextUtils.isEmpty(btnText)) {
            rightButton.setBackgroundResource(R.color.idolproj_transparent2);
            rightButton.setText(btnText);
        }
        rightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.run(false, null);
            }
        });
    }

    public void hiddenAllView() {
        leftButton.setVisibility(View.INVISIBLE);
        rightButton.setVisibility(View.INVISIBLE);
        rightImageButton.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
    }

    public void setLeftButton(String btnText, int id, final CallBack callack) {
        leftButton.setVisibility(View.VISIBLE);
        if (id > 0) {
            leftButton.setBackgroundResource(id);
        }
        leftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (callack != null)
                    callack.run(false, null);
            }
        });
    }

    private void initTitleBar() {
        leftButton = (ImageView) findViewById(R.id.btnLeft);
        rightButton = (Button) findViewById(R.id.btnRight);
        nagigationBar = (ViewGroup) findViewById(R.id.navigationBar);
        rightImageButton = (ImageView) findViewById(R.id.imageButtonRight);
        title = (TextView) findViewById(R.id.title);
        setNavigationBarColor(getResources().getColor(
                R.color.idolproj_transparent2));
        setTextTitleColor(getResources().getColor(R.color.idolproj_black_a));
        setLeftButton("", 0, new CallBack() {
            @Override
            public void run(boolean isError, Object t) {
                finish();
            }
        });
    }

    private void initContentView() {
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
        content.removeAllViews();
        contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        content.addView(contentLayout);
        LayoutInflater.from(this).inflate(R.layout.navigationbar,
                contentLayout, true);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, contentLayout, true);
    }

    @Override
    public void setContentView(View customContentView) {
        contentLayout.addView(customContentView);
    }

    private void initSysTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager
                .setStatusBarTintResource(R.color.default_circle_indicator_fill_color);// 通知栏所需颜色
        tintManager.setStatusBarAlpha(DefaultAlpha);
    }

    public void setStatusBarAlpha(float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            tintManager.setStatusBarAlpha(alpha);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置4.4以上的标题的title的margin防止顶出systemUi
     */
    public void setViewMargin(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) view
                    .getLayoutParams();
            params.setMargins(0, getStatusBarHeight(), 0, 0);
            view.setLayoutParams(params);
        }

    }

    /**
     * 获取状态栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 清除contlayout的margin 避免多次调用setViewMargin
     */
    private void clearContentLayputMargin() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LayoutParams params = (LayoutParams) contentLayout
                    .getLayoutParams();
            ((MarginLayoutParams) params).setMargins(0, 0, 0, 0);
            contentLayout.setLayoutParams(params);
        }
    }

    /**
     * 针对某些特殊页面需要设置Padding的情况提供方法
     *
     * @param view
     */
    public void setViewPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

    /**
     * 是否使用透明状态栏效果 需要使用4.4之后透明状态栏的调用此方法后根据是否需要title设置margin 调用setViewMargin
     */
    public void setStatusBarUsable() {
        initSysTitle();
    }
}
