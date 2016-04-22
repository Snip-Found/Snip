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
package com.tomeokin.snip.share.entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Share implements Parcelable {
  private String title;
  private String webUrl;
  private Uri imgUrl;
  private List<String> tagList;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getWebUrl() {
    return webUrl;
  }

  public void setWebUrl(String webUrl) {
    this.webUrl = webUrl;
  }

  public Uri getImgUrl() {
    return imgUrl;
  }

  public void setImageUrl(Uri fileUrl) {
    this.imgUrl = fileUrl;
  }

  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.title);
    dest.writeString(this.webUrl);
    dest.writeParcelable(this.imgUrl, flags);
    dest.writeStringList(this.tagList);
  }

  public Share() {
  }

  protected Share(Parcel in) {
    this.title = in.readString();
    this.webUrl = in.readString();
    this.imgUrl = in.readParcelable(Uri.class.getClassLoader());
    this.tagList = in.createStringArrayList();
  }

  public static final Creator<Share> CREATOR = new Creator<Share>() {
    @Override
    public Share createFromParcel(Parcel source) {
      return new Share(source);
    }

    @Override
    public Share[] newArray(int size) {
      return new Share[size];
    }
  };
}
