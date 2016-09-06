package viewpager.lingdian.com.viewpagerdouble.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import viewpager.lingdian.com.viewpagerdouble.R;
import viewpager.lingdian.com.viewpagerdouble.view.scrollable.ScrollableLayout;

/**
 * Created by longhy on 2016/9/6.
 */
public class TwoFragment extends Fragment {

    private ImageView iv_top;
    private ViewPager view_pager;
    private ScrollableLayout mScrollLayout;
    private SlidingTabLayout mSlidingTabLayout;
    private List<String> titles = new ArrayList<>();


    public static TwoFragment newInstance(){
        TwoFragment f = new TwoFragment();
        Bundle b = new Bundle();
        b.putString("fragment","OneFragment");
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mScrollLayout = (ScrollableLayout) this.getView().findViewById(R.id.scrollableLayout);
        iv_top = (ImageView) this.getView().findViewById(R.id.iv_top);
        mSlidingTabLayout = (SlidingTabLayout) this.getView().findViewById(R.id.tab_layout);
        view_pager = (ViewPager) this.getView().findViewById(R.id.view_pager);

        RelativeLayout.LayoutParams linearParams =
                (RelativeLayout.LayoutParams) iv_top.getLayoutParams();
        linearParams.width = getScreenMaxWidth(0);
        int maxHeight = (int) (getScreenMaxWidth(0) / 1.50);
        linearParams.height = maxHeight;
        iv_top.setLayoutParams(linearParams);
        iv_top.setImageResource(R.mipmap.p4);
        mScrollLayout.setClickHeadExpand(maxHeight);
        init();
    }


    public void init(){
        titles.add("HOT");
        titles.add("纯色");
        titles.add("花卉");
        titles.add("线条感");
        titles.add("小清新");
        titles.add("黑白灰");
        titles.add("童趣");
        final ArrayList<ScrollAbleFragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragmentList.add(TwoCateFragment.newInstance());
        }

        final MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        view_pager.setAdapter(adapter);
        view_pager.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        view_pager.setOffscreenPageLimit(titles.size());
        view_pager.setCurrentItem(0);
        mSlidingTabLayout.setViewPager(view_pager);
        mScrollLayout.getHelper().setCurrentScrollableContainer(fragmentList.get(0));
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                mScrollLayout.getHelper().setCurrentScrollableContainer(fragmentList.get(arg0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }








    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<ScrollAbleFragment> fragmentList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<ScrollAbleFragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }


    public int getScreenMaxWidth(int paramInt) {
        Object localObject = new DisplayMetrics();
        try {
            DisplayMetrics localDisplayMetrics =
                    getActivity().getApplicationContext().getResources().getDisplayMetrics();
            localObject = localDisplayMetrics;
            return ((DisplayMetrics) localObject).widthPixels - dip2px(getActivity(), paramInt);
        } catch (Exception localException) {
            while (true) {
                localException.printStackTrace();
                ((DisplayMetrics) localObject).widthPixels = 640;
            }
        }
    }

    public int dip2px(Context context,int dipValue) {
        if(context != null){
            float reSize = context.getResources().getDisplayMetrics().density;
            return (int) ((dipValue * reSize) + 0.5);
        }
        return dipValue;
    }
}
