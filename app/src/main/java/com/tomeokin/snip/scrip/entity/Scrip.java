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
package com.tomeokin.snip.scrip.entity;

import android.graphics.drawable.Drawable;

public class Scrip {
  private int identity;
  private Drawable portrait;
  private String title;
  private String time;
  private String sketch;
  private int noticeCount;

  public int getIdentity() {
    return identity;
  }

  public void setIdentity(int identity) {
    this.identity = identity;
  }

  public Drawable getPortrait() {
    return portrait;
  }

  public void setPortrait(Drawable portrait) {
    this.portrait = portrait;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getSketch() {
    return sketch;
  }

  public void setSketch(String sketch) {
    this.sketch = sketch;
  }

  public int getNoticeCount() {
    return noticeCount;
  }

  public void setNoticeCount(int noticeCount) {
    this.noticeCount = noticeCount;
  }
}
