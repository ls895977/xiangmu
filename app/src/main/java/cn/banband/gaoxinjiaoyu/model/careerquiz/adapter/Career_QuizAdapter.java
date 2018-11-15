package cn.banband.gaoxinjiaoyu.model.careerquiz.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import cn.banband.gaoxinjiaoyu.model.careerquiz.CareerQuizBean;

public class Career_QuizAdapter extends BaseAdapter {

    private Context context;
    List<CareerQuizBean> data;
    private OnBackItem onBackItem;

    public void setOnBackItem(OnBackItem onBackItem) {
        this.onBackItem = onBackItem;
    }

    public Career_QuizAdapter(Context context, List<CareerQuizBean> data1) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_careerquiz, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Drawable drawable1 = context.getResources().getDrawable(R.drawable.tvboder3);
        Drawable drawable2 = context.getResources().getDrawable(R.drawable.tvboder4);
        Drawable drawable3 = context.getResources().getDrawable(R.drawable.tvboder5);
        CareerQuizBean bean = data.get(position);
        switch (bean.getStatus()) {
            case 0://未开始
                holder.stauts.setText("未开始");
                holder.stauts.setBackground(drawable3);
                break;
            case 1://答题中
                holder.stauts.setText("测试中");
                holder.stauts.setBackground(drawable2);
                break;
            case 2://已提交
                holder.stauts.setText("已完成");
                holder.stauts.setBackground(drawable1);
                break;
        }
        holder.title.setText(bean.getName());
        holder.date.setText(ms2DateOnlyDay(bean.getCreatetime()));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackItem.backItem(position);
            }
        });
        return convertView;
    }

    public class ViewHolder {
        private TextView title, stauts, date, num;
        private LinearLayout item;

        public ViewHolder(View itemView) {
            title = itemView.findViewById(R.id.item_careerquiz_title);
            stauts = itemView.findViewById(R.id.item_careerquiz_staus);
            date = itemView.findViewById(R.id.item_careerquiz_date);
            num = itemView.findViewById(R.id.item_careerquiz_num);
            item = itemView.findViewById(R.id.item_careerquiz_all);
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