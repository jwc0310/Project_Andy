package example.andy.com.emandy;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class LoadingDialog extends Dialog {

    private ImageView loading;
    private RotateAnimation refreshingAnimation;

    public LoadingDialog(Context context) {
        super(context, R.style.MyLoadingDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        initView();
    }


    /**
     * <功能简述>
     */
    private void initView() {
        loading = (ImageView) findViewById(R.id.idolpro_loading_dialog_img);
        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
                getContext(), R.anim.rotating_anim);
        LinearInterpolator lir = new LinearInterpolator();
        refreshingAnimation.setInterpolator(lir);
        refreshingAnimation.setRepeatCount(3000);
//	    loading.startAnimation(refreshingAnimation);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        super.show();
        //多次show时需重新启动动画 避免出现动画卡住问题
        loading.startAnimation(refreshingAnimation);
    }
}
