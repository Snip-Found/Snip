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
package com.tomeokin.snip.share.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tomeokin.snip.R;
import com.tomeokin.snip.scrip.entity.Scrip;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class SimpleChannelListAdapter
    extends RecyclerView.Adapter<SimpleChannelListAdapter.ViewHolder>
    implements View.OnClickListener {
  private static final String TAG = "SimpleChannelListAdapter";

  class ViewHolder extends RecyclerView.ViewHolder {
    CircleImageView portrait;
    TextView channelTitle;

    public ViewHolder(View itemView) {
      super(itemView);
      portrait = (CircleImageView) itemView.findViewById(R.id.portrait_iv);
      channelTitle = (TextView) itemView.findViewById(R.id.channel_tv);
    }
  }

  private Context context;
  private List<Scrip> scripList;
  private OnSelectedListener listener;

  public SimpleChannelListAdapter(Context context, List<Scrip> scripList) {
    this.context = context;
    this.scripList = scripList;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.list_item_simple_channel, parent, false);
    final ViewHolder viewHolder = new ViewHolder(view);
    viewHolder.itemView.setOnClickListener(this);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    final Scrip scrip = scripList.get(position);
    holder.itemView.setTag(position);
    holder.portrait.setImageDrawable(scrip.getPortrait());
    holder.channelTitle.setText(scrip.getTitle());
  }

  @Override
  public int getItemCount() {
    return scripList != null ? scripList.size() : 0;
  }

  @Override
  public void onClick(View v) {
    final int position = (int) v.getTag();
    if (listener != null) {
      listener.onItemSelected(v, scripList.get(position));
    }
  }

  public List<Scrip> getScripList() {
    return scripList;
  }

  public void setScripList(List<Scrip> scripList) {
    this.scripList = scripList;
  }

  public interface OnSelectedListener {
    void onItemSelected(View view, Scrip scrip);
  }

  public OnSelectedListener getListener() {
    return listener;
  }

  public void setListener(OnSelectedListener listener) {
    this.listener = listener;
  }
}
