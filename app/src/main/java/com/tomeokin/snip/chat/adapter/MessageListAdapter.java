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
package com.tomeokin.snip.chat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tomeokin.snip.R;
import com.tomeokin.snip.chat.entity.Message;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {
  private static final String TAG = "MessageListAdapter";

  class ViewHolder extends RecyclerView.ViewHolder {
    ImageView messageImage;
    TextView messageText;
    CircleImageView portraitImg;
    TextView portraitName;
    ImageView tag;

    public ViewHolder(View itemView) {
      super(itemView);
      messageImage = (ImageView) itemView.findViewById(R.id.message_image);
      messageText = (TextView) itemView.findViewById(R.id.message_text);
      portraitImg = (CircleImageView) itemView.findViewById(R.id.portrait_iv);
      portraitName = (TextView) itemView.findViewById(R.id.portrait_tv);
      tag = (ImageView) itemView.findViewById(R.id.tag_iv);
    }
  }

  private List<Message> messageList;

  public MessageListAdapter(List<Message> messageList) {
    this.messageList = messageList;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.list_item_chat_message, parent, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    final Message message = messageList.get(position);
    holder.itemView.setTag(message.getId());
    holder.messageImage.setImageDrawable(message.getMessageImg());
    holder.messageText.setText(message.getMessageText());
    holder.portraitImg.setImageDrawable(message.getPortraitImg());
    holder.portraitName.setText(message.getPortraitName());
    holder.tag.setVisibility(message.isHasTag() ? View.VISIBLE : View.GONE);
  }

  @Override
  public int getItemCount() {
    return messageList != null ? messageList.size() : 0;
  }

  public void setMessageList(List<Message> messageList) {
    this.messageList = messageList;
  }
}
