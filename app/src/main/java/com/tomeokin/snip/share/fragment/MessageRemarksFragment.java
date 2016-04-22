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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tomeokin.snip.R;
import com.tomeokin.snip.share.entity.Share;

public class MessageRemarksFragment extends DialogFragment
    implements DialogInterface.OnClickListener {
  public static final String EXTRA_SHARE = "com.tomeokin.snip.share.fragment.share";
  public static final String EXTRA_DIALOG_TITLE = "com.tomeokin.snip.share.fragment.dialog_title";

  private Share share;
  private String dialogTitle;

  private View root;
  private ImageView shareImageView;
  private TextView shareTitleTV;
  private TextView shareUrlTV;

  public static MessageRemarksFragment newInstance(String dialogTitle, Share share) {
    Bundle args = new Bundle();
    args.putParcelable(EXTRA_SHARE, share);
    args.putString(EXTRA_DIALOG_TITLE, dialogTitle);
    MessageRemarksFragment fragment = new MessageRemarksFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    share = getArguments().getParcelable(EXTRA_SHARE);
    dialogTitle = getArguments().getString(EXTRA_DIALOG_TITLE);

    root = getActivity().getLayoutInflater().inflate(R.layout.fragment_share_edit, null);
    shareImageView = (ImageView) root.findViewById(R.id.share_image);
    shareTitleTV = (TextView) root.findViewById(R.id.share_title);
    shareUrlTV = (TextView) root.findViewById(R.id.share_url);

    initView();

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    return builder.setTitle(dialogTitle)
        .setView(root)
        .setPositiveButton(android.R.string.ok, this)
        .setNegativeButton(android.R.string.cancel, null)
        .create();
  }

  private void initView() {
    if (!TextUtils.isEmpty(share.getTitle())) {
      shareTitleTV.setText(share.getTitle());
      shareTitleTV.setVisibility(View.VISIBLE);
    }
    if (!TextUtils.isEmpty(share.getWebUrl())) {
      shareUrlTV.setText(share.getWebUrl());
      shareUrlTV.setVisibility(View.VISIBLE);
    }
    if (share.getImgUrl() != null && !TextUtils.isEmpty(share.getImgUrl().toString())) {
      Log.i("take", share.getImgUrl().toString());
      shareImageView.setVisibility(View.VISIBLE);
      Glide.with(this)
          .load(share.getImgUrl())
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .into(shareImageView);
    }
  }

  private void sendResult(int resultCode) {
    if (getTargetFragment() == null) {
      return;
    }

    Intent i = new Intent();
    i.putExtra(EXTRA_SHARE, share);

    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    sendResult(Activity.RESULT_OK);
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
