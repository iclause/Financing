package com.mga.financing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.bean.ProductBean;

import java.util.List;
import java.util.Map;

/**
 * mga
 * 具有显示不同item组header功能
 * 通过一个记录每个组中第一个item位置的map<object,Integer>匹配adapter中当前位置，来决定是否显示header
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ProductBean> mProductBeanList;
    private Context mContext;
    private LayoutInflater mInflater;
    private Map<Integer, Integer> mLettes;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_CONTENT = 0;

    private static final int TYPE_HEADER1 = 0; //持有黄金 保值增值
    private static final int TYPE_HEADER2 = 1; //稳定生产 省时省力
    private String TAG=MyAdapter.class.getSimpleName();

    public MyAdapter(Context context, List<ProductBean> ProductBeanList,
                     Map<Integer, Integer> lettes) {
        mProductBeanList = ProductBeanList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mLettes = lettes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder(mInflater.inflate(R.layout.item_fg1, parent, false), false);
            case 1:
                return new ViewHolder(mInflater.inflate(R.layout.item_fg1, parent, false), true);
        }
        return null;
    }

    public void refreshData(List<ProductBean> ProductBeanList,
                            Map<Integer, Integer> lettes) {
        this.mProductBeanList=ProductBeanList;
        this.mLettes=lettes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mProductBeanList.get(position) == null || mProductBeanList.size() == 0||mLettes==null||mLettes.size()==0){
            Log.i(TAG,"mProductBeanList or mLettes is invalid");
            return;
        }

        holder.proName_tv.setText(mProductBeanList.get(position).getProName());
        holder.proDes_tv.setText(mProductBeanList.get(position).getProDes());
        holder.upDownNum_tv.setText(mProductBeanList.get(position).getUpDownNum());
        holder.proDes1_tv.setText(mProductBeanList.get(position).getProDes1());
        holder.circle_tv.setText(mProductBeanList.get(position).getCircle());
        if (mProductBeanList.get(position).getHeadertype() == TYPE_HEADER1) {
            //持有黄金 保值增值
            holder.header_tv.setText(mContext.getResources().getString(R.string.text2));
            holder.header_iv.setImageResource(R.drawable.orange_ring);
            holder.upDownNum_tv.setTextColor(mContext.getResources().getColor(R.color.orange_fg_item));


        } else {
            //稳定生产 省时省力
            holder.header_tv.setText(mContext.getResources().getString(R.string.text3));
            holder.header_iv.setImageResource(R.drawable.blue_ring);
            holder.upDownNum_tv.setTextColor(mContext.getResources().getColor(R.color.blue_fg1_item));
        }

    }

    @Override
    public int getItemCount() {

        return (mProductBeanList == null || mProductBeanList.isEmpty()) ? 0 : mProductBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //根据每个字母下第一个联系人在数据中的位置，来显示headView
        ProductBean ProductBean = mProductBeanList.get(position);
        if (mLettes.get(ProductBean.getHeadertype()) == position) {
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout headLl;
        private TextView header_tv;
        private ImageView header_iv;
        private TextView proName_tv; //eg:流动金
        private TextView proDes_tv;  //eg:1元起购，随时买卖
        private TextView upDownNum_tv;  //eg:1.0%
        private TextView proDes1_tv;  //eg:目标年化回报率
        private TextView circle_tv;  //eg:1~10个月


        public ViewHolder(View itemView, boolean show) {
            super(itemView);
            headLl = (LinearLayout) itemView.findViewById(R.id.header_ll);
            header_tv = (TextView) itemView.findViewById(R.id.header_tv);
            header_iv = (ImageView) itemView.findViewById(R.id.header_iv);
            proName_tv = (TextView) itemView.findViewById(R.id.proname_tv);
            proDes_tv = (TextView) itemView.findViewById(R.id.proDes_tv);
            upDownNum_tv = (TextView) itemView.findViewById(R.id.updownnum_tv);
            proDes1_tv = (TextView) itemView.findViewById(R.id.proDes1_tv);
            circle_tv = (TextView) itemView.findViewById(R.id.circle_tv);
            if (!show) {
                headLl.setVisibility(View.GONE);
            } else {
                headLl.setVisibility(View.VISIBLE);
            }
        }
    }
}