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

import android.graphics.drawable.Drawable;

public class Message {
  private String id;
  private Drawable portraitImg;
  private String portraitName;
  private Drawable messageImg;
  private String messageText;
  private String time;
  private boolean hasTag;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Drawable getPortraitImg() {
    return portraitImg;
  }

  public void setPortraitImg(Drawable portraitImg) {
    this.portraitImg = portraitImg;
  }

  public String getPortraitName() {
    return portraitName;
  }

  public void setPortraitName(String portraitName) {
    this.portraitName = portraitName;
  }

  public Drawable getMessageImg() {
    return messageImg;
  }

  public void setMessageImg(Drawable messageImg) {
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

  public boolean isHasTag() {
    return hasTag;
  }

  public void setHasTag(boolean hasTag) {
    this.hasTag = hasTag;
  }
}
