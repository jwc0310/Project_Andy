/*
 * Copyright (C) 2011 VOV IO (http://vov.io/)
 */

package example.andy.com.emandy.module_vitamio;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import example.andy.com.emandy.R;
import example.andy.com.emandy.utils.DensityUtil;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoViewDemo extends Activity {

	private String path = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/Tencent/QQfile_recv/短袖版-1.mp4";
	private VideoView mVideoView;
	private AudioManager mAudioManager;
	
	private RelativeLayout gesture_volume_layout;//音量控制布局
	private TextView geture_tv_volume_percentage;//音量百分比
	private ImageView gesture_iv_player_volume;//音量图标

	private RelativeLayout gesture_progress_layout;//进度图标布局
	private TextView geture_tv_progress_time;//播放时间进度
	private ImageView gesture_iv_progress;//快进或快退标志

	private int maxVolume, currentVolume;
	private static final float STEP_PROGRESS = 2f;//设定进度滑动时的步长，避免每次滑动都改变，导致改变过快
	private static final float STEP_VOLUME = 2f;//协调音量滑动时的步长，避免每次滑动都改变，导致改变过快
	private boolean firstScroll = false;//每次触摸屏幕后，第一次scroll的标志
	
	private int GESTURE_FLAG = 0;//1,调节进度，2，调节音量
	private static final int GESTURE_MODIFY_PROGRESS = 1;
	private static final int GESTURE_MODIFY_VOLUME = 2;
	
	
	private GestureDetector mGestureDetector;
	private MediaController mediaController;
	
	  private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;
	private int maxLength ;
	private int currentLentth;
	
	private static final float vsped = 150f;
	private static final float hsped = 150f;
	
	private static final float HORIZENTAOL = 0.2f;
	private static final float VITER = 0.2f;
	int screenWidth ;
	int screenHeitht;
	
	
	private long palyerCurrentPosition;  //度播放的当前标志，毫秒
	private long playerDuration ;//播放资源的时长，毫秒
	Context context;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
        context = this;
		setContentView(R.layout.videoview1);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		gesture_volume_layout = (RelativeLayout) findViewById(R.id.gesture_volume_layout);
		gesture_progress_layout = (RelativeLayout) findViewById(R.id.gesture_progress_layout);
		geture_tv_progress_time = (TextView) findViewById(R.id.geture_tv_progress_time);
		geture_tv_volume_percentage = (TextView) findViewById(R.id.geture_tv_volume_percentage);
		gesture_iv_progress = (ImageView) findViewById(R.id.gesture_iv_progress);
		gesture_iv_player_volume = (ImageView) findViewById(R.id.gesture_iv_player_volume);
		
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mVideoView.setVideoPath(path);
		mediaController = new MediaController(this);
		mVideoView.setMediaController(mediaController);
		mVideoView.requestFocus();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics( dm );
		screenWidth = dm.widthPixels;
		screenHeitht = dm.heightPixels;
		maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		mGestureDetector = new GestureDetector(this, new MyGestectoroListener());

	}

	class MyGestectoroListener extends SimpleOnGestureListener {
	
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			if (firstScroll) {//以触摸屏幕后第一次滑动为标准，避免在屏幕上操作切换混乱
				//横向的距离变化大则调整进度，纵向的变化大则调整音量
				if (Math.abs(distanceX) >= Math.abs(distanceY)) {
					gesture_volume_layout.setVisibility(View.INVISIBLE);
					gesture_progress_layout.setVisibility(View.VISIBLE);
					GESTURE_FLAG = GESTURE_MODIFY_PROGRESS;
				} else {
					gesture_volume_layout.setVisibility(View.VISIBLE);
					gesture_progress_layout.setVisibility(View.INVISIBLE);
					GESTURE_FLAG = GESTURE_MODIFY_VOLUME;
				}
			}
			palyerCurrentPosition = mVideoView.getCurrentPosition();
			playerDuration = mVideoView.getDuration();
			
			//如果每次触摸屏幕后第一次scroll是调节进度，那之后的scroll事件都处理音量进度，直到离开屏幕执行下一次操作
			if (GESTURE_FLAG == GESTURE_MODIFY_PROGRESS) {
				// distanceX=lastScrollPositionX-currentScrollPositionX，因此为正时是快进
				if (Math.abs(distanceX) > Math.abs(distanceY)) {//横向移动大于纵向移动
					if (distanceX >= DensityUtil.dip2px(context, STEP_PROGRESS)) {//快退，用步长控制改变速度，可微调
						gesture_iv_progress.setImageResource(R.mipmap.souhu_player_backward);
						if (palyerCurrentPosition > 3 * 1000) {//避免为负
							palyerCurrentPosition -= 3 * 1000;// scroll方法执行一次快退三秒
						} else {
							palyerCurrentPosition = 3 * 1000;
						}
					} else if (distanceX <= -DensityUtil.dip2px(context, STEP_PROGRESS)) {//快进
						gesture_iv_progress.setImageResource(R.mipmap.souhu_player_forward);
						if (palyerCurrentPosition < playerDuration - 16 * 1000) {//避免超过总时长
							palyerCurrentPosition += 3 * 1000;//scroll方法执行一次快进3秒
						} else {
							palyerCurrentPosition = playerDuration - 10 * 1000;
						}
					}
				}

				geture_tv_progress_time.setText(converLongTimeToStr(palyerCurrentPosition) + "/"
						+ converLongTimeToStr(playerDuration));
				mVideoView.seekTo(palyerCurrentPosition);

			}
			//如果每次触摸屏幕后第一次scroll是调节音量，那之后的scroll事件都处理音量调节，直到离开屏幕执行下一次操作
			else if (GESTURE_FLAG == GESTURE_MODIFY_VOLUME) {
				currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //获取当前值
				if (Math.abs(distanceY) > Math.abs(distanceX)) {//纵向移动大于横向移动
					if (distanceY >= DensityUtil.dip2px(context, STEP_VOLUME)) {//音量调大,注意横屏时的坐标体系,尽管左上角是原点，但横向向上滑动时distanceY为正
						if (currentVolume < maxVolume) {//为避免调节过快，distanceY应大于一个设定值
							currentVolume++;
						}
						gesture_iv_player_volume.setImageResource(R.mipmap.souhu_player_volume);
					} else if (distanceY <= -DensityUtil.dip2px(context, STEP_VOLUME)) {//音量调小
						if (currentVolume > 0) {
							currentVolume--;
							if (currentVolume == 0) {//静音，设定静音独有的图片
								gesture_iv_player_volume.setImageResource(R.mipmap.souhu_player_silence);
							}
						}
					}
					int percentage = (currentVolume * 100) / maxVolume;
					geture_tv_volume_percentage.setText(percentage + "%");
					mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 0);
				}

			}

			firstScroll = false;/**第一次scroll执行完成，修改标志**/
			return false;
		}

		  /** 双击 */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mLayout == VideoView.VIDEO_LAYOUT_ZOOM)
                mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
            else
                mLayout++;
            if (mVideoView != null)
                mVideoView.setVideoLayout(mLayout, 0);
            return true;
        }
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
		     firstScroll = true;
		     
			return false;
		}
		
		

	}

	
	/**
	 * 转换毫秒数成“分、秒”，如“01:53”。若超过60分钟则显示“时、分、秒”，如“01:01:30
	 *
	 * @param time
	 * 待转换的毫秒数
	 *
	 * */
	private String converLongTimeToStr(long time) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;

		long hour = (time) / hh;
		long minute = (time - hour * hh) / mi;
		long second = (time - hour * hh - minute * mi) / ss;

		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		if (hour > 0) {
			return strHour + ":" + strMinute + ":" + strSecond;
		} else {
			return strMinute + ":" + strSecond;
		}
	}
	


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (mGestureDetector.onTouchEvent(event)) {
			return true;
		}
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_UP:
			endGesture();
			break;

		default:
			break;
		}

		return super.onTouchEvent(event);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
			case 0:
				GESTURE_FLAG = 0;//手指离开屏幕后，重置调节音量或进度的标志
				gesture_volume_layout.setVisibility(View.INVISIBLE);
				gesture_progress_layout.setVisibility(View.INVISIBLE);
				break;

			default:
				break;
			}
		};
	};

	/** 手势结束 */
	private void endGesture() {

		//隐藏
		handler.removeMessages(0);
		handler.sendEmptyMessageDelayed(0, 500);
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		playerDuration =mVideoView.getDuration();
		Log.i("tag", "onresume中总长度"+playerDuration);
		super.onResume();
	}
}
