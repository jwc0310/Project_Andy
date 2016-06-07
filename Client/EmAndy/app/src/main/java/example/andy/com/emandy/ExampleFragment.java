package example.andy.com.emandy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.andy.com.emandy.base.BaseFragment;

/**
 * Created by Andy on 16/6/7.
 */
public class ExampleFragment extends BaseFragment {

    private int type;
    private String content;
    private TextView tv;

    public static ExampleFragment newInstance(int type, String content){
        ExampleFragment frag = new ExampleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("content", content);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);

        Bundle args = getArguments();
        this.type = args.getInt("type");
        this.content = args.getString("content");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.fragment_main, null);
        tv = (TextView)view.findViewById(R.id.content);
        tv.setText(content);
        return view;

    }

}
