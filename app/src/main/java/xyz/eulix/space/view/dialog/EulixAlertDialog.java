/*
 * Copyright (c) 2022 Institute of Software, Chinese Academy of Sciences (ISCAS)
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

package xyz.eulix.space.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import xyz.eulix.space.R;

/**
 * Author:      Zhu Fuyu
 * Description: 确认对话框（包含确认、取消按钮）
 * History:     2021/9/30
 */
public class EulixAlertDialog extends Dialog {
    private TextView mTvTitle;
    private TextView mTvContent;
    private Button btnConfirm;
    private Button btnCancel;

    public EulixAlertDialog(Context context) {
        super(context, R.style.EulixDialog);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public static class Builder {
        private Context mContext;
        private String mTitleText;
        private String mContentText;
        private String mConfirmText;
        private String mCancelText;
        private OnClickListener mOnConfirmClickListener;
        private OnClickListener mOnCancelClickListener;
        private boolean mCancelable = false;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(String text) {
            this.mTitleText = text;
            return this;
        }

        public Builder setContent(String text) {
            this.mContentText = text;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            return this;
        }

        public Builder setConfirmButton(String text, OnClickListener listener) {
            this.mConfirmText = text;
            this.mOnConfirmClickListener = listener;
            return this;
        }

        public Builder setCancelButton(String text, OnClickListener listener) {
            this.mCancelText = text;
            this.mOnCancelClickListener = listener;
            return this;
        }

        public EulixAlertDialog create() {
            EulixAlertDialog dialog = new EulixAlertDialog(mContext);

            Window window = dialog.getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = mContext.getResources().getDimensionPixelSize(R.dimen.dp_259);
            window.setAttributes(layoutParams);

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.eulix_space_two_button_dialog, null);

            dialog.mTvTitle = view.findViewById(R.id.dialog_title);
            dialog.mTvContent = view.findViewById(R.id.dialog_content);
            dialog.btnConfirm = view.findViewById(R.id.dialog_confirm);
            dialog.btnCancel = view.findViewById(R.id.dialog_cancel);

            dialog.mTvTitle.setText(mTitleText);
            dialog.mTvContent.setText(mContentText);
            dialog.btnConfirm.setText(TextUtils.isEmpty(mConfirmText) ?
                    mContext.getResources().getString(R.string.confirm) : mConfirmText);
            dialog.btnCancel.setText(TextUtils.isEmpty(mCancelText) ?
                    mContext.getResources().getString(R.string.cancel) : mCancelText);
            dialog.btnConfirm.setOnClickListener(v -> {
                if (mOnConfirmClickListener != null){
                    mOnConfirmClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
                dialog.dismiss();
            });

            dialog.btnCancel.setOnClickListener(v -> {
                if (mOnCancelClickListener != null){
                    mOnCancelClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
                dialog.dismiss();
            });


            dialog.setContentView(view);
            dialog.setCancelable(mCancelable);

            return dialog;

        }
    }

}
