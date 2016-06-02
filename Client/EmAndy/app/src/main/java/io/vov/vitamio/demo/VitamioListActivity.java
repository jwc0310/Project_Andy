/*
 * Copyright (C) 2013 YIXIA.COM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.vov.vitamio.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import example.andy.com.emandy.R;
import io.vov.vitamio.LibsChecker;

/**
 * List
 */
public class VitamioListActivity extends Activity implements View.OnClickListener{

	private TextView tv1, tv2, tv3, tv4, tv5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.main);
		initView();
	}

	private void initView(){
		tv1 = (TextView) findViewById(R.id.aa);
		tv2 = (TextView) findViewById(R.id.ab);
		tv3 = (TextView) findViewById(R.id.ac);
		tv4 = (TextView) findViewById(R.id.ad);
		tv5 = (TextView) findViewById(R.id.ae);

		tv1.setOnClickListener(this);
		tv3.setOnClickListener(this);
		tv2.setOnClickListener(this);
		tv4.setOnClickListener(this);
		tv5.setOnClickListener(this);

	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "MediaPlayer", new Intent(this, MediaPlayerDemo.class));
		addItem(myData, "VideoView", new Intent(this, VideoViewDemo.class));
		addItem(myData, "MediaMetadata", new Intent(this, MediaMetadataRetrieverDemo.class));
		addItem(myData, "VideoSubtitle", new Intent(this, VideoSubtitleList.class));
		addItem(myData, "VideoViewBuffer", new Intent(this, VideoViewBuffer.class));
		return myData;
	}

	protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		Intent intent;
		if (R.id.aa == id){
			intent = new Intent(this, MediaPlayerDemo.class);
		}else if (R.id.ab == id){
			intent = new Intent(this, VideoViewDemo.class);
		}else if (R.id.ac == id){
			intent = new Intent(this, MediaMetadataRetrieverDemo.class);
		}else if (R.id.ad == id){
			intent = new Intent(this, VideoSubtitleList.class);
		}else{
			intent = new Intent(this, VideoViewBuffer.class);
		}

		startActivity(intent);
	}
}
