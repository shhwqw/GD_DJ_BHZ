package com.shtoone.gdbhz.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.bean.ProduceQueryDetailActivityData;
import com.shtoone.gdbhz.bean.QRCodeRecordData;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public class QRDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  Context   context;
    private Resources mResources;
    private List<QRCodeRecordData> mDataList;

    public QRDataAdapter(Context context, List<QRCodeRecordData> dataList) {
        this.context = context;
        mDataList = dataList;
        mResources = context.getResources();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_qrcode_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder mItemViewHolder = (ItemViewHolder) holder;
            QRCodeRecordData qrCodeRecordData = mDataList.get(position);
            ViewGroup mViewGroup = (ViewGroup) mItemViewHolder.tv_time.getParent();
            mViewGroup.setBackgroundColor(position % 2 == 0 ? mResources.getColor(R.color.material_teal_50) : mResources.getColor(R.color.material_blue_50));
            mItemViewHolder.tv_time.setText(qrCodeRecordData.getStartTime());
            mItemViewHolder.tv_name.setText(qrCodeRecordData.getUserName());

        }
    }

    @Override
    public int getItemCount() {
        if (mDataList != null && mDataList.size() > 0) {
            return mDataList.size();
        }
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_name;


        public ItemViewHolder(View view) {
            super(view);
            tv_time = (TextView) view.findViewById(R.id.tv_recode_time);
            tv_name = (TextView) view.findViewById(R.id.tv_recode_name);

        }
    }
}
