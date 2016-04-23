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
package com.tomeokin.snip.chat.entity;

import android.net.Uri;
import java.util.List;

public class Message {
  private String id;
  private Uri portraitImg;
  private String portraitName;
  private Uri messageImg;
  private String messageText;
  private String time;
  private List<String> tagList;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Uri getPortraitImg() {
    return portraitImg;
  }

  public void setPortraitImg(Uri portraitImg) {
    this.portraitImg = portraitImg;
  }

  public String getPortraitName() {
    return portraitName;
  }

  public void setPortraitName(String portraitName) {
    this.portraitName = portraitName;
  }

  public Uri getMessageImg() {
    return messageImg;
  }

  public void setMessageImg(Uri messageImg) {
    this.messageImg = messageImg;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String text) {
    this.messageText = text;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }
}
