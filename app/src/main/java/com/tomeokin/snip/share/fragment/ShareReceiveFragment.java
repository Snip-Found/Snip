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
package com.tomeokin.snip.share.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tomeokin.snip.R;
import com.tomeokin.snip.scrip.ScripManager;
import com.tomeokin.snip.scrip.decorator.DividerItemDecoration;
import com.tomeokin.snip.scrip.entity.Scrip;
import com.tomeokin.snip.share.adapter.SimpleChannelListAdapter;
import com.tomeokin.snip.share.entity.Share;
import java.util.ArrayList;
import java.util.List;

public class ShareReceiveFragment extends Fragment implements SimpleChannelListAdapter.OnSelectedListener {
  private static final String DIALOG_SHARE = "share";
  private static final int REQUEST_SHARE = 0;

  RecyclerView mChannelListRv;
  SimpleChannelListAdapter mSimpleChannelAdapter;
  private List<Scrip> mScripList = null;
  private ScripManager mScripManager;
  private Share mShare;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.activity_share_receive, container, false);

    mScripManager = ScripManager.getInstance(getContext());
    mChannelListRv = (RecyclerView) view.findViewById(R.id.channelList_rv);
    mChannelListRv.setLayoutManager(new LinearLayoutManager(getContext()));
    mChannelListRv.addItemDecoration(
        new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    updateAdapter();

    dealIntent();
    return view;
  }

  private void updateAdapter() {
    if (mSimpleChannelAdapter == null) {
      mScripList = mScripManager.getScripList();
      mSimpleChannelAdapter = new SimpleChannelListAdapter(getContext(), mScripList);
      mSimpleChannelAdapter.setListener(this);
      mChannelListRv.setAdapter(mSimpleChannelAdapter);
    } else {
      mSimpleChannelAdapter.setScripList(mScripList);
      mSimpleChannelAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onItemSelected(View view, Scrip scrip) {
    final String name = getResources().getString(R.string.send_to_arg, scrip.getTitle());

    FragmentManager fm = getActivity().getSupportFragmentManager();
    MessageRemarksFragment dialog = MessageRemarksFragment.newInstance(name, mShare);
    dialog.setTargetFragment(ShareReceiveFragment.this, REQUEST_SHARE);
    dialog.show(fm, DIALOG_SHARE);
  }

  private void dealIntent() {
    Intent intent = getActivity().getIntent();
    String action = intent.getAction();
    String type = intent.getType();

    mShare = new Share();
    if (Intent.ACTION_SEND.equals(action) && type != null) {
      if ("text/plain".equals(type)) {
        mShare.setTitle(intent.getStringExtra(Intent.EXTRA_TITLE));
        mShare.setWebUrl(intent.getStringExtra(Intent.EXTRA_TEXT));
      } else if (type.startsWith("image/")) {
        mShare.setImageUrl((Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM));
      }

    } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
      if (type.startsWith("image/")) {
        //handleSendMultipleImages(intent); // Handle multiple images being sent
      }
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    if (requestCode == REQUEST_SHARE) {
      mShare = data.getParcelableExtra(MessageRemarksFragment.EXTRA_SHARE);
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  void handleSendText(Intent intent) {
    String sharedTitle = intent.getStringExtra(Intent.EXTRA_TITLE);
    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
    Share share = new Share();
    share.setTitle(sharedTitle != null ? sharedTitle : "");
    share.setWebUrl(sharedText != null ? sharedText : "");
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
