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
import com.mga.financing.bean.response.ProductRes;

import java.util.List;
import java.util.Map;

/**
 * Created by mga on 2018/5/28 14:30.
 */

public class MlistAdapter extends BaseAdapter {
    private List<ProductRes> mProductResList;
    private Context mContext;
    private LayoutInflater mInflater;
    private Map<Integer, Integer> mLettes;

    private static boolean ISSHOWHEADER = false;
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_CONTENT = 0;

    private static final int TYPE_HEADER1 = 0; //持有黄金 保值增值
    private static final int TYPE_HEADER2 = 1; //稳定生产 省时省力
    private String TAG = getClass().getSimpleName();

    /**
     *
     * @param context
     * @param productResList
     * @param lettes 为null，标识不显示header；有值，显示header
     */
    public MlistAdapter(Context context, List<ProductRes> productResList,
                        Map<Integer, Integer> lettes) {
        mProductResList = productResList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        if(lettes==null){
            ISSHOWHEADER=false;
        }else {
            this.mLettes = lettes;
            ISSHOWHEADER=true;
        }
    }

    @Override
    public int getCount() {
        return (mProductResList == null || mProductResList.isEmpty()) ? 0 : mProductResList.size();
    }

    @Override
    public Object getItem(int position) {
        return (mProductResList == null || mProductResList.isEmpty()) ? null : mProductResList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        //根据每个字母下第一个联系人在数据中的位置，来显示headView
        if(!ISSHOWHEADER){
            return TYPE_CONTENT;
        }
        if (mLettes.get(Integer.parseInt(mProductResList.get(position).getProductslogan())) == position) {
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mProductResList.get(position) == null || mProductResList.size() == 0 ) {
            Log.i(TAG, "mProductBeanList or mLettes is invalid");
            return null;
        }
        Log.i(TAG, "position "+position+"\n item"+mProductResList.get(position).toString());
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_fg1,parent, false);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.proName_tv.setText(mProductResList.get(position).getProductname());
        holder.proDes_tv.setText(mProductResList.get(position).getProductdesc());
        holder.upDownNum_tv.setText(mProductResList.get(position).getAnnualizedrate());
        holder.proDes1_tv.setText(mProductResList.get(position).getAnnualizedratedes());
        holder.circle_tv.setText(mProductResList.get(position).getCycle());
        if (getItemViewType(position) == TYPE_CONTENT) {
            //内容
            holder.setHeaderVisible(false);

        } else {
//            标题
            holder.setHeaderVisible(true);
            if (Integer.parseInt(mProductResList.get(position).getProductslogan()) == TYPE_HEADER1) {
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

    /**
     *
     * @param productResList
     * @param lettes  为null，标识不显示header；有值，显示header
     */
    public void refreshData(List<ProductRes> productResList,
                            Map<Integer, Integer> lettes) {
        if(productResList!=null) Log.i(TAG,"productResList :\n"+productResList.toString());
        if(lettes!=null) Log.i(TAG,"lettes :\n"+lettes.toString());
        this.mProductResList = productResList;
        if(lettes==null){
            ISSHOWHEADER=false;
        }else {
            this.mLettes = lettes;
            ISSHOWHEADER=true;
        }
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
