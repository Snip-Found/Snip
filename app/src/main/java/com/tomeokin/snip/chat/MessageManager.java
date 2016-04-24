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
package com.tomeokin.snip.chat;

import android.content.Context;
import android.util.SparseArray;
import com.tomeokin.snip.R;
import com.tomeokin.snip.chat.entity.Message;
import com.tomeokin.snip.scrip.ScripManager;
import com.tomeokin.snip.utils.uri.UriUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageManager {
  private static Context context;
  private static SparseArray<List<Message>> messageCache;

  private MessageManager() {
    messageCache = new SparseArray<>();

    List<Message> messageList = new ArrayList<>();

    Message message = new Message();
    message.setMessageText("text");
    message.setTagList(Arrays.asList("tag1", "tag2"));
    message.setPortraitImg(UriUtils.resourceIdToUri(context, R.drawable.col_96));
    message.setPortraitName("收藏");

    messageList.add(message);
    messageCache.put(ScripManager.COLLECTION, messageList);
  }

  public List<Message> getMessageList(int id) {
    return messageCache.get(id);
  }

  public void setMessageList(int id, List<Message> messageList) {
    messageCache.put(id, messageList);
  }

  public void putMessageInto(int id, Message message) {
    List<Message> messageList = messageCache.get(id);
    if (messageList == null) {
      messageList = new ArrayList<>();
    }
    messageList.add(message);
    messageCache.put(id, messageList);
  }

  public static MessageManager getInstance(Context context) {
    MessageManager.context = context.getApplicationContext();
    return SingletonLoader.INSTANCE;
  }

  private static class SingletonLoader {
    static final MessageManager INSTANCE = new MessageManager();
  }
}
