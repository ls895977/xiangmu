package cn.banband.gaoxinjiaoyu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.banband.gaoxinjiaoyu.R;
import cn.banband.gaoxinjiaoyu.model.GxRecruit;


public class JobRecruitListAdapter extends BaseAdapter {

    private Context context;
    private List<GxRecruit> recruitList;
    private ItemClickListener itemClickListener;

    public JobRecruitListAdapter(Context context) {
        this.context = context;
    }

    public void setRecruitList(List<GxRecruit> recruitList) {
        this.recruitList = recruitList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getCount() {
        return recruitList == null ? 0 : recruitList.size();
    }

    @Override
    public Object getItem(int position) {
        return recruitList == null ? null : recruitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recruitList == null ? 0 : recruitList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_job_recruit_list,parent, false);
        }

        final GxRecruit recruit = recruitList.get(position);

        TextView textView = convertView.findViewById(R.id.textView2);
        textView.setText(recruit.getCompany());

        //图片加载，drawable-xhdpi 下有不同尺寸的 null 图片可用
        //Glide.with(context).load(imgUrl).placeholder(R.drawable.icon_null).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgv);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null){
                    itemClickListener.onItemClickListener(recruit);
                }
            }
        });

        return convertView;
    }

    public interface ItemClickListener {
        public void onItemClickListener(GxRecruit recruit);
    }
}
