package cn.banband.gaoxinjiaoyu.model.datadownload.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.activity.DataDownLoaBean;

public class DataDownloadAdapter extends RecyclerView.Adapter<DataDownloadAdapter.ViewHolder> {

    private Context context;
    private List<DataDownLoaBean> data;

    public DataDownloadAdapter(Context context, List<DataDownLoaBean> data) {
        this.context = context;
        this.data = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_datadownload, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        DataDownLoaBean bean = data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}