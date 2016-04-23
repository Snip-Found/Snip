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
package com.tomeokin.snip.chat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tomeokin.snip.R;
import com.tomeokin.snip.chat.MessageManager;
import com.tomeokin.snip.chat.adapter.MessageListAdapter;
import com.tomeokin.snip.chat.entity.Message;
import com.tomeokin.snip.utils.uri.UriUtils;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
  public static final String EXTRA_MESSAGE_LIST_ID =
      "com.tomeokin.snip.chat.activity.message_list_id";

  TextView mTitle;
  RecyclerView mMessageListRv;
  MessageListAdapter mMessageAdapter;
  EditText mEditText;
  Button mSendButton;

  private List<Message> mMessageList = null;
  private MessageManager mMessageManager;
  private int mId;

  public static void start(Context context, int identity) {
    Intent intent = new Intent(context, ChatActivity.class);
    intent.putExtra(ChatActivity.EXTRA_MESSAGE_LIST_ID, identity);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);

    mId = getIntent().getIntExtra(EXTRA_MESSAGE_LIST_ID, -1);
    mMessageManager = MessageManager.getInstance(this);

    mTitle = (TextView) findViewById(R.id.title_tv);
    mMessageListRv = (RecyclerView) findViewById(R.id.messageList_rv);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    //mLayoutManager.setReverseLayout(true);
    //mLayoutManager.setStackFromEnd(true);
    mMessageListRv.setLayoutManager(mLayoutManager);
    mMessageListRv.setHasFixedSize(true);
    updateAdapter();

    mEditText = (EditText) findViewById(R.id.editText);
    mEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        final boolean isEmpty = TextUtils.isEmpty(s);
        mSendButton.setEnabled(!isEmpty);
        mSendButton.setBackgroundResource(
            isEmpty ? R.drawable.disabled_drawable : R.drawable.actived_drawable);
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
    mSendButton = (Button) findViewById(R.id.send_btn);
    mSendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!TextUtils.isEmpty(mEditText.getText())) {
          Message message = new Message();
          message.setPortraitImg(UriUtils.resourceIdToUri(ChatActivity.this, R.drawable.col_96));
          message.setPortraitName("收藏");
          message.setMessageText(mEditText.getText().toString());
          message.setTime(new Date().toString());
          mMessageManager.putMessageInto(mId, message);
          mEditText.setText(null);
          updateAdapter();
        }
      }
    });
  }

  private void updateAdapter() {
    if (mMessageAdapter == null) {
      mMessageList = mMessageManager.getMessageList(mId);
      mMessageAdapter = new MessageListAdapter(this, mMessageList);
      mMessageListRv.setAdapter(mMessageAdapter);
    } else {
      mMessageList = mMessageManager.getMessageList(mId);
      mMessageAdapter.setMessageList(mMessageList);
      mMessageAdapter.notifyDataSetChanged();
    }
    LinearLayoutManager manager = (LinearLayoutManager) mMessageListRv.getLayoutManager();
    manager.scrollToPositionWithOffset(mMessageAdapter.getItemCount() - 1, 0);
    //mMessageListRv.scrollToPosition(0);
  }
}
