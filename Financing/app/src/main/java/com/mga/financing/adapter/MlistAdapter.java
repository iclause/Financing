package com.mga.financing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mga.financing.R;
import com.mga.financing.bean.ProductBean;

import java.util.List;
import java.util.Map;

/**
 * Created by mga on 2018/5/28 14:30.
 */

public class MlistAdapter extends BaseAdapter {
    private List<ProductBean> mProductBeanList;
    private Context mContext;
    private LayoutInflater mInflater;
    private Map<Integer, Integer> mLettes;

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_CONTENT = 0;

    private static final int TYPE_HEADER1 = 0; //持有黄金 保值增值
    private static final int TYPE_HEADER2 = 1; //稳定生产 省时省力
    private String TAG = getClass().getSimpleName();

    public MlistAdapter(Context context, List<ProductBean> ProductBeanList,
                        Map<Integer, Integer> lettes) {
        mProductBeanList = ProductBeanList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mLettes = lettes;
    }

    @Override
    public int getCount() {
        return (mProductBeanList == null || mProductBeanList.isEmpty()) ? 0 : mProductBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return (mProductBeanList == null || mProductBeanList.isEmpty()) ? null : mProductBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mProductBeanList.get(position) == null || mProductBeanList.size() == 0 || mLettes == null || mLettes.size() == 0) {
            Log.i(TAG, "mProductBeanList or mLettes is invalid");
            return null;
        }
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fg1,parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.proName_tv.setText(mProductBeanList.get(position).getProName());
        holder.proDes_tv.setText(mProductBeanList.get(position).getProDes());
        holder.upDownNum_tv.setText(mProductBeanList.get(position).getUpDownNum());
        holder.proDes1_tv.setText(mProductBeanList.get(position).getProDes1());
        holder.circle_tv.setText(mProductBeanList.get(position).getCircle());
        if (getItemViewType(position) == TYPE_CONTENT) {
            //内容
            holder.setHeaderVisible(false);

        } else {
//            标题
            holder.setHeaderVisible(true);
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


        return convertView;
    }

    public void refreshData(List<ProductBean> ProductBeanList,
                            Map<Integer, Integer> lettes) {
        this.mProductBeanList = ProductBeanList;
        this.mLettes = lettes;
        notifyDataSetChanged();
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


        public ViewHolder(View itemView) {
            super(itemView);
            headLl = (LinearLayout) itemView.findViewById(R.id.header_ll);
            header_tv = (TextView) itemView.findViewById(R.id.header_tv);
            header_iv = (ImageView) itemView.findViewById(R.id.header_iv);
            proName_tv = (TextView) itemView.findViewById(R.id.proname_tv);
            proDes_tv = (TextView) itemView.findViewById(R.id.proDes_tv);
            upDownNum_tv = (TextView) itemView.findViewById(R.id.updownnum_tv);
            proDes1_tv = (TextView) itemView.findViewById(R.id.proDes1_tv);
            circle_tv = (TextView) itemView.findViewById(R.id.circle_tv);
//            if (!show) {
//                headLl.setVisibility(View.GONE);
//            } else {
//                headLl.setVisibility(View.VISIBLE);
//            }
        }

        public void setHeaderVisible(boolean show) {
            if (!show) {
                headLl.setVisibility(View.GONE);
            } else {
                headLl.setVisibility(View.VISIBLE);
            }
        }
    }
}
