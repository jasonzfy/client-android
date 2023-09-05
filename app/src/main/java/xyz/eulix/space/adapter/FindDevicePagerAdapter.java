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

package xyz.eulix.space.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xyz.eulix.space.R;
import xyz.eulix.space.bean.EulixDevice;
import xyz.eulix.space.util.LottieUtil;

/**
 * @author: chenjiawei
 * date: 2021/7/16 9:45
 */
public class FindDevicePagerAdapter extends PagerAdapter implements View.OnClickListener {
    private static final String TAG = FindDevicePagerAdapter.class.getSimpleName();
    private Context mContext;
    private List<EulixDevice> mEulixDeviceList;
    private OnItemClickListener mOnItemClickListener;
    private Map<Integer, View> viewMap = new HashMap<>();

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public FindDevicePagerAdapter(Context context, List<EulixDevice> eulixDeviceList) {
        mContext = context;
        mEulixDeviceList = eulixDeviceList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void updateData(List<EulixDevice> eulixDevices) {
        mEulixDeviceList = eulixDevices;
        notifyDataSetChanged();
    }

    public void updateView(int position, boolean isBusy) {
        if (position >= 0 && viewMap.containsKey(position)) {
            View view = viewMap.get(position);
            if (view != null) {
                LottieAnimationView loadingAnimation = view.findViewById(R.id.loading_animation);
                TextView content = view.findViewById(R.id.loading_content);
                if (loadingAnimation != null && content != null) {
                    if (isBusy) {
                        content.setText(R.string.binding);
                        loadingAnimation.setVisibility(View.VISIBLE);
                        LottieUtil.loop(loadingAnimation, "loading_button.json");
                    } else {
                        LottieUtil.stop(loadingAnimation);
                        loadingAnimation.setVisibility(View.GONE);
                        content.setText(R.string.confirm_bind);
                    }
                }
            }
        } else if (position == -1) {
            Set<Integer> keySet = viewMap.keySet();
            for (int key : keySet) {
                updateView(key, isBusy);
            }
        }
    }

    @Override
    public int getCount() {
        return (mEulixDeviceList == null ? 0 : mEulixDeviceList.size());
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.eulix_device_found_item, null);
        viewMap.put(position, view);
        if (mEulixDeviceList != null && mEulixDeviceList.size() > position) {
            EulixDevice eulixDevice = mEulixDeviceList.get(position);
            if (eulixDevice != null) {
                TextView name = view.findViewById(R.id.name);
                ImageButton itemExit = view.findViewById(R.id.item_exit);
                LinearLayout loadingButtonContainer = view.findViewById(R.id.loading_button_container);
                name.setText(eulixDevice.getHostName());
                loadingButtonContainer.setTag(position);
                updateView(position, false);
                loadingButtonContainer.setOnClickListener(this);
                if (mEulixDeviceList.size() <= 1) {
                    itemExit.setVisibility(View.VISIBLE);
                    itemExit.setTag(position);
                    itemExit.setOnClickListener(this);
                } else {
                    itemExit.setVisibility(View.INVISIBLE);
                    itemExit.setClickable(false);
                }
            }
            view.setTag(position);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
        } else {
            super.destroyItem(container, position, object);
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int position = (mEulixDeviceList == null ? -1 : mEulixDeviceList.indexOf(object));
        return (position == -1 ? POSITION_NONE : position);
    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            Object positionTag = v.getTag();
            if (positionTag instanceof Integer) {
                int position = (int) positionTag;
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        }
    }

    public static class ScaleTransformer implements ViewPager.PageTransformer {
        private float minScale = 1.0f;

        public ScaleTransformer(float minScale) {
            this.minScale = Math.max(Math.min(Math.abs(minScale), 1), 0);
        }

        @Override
        public void transformPage(@NonNull View page, float position) {
            if (position < -1 || position > 1) {
                page.setScaleX(minScale);
                page.setScaleY(minScale);
            } else {
                float scale = 1 - (1 - minScale) * Math.abs(position);
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
        }
    }
}
