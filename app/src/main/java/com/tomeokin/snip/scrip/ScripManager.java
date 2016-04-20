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
package com.tomeokin.snip.scrip;

import android.content.Context;
import android.util.SparseArray;
import com.tomeokin.snip.R;
import com.tomeokin.snip.scrip.entity.Scrip;
import java.util.ArrayList;
import java.util.List;

public class ScripManager {
  private static Context context;
  public static SparseArray<Class> scripMapper;
  public static final int COLLECTION = Integer.MAX_VALUE - 1;
  private static List<Scrip> scripList = null;

  private ScripManager() {
    scripList = new ArrayList<>();
    Scrip scrip = new Scrip();
    scrip.setIdentity(COLLECTION);
    scrip.setPortrait(context.getResources().getDrawable(R.drawable.col_96));
    scrip.setTitle(context.getResources().getString(R.string.collection));
    scrip.setTime("2016-04-16");
    scrip.setSketch("");
    scripList.add(scrip);
  }

  public List<Scrip> getScripList() {
    return scripList;
  }

  public void setScripList(List<Scrip> scripList) {
    ScripManager.scripList = scripList;
  }

  public static ScripManager getInstance(Context context) {
    ScripManager.context = context.getApplicationContext();
    return SingletonLoader.INSTANCE;
  }

  private static class SingletonLoader {
    static final ScripManager INSTANCE = new ScripManager();
  }
}
