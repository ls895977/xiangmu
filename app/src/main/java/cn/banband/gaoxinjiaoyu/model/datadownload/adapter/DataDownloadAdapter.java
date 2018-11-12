package cn.banband.gaoxinjiaoyu.model.datadownload.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.activity.DataDownLoaBean;

public class DataDownloadAdapter extends RecyclerView.Adapter<DataDownloadAdapter.ViewHolder> {

    private Context context;
    List<DataDownLoaBean> data;

    public DataDownloadAdapter(Context context, List<DataDownLoaBean> data1) {
        this.context = context;
        this.data = data1;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_datadownload, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        DataDownLoaBean bean = data.get(position);
        holder.title.setText(bean.getTitle());
        holder.date.setText("日期："+ms2DateOnlyDay(bean.getCreatetime()));
        holder.num.setText("下载次数："+bean.getDownloads()+"次");
        holder.money.setText("¥"+position);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, date, num, money;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_DataDownload_title);
            date = itemView.findViewById(R.id.item_datadownload_date);
            num = itemView.findViewById(R.id.item_datadownload_num);
            money = itemView.findViewById(R.id.item_datadownload_money);
        }
    }
    public  String ms2DateOnlyDay(long _ms){
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }
}