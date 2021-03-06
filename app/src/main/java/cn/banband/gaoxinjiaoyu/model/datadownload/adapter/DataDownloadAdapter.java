package cn.banband.gaoxinjiaoyu.model.datadownload.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.activity.DataDownLoaBean;

public class DataDownloadAdapter extends BaseAdapter {

    private Context context;
    List<DataDownLoaBean> data;
    private OnBackItem onBackItem;

    public void setOnBackItem(OnBackItem onBackItem) {
        this.onBackItem = onBackItem;
    }

    public DataDownloadAdapter(Context context, List<DataDownLoaBean> data1) {
        this.context = context;
        this.data = data1;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    ViewHolder holder = null;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_datadownload, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataDownLoaBean bean = data.get(position);
        holder.title.setText(bean.getTitle());
        holder.date.setText("日期：" + ms2DateOnlyDay(bean.getCreatetime()));
        holder.num.setText("下载次数：" + bean.getDownloads() + "次");
        holder.money.setText("¥" + position);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackItem.backItem(position);
            }
        });
        return convertView;
    }

    public class ViewHolder {
        private TextView title, date, num, money;
        private LinearLayout item;

        public ViewHolder(View itemView) {
            title = itemView.findViewById(R.id.item_DataDownload_title);
            date = itemView.findViewById(R.id.item_datadownload_date);
            num = itemView.findViewById(R.id.item_datadownload_num);
            money = itemView.findViewById(R.id.item_datadownload_money);
            item = itemView.findViewById(R.id.item_datadownload_all);
        }
    }

    public String ms2DateOnlyDay(long _ms) {
        Date date = new Date(_ms);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }

    public interface OnBackItem {
        void backItem(int position);
    }
}