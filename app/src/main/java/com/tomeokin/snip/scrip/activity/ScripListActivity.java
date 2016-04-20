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
package com.tomeokin.snip.scrip.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.tomeokin.snip.R;
import com.tomeokin.snip.scrip.ScripManager;
import com.tomeokin.snip.scrip.adapter.ScripListAdapter;
import com.tomeokin.snip.scrip.decorator.DividerItemDecoration;
import com.tomeokin.snip.scrip.entity.Scrip;
import java.util.List;

public class ScripListActivity extends AppCompatActivity {
  RecyclerView mScripListRv;
  ScripListAdapter mScripAdapter;
  private List<Scrip> mScripList = null;
  private ScripManager mScripManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scrip_list);

    mScripManager = ScripManager.getInstance(this);
    mScripListRv = (RecyclerView) findViewById(R.id.scripList_rv);
    mScripListRv.setLayoutManager(new LinearLayoutManager(this));
    mScripListRv.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    updateAdapter();
  }

  private void updateAdapter() {
    if (mScripAdapter == null) {
      mScripList = mScripManager.getScripList();
      mScripAdapter = new ScripListAdapter(this, mScripList);
      mScripListRv.setAdapter(mScripAdapter);
    } else {
      mScripAdapter.setScripList(mScripList);
      mScripAdapter.notifyDataSetChanged();
    }
  }
}
