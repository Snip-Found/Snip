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
package com.tomeokin.snip.share.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.tomeokin.snip.R;

public class MessageRemarksFragment extends DialogFragment
    implements DialogInterface.OnClickListener {
  private View root;

  public static MessageRemarksFragment newInstance() {
     Bundle args = new Bundle();
     MessageRemarksFragment fragment = new MessageRemarksFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    root = getActivity().getLayoutInflater().inflate(R.layout.fragment_share_edit, null);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    return builder.setTitle("分享")
        .setView(root)
        .setPositiveButton(android.R.string.ok, this)
        .setNegativeButton(android.R.string.cancel, null)
        .create();
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {

  }

  @Override
  public void onCancel(DialogInterface dialog) {
    super.onCancel(dialog);
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
  }
}
