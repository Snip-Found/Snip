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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tomeokin.snip.R;
import com.tomeokin.snip.chat.MessageManager;
import com.tomeokin.snip.chat.activity.ChatActivity;
import com.tomeokin.snip.chat.entity.Message;
import com.tomeokin.snip.scrip.ScripManager;
import com.tomeokin.snip.scrip.decorator.DividerItemDecoration;
import com.tomeokin.snip.scrip.entity.Scrip;
import com.tomeokin.snip.share.adapter.SimpleChannelListAdapter;
import com.tomeokin.snip.share.entity.Share;
import com.tomeokin.snip.utils.uri.UriUtils;
import java.util.ArrayList;
import java.util.List;

public class ShareReceiveFragment extends Fragment
    implements SimpleChannelListAdapter.OnSelectedListener {
  private static final String DIALOG_SHARE = "share";
  private static final int REQUEST_SHARE = 0;

  RecyclerView mChannelListRv;
  SimpleChannelListAdapter mSimpleChannelAdapter;
  private List<Scrip> mScripList = null;
  private ScripManager mScripManager;
  private MessageManager mMessageManager;
  private Scrip mScrip;

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
    mMessageManager = MessageManager.getInstance(getContext());

    mChannelListRv = (RecyclerView) view.findViewById(R.id.channelList_rv);
    mChannelListRv.setLayoutManager(new LinearLayoutManager(getContext()));
    mChannelListRv.addItemDecoration(
        new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    updateAdapter();

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

    mScrip = scrip;
    Share share = dealIntent();
    if (share == null) {
      return;
    }

    FragmentManager fm = getActivity().getSupportFragmentManager();
    MessageRemarksFragment dialog = MessageRemarksFragment.newInstance(name, share);
    dialog.setTargetFragment(ShareReceiveFragment.this, REQUEST_SHARE);
    dialog.show(fm, DIALOG_SHARE);
  }

  private Share dealIntent() {
    Intent intent = getActivity().getIntent();
    String action = intent.getAction();
    String type = intent.getType();

    Share share = null;
    if (Intent.ACTION_SEND.equals(action) && type != null) {
      share = new Share();
      if ("text/plain".equals(type)) {
        share.setTitle(intent.getStringExtra(Intent.EXTRA_TITLE));
        share.setWebUrl(intent.getStringExtra(Intent.EXTRA_TEXT));
      } else if (type.startsWith("image/")) {
        share.setImageUrl((Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM));
      }
    } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
      if (type.startsWith("image/")) {
        //handleSendMultipleImages(intent); // Handle multiple images being sent
      }
    }

    return share;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }

    if (requestCode == REQUEST_SHARE) {
      Share share = data.getParcelableExtra(MessageRemarksFragment.EXTRA_SHARE);
      final Message message = new Message();
      message.setMessageImg(share.getImgUrl());
      message.setTagList(share.getTagList());

      String text = !TextUtils.isEmpty(share.getTitle()) ? share.getTitle() : "";
      if (!TextUtils.isEmpty(share.getWebUrl())) {
        text += !TextUtils.isEmpty(text) ? " " : "";
        text += share.getWebUrl();
      }
      message.setMessageText(text);
      message.setPortraitName("收藏"); // TODO
      message.setPortraitImg(UriUtils.resourceIdToUri(getContext(), R.drawable.col_96)); // TODO
      mMessageManager.putMessageInto(mScrip.getIdentity(), message);

      ChatActivity.start(getContext(), mScrip.getIdentity());
      getActivity().finish();
    }
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

  @Override
  public void onPause() {
    super.onPause();
    getActivity().finish();
  }
}
