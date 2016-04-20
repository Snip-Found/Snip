/*
 * Copyright 2016 TomeOkin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tomeokin.snip.share.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.tomeokin.snip.R;
import com.tomeokin.snip.scrip.ScripManager;
import com.tomeokin.snip.scrip.decorator.DividerItemDecoration;
import com.tomeokin.snip.scrip.entity.Scrip;
import com.tomeokin.snip.share.adapter.SimpleChannelListAdapter;
import java.util.ArrayList;
import java.util.List;

public class ShareReceiveActivity extends AppCompatActivity {
  RecyclerView mChannelListRv;
  SimpleChannelListAdapter mSimpleChannelAdapter;
  private List<Scrip> mScripList = null;
  private ScripManager mScripManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_share_receive);

    mScripManager = ScripManager.getInstance(this);
    mChannelListRv = (RecyclerView) findViewById(R.id.channelList_rv);
    mChannelListRv.setLayoutManager(new LinearLayoutManager(this));
    mChannelListRv.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    updateAdapter();

    Intent intent = getIntent();
    String action = intent.getAction();
    String type = intent.getType();

    if (Intent.ACTION_SEND.equals(action) && type != null) {
      if ("text/plain".equals(type)) {
        handleSendText(intent); // Handle text being sent
      } else if (type.startsWith("image/")) {
        handleSendImage(intent); // Handle single image being sent
      }
    } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
      if (type.startsWith("image/")) {
        handleSendMultipleImages(intent); // Handle multiple images being sent
      }
    }
  }

  private void updateAdapter() {
    if (mSimpleChannelAdapter == null) {
      mScripList = mScripManager.getScripList();
      mSimpleChannelAdapter = new SimpleChannelListAdapter(this, mScripList);
      mChannelListRv.setAdapter(mSimpleChannelAdapter);
    } else {
      mSimpleChannelAdapter.setScripList(mScripList);
      mSimpleChannelAdapter.notifyDataSetChanged();
    }
  }

  void handleSendText(Intent intent) {
    String sharedTitle = intent.getStringExtra(Intent.EXTRA_TITLE);
    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);

    
  }

  void handleSendImage(Intent intent) {
    Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
    if (imageUri != null) {
      // Update UI to reflect image being shared
    }
  }

  void handleSendMultipleImages(Intent intent) {
    ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
    if (imageUris != null) {
      // Update UI to reflect multiple images being shared
    }
  }
}
