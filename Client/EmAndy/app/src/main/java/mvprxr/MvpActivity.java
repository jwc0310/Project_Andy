package mvprxr;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.andy.com.emandy.R;
import mvprxr.entity.Subject;
import mvprxr.http.HttpMethods;
import mvprxr.subscribers.ProgressSubscriber;
import mvprxr.subscribers.SubscriberOnNextListener;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * start to learn mvp+rxjava+retrofit
 * Created by Andy on 2016/9/23.
 */
public class MvpActivity extends Activity {

    @Bind(R.id.click_me_BN)
    Button btn_click;
    @Bind(R.id.result_TV)
    TextView tv_content;

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                tv_content.setText(subjects.toString());
            }
        };
    }

    @OnClick(R.id.click_me_BN)
    public void onClick(){
        //tv_content.setText("ddddddd");
        //getMovie();
        test();
    }

    //进行网络请求
    private void getMovie(){
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, MvpActivity.this), 0, 10);
    }
    private void test2(){

    }
    private void test1(){
        Observable.just("The third one")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s;
                    }
                })
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode() ;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        Log.e("XY_Andy", s+"");
                    }
                });

    }
    private void test(){
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onNext("hhhhh");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e("XY_Andy", s);
            }

            @Override
            public void onCompleted() {
                Toast.makeText(MvpActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MvpActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        };
        myObservable.subscribe(mySubscriber);


        Observable<String> myObservable2 = Observable.just("hellow world");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("XY_Andy", s);
            }
        };
        myObservable2.subscribe(onNextAction);
    }
}
