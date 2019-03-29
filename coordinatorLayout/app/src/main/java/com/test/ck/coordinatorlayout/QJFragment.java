package com.test.ck.coordinatorlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by W541 on 2019/3/15.
 */

public class QJFragment extends Fragment{
    private RecyclerView recyclerView;
    private Activity activity;
    private ArrayList<String> datas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        initdata();
        activity = getActivity();
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new ReAdapter(activity));
        return view;
    }

    private void initdata() {
        datas = new ArrayList<>();
        for (int i = 0;i < 50; i++){
            datas.add(String.valueOf(i));
        }
    }

    private class ReAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public ReAdapter(Activity activity) {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = View.inflate(activity, R.layout.fragment_item,null);
            MyHolder myHolder = new MyHolder(inflate);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyHolder){
                MyHolder myHolder = (MyHolder) holder;
                myHolder.text.setText(datas.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }
    class MyHolder extends RecyclerView.ViewHolder{
        TextView text;
        public MyHolder(View itemView) {
            super(itemView);
             text = (TextView)itemView.findViewById(R.id.text);
        }
    }
}
