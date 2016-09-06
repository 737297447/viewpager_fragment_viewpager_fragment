package viewpager.lingdian.com.viewpagerdouble.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import viewpager.lingdian.com.viewpagerdouble.R;
import viewpager.lingdian.com.viewpagerdouble.adapter.OneAdapter;
import viewpager.lingdian.com.viewpagerdouble.view.YRecycleview;

/**
 * Created by longhy on 2016/9/6.
 */
public class OneFragment extends Fragment {

    private YRecycleview recyclerview;
    private OneAdapter oneAdapter;
    private List<String> list = new ArrayList<>();

    public static OneFragment newInstance(){
        OneFragment f = new OneFragment();
        Bundle b = new Bundle();
        b.putString("fragment","OneFragment");
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
        oneAdapter = new OneAdapter(getActivity());
        recyclerview.setAdapter(oneAdapter);
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

        list.add("http://p3.so.qhmsg.com/sdr/720_1080_/t01d4b941b38b7cca7d.jpg");
        list.add("http://p0.so.qhmsg.com/sdr/720_1080_/t01c2345b0a461928af.jpg");
        list.add("http://p0.so.qhmsg.com/sdr/720_1080_/t0102e22db3f17f755a.jpg");
        list.add("http://p3.so.qhmsg.com/sdr/1620_1080_/t018650cc445938d97d.jpg");
        list.add("http://p3.so.qhmsg.com/sdr/719_1080_/t016027774780be365d.jpg");
        list.add("http://p1.so.qhmsg.com/sdr/719_1080_/t0100f16f9b1a9174fb.jpg");
        list.add("http://p2.so.qhmsg.com/sdr/1619_1080_/t019d150016e25a4ba2.jpg");
        list.add("http://p2.so.qhmsg.com/sdr/720_1080_/t01f1ad79da36f90057.jpg");
        oneAdapter.onReference(list);

    }
}
