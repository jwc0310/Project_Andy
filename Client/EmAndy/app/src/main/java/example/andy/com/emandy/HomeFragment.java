package example.andy.com.emandy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.andy.com.emandy.base.BaseFragment;
import example.andy.com.emandy.utils.Logger;

/**
 * Created by Andy on 16/6/7.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View rootView;
    Button btn_test1;
    Button btn_test2;
    Button btn_test3;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        //避免多次调用onCreateView
        if (rootView == null){
            Logger.e("rootView == null");
            rootView = inflater.inflate(R.layout.frament_home, null);
            initView(rootView);
            initData();
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null){
            parent.removeView(rootView);
        }
        return rootView;
    }

    /**
     * 初始化布局
     * @param view
     */
    private void initView(View view) {
        btn_test1 = (Button) view.findViewById(R.id.test1);
        btn_test2 = (Button) view.findViewById(R.id.test2);
        btn_test3 = (Button) view.findViewById(R.id.test3);
        btn_test1.setOnClickListener(this);
        btn_test2.setOnClickListener(this);
        btn_test3.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (R.id.test1 == id){

        }else if (R.id.test2 == id){

        }else if (R.id.test3 == id){

        }
    }
}
