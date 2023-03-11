package com.zxj.wanandroid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.home.HomeFragment;
import com.zxj.mine.mine.MineFragment;
import com.zxj.navigation.NavigationFragment;
import com.zxj.navigation.navi.NaviFragment;
import com.zxj.project.ProjectFragment;
import com.zxj.wanandroid.databinding.ActivityMainBinding;

public class MainActivity extends BaseMvvmActivity<ActivityMainBinding, BaseViewModel> {
    Fragment[] fragments;
    String[] titles;
    int[] iconIDs;
    int[] colors;
    FragmentPagerAdapter mVpAdapter;
    @Override
    public void initViews() {
//        fragments = new Fragment[]{new HomeFragment(),new ProjectFragment(),new Fragment(),new MineFragment()};
        fragments = new Fragment[4];
        fragments[0] = new HomeFragment();
        fragments[1] = new ProjectFragment();
        fragments[2] = new NavigationFragment();
        fragments[3] = new MineFragment();
        titles = new String[]{"首页","项目","导航","我的"};
        iconIDs = new int[]{R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4};
        colors = new int[]{R.color.black, com.zxj.common.R.color.color_C1C1C1};
        mVpAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                   return fragments[position];
            }

            @Override
            public int getCount() {
                return 4;
            }


        };
        mbind.mainVp.setOffscreenPageLimit(4);
        mbind.mainVp.setAdapter(mVpAdapter);
        mbind.mainTb.setTabMode(TabLayout.MODE_FIXED);
        mbind.mainTb.setupWithViewPager(mbind.mainVp);
        for(int i = 0; i < mbind.mainTb.getTabCount(); i++){
            mbind.mainTb.getTabAt(i).setCustomView(R.layout.tab_icon);
            View view = mbind.mainTb.getTabAt(i).getCustomView();
            ImageView icon_img = (ImageView)view.findViewById(R.id.tab_icon_img);
            TextView  icon_tv  = (TextView)view.findViewById(R.id.tab_icon_tv);
            icon_img.setBackgroundResource(iconIDs[i]);
            icon_tv.setText(titles[i]);
            if(i == 0) icon_tv.setTextColor(getResources().getColor(colors[0]));

        }
    }

    @Override
    public void initEvents() {
        mbind.mainTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView  icon_tv  = (TextView)view.findViewById(R.id.tab_icon_tv);
                icon_tv.setTextColor(getResources().getColor(colors[0]));
                Log.d("tab", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView  icon_tv  = (TextView)view.findViewById(R.id.tab_icon_tv);
                icon_tv.setTextColor(getResources().getColor(colors[1]));
                Log.d("tab", "onTabUnSelected: " + tab.getPosition());
            }

            //点击tab后重新点击这个tab
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("tab", "onTabReselected: " + tab.getPosition());
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }

    public static void launch(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }




}