package viewpager.lingdian.com.viewpagerdouble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import viewpager.lingdian.com.viewpagerdouble.R;
import viewpager.lingdian.com.viewpagerdouble.adapter.TwoAdapter;
import viewpager.lingdian.com.viewpagerdouble.view.YRecycleview;

/**
 * Created by longhy on 2016/9/6.
 */
public class TwoCateFragment extends ScrollAbleFragment {

    private YRecycleview recyclerview;
    private TwoAdapter twoAdapter;
    private List<String> list = new ArrayList<>();

    public static TwoCateFragment newInstance(){
        TwoCateFragment f = new TwoCateFragment();
        Bundle b = new Bundle();
        b.putString("fragment","TwoCateFragment");
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycleview,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerview = (YRecycleview) this.getView().findViewById(R.id.yrecycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        twoAdapter = new TwoAdapter(getActivity());
        recyclerview.setAdapter(twoAdapter);
        recyclerview.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                Log.i("MissCandy","下拉刷新");
                recyclerview.setReFreshComplete();
            }

            @Override
            public void onLoadMore() {
                Log.i("MissCandy","加载更多");
                recyclerview.setloadMoreComplete();
            }
        });

        list.add("http://p1.so.qhmsg.com/sdr/720_1080_/t01de5bf40a00aaea9b.jpg");
        list.add("http://p4.so.qhmsg.com/sdr/719_1080_/t01d7c0c03443ec2129.jpg");
        list.add("http://p4.so.qhmsg.com/sdr/720_1080_/t01d813a45c64085e3e.jpg");
        list.add("http://p1.so.qhmsg.com/sdr/720_1080_/t01aa0918403910e501.jpg");
        list.add("http://p4.so.qhmsg.com/sdr/719_1080_/t010f476c4002d7d0f9.jpg");
        list.add("http://p2.so.qhmsg.com/sdr/720_1080_/t01606b07ab9f4ee40c.jpg");
        list.add("http://p3.so.qhmsg.com/sdr/720_1080_/t01025973f4da6e16c8.jpg");
        list.add("http://p0.so.qhmsg.com/sdr/1620_1080_/t0122e43c9de3d54dc5.jpg");
        twoAdapter.onReference(list);

    }


    @Override
    public View getScrollableView() {
        return recyclerview;
    }
}
