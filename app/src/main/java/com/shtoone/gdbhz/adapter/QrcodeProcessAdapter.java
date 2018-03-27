package com.shtoone.gdbhz.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.bean.QrcodeProcessData;
import com.shtoone.gdbhz.bean.TodaySystemQueryData;
import com.shtoone.gdbhz.ui.SlantedTextView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class QrcodeProcessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = QrcodeProcessAdapter.class.getSimpleName();
    private Context             context;
    private OnItemClickListener mOnItemClickListener;
    private Resources           mResources;
    private List<QrcodeProcessData.DataEntity> itemsData;

    public QrcodeProcessAdapter(Context context, List<QrcodeProcessData.DataEntity> itemsData) {
        this.context = context;
        this.itemsData = itemsData;
        mResources = context.getResources();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public enum ITEM_TYPE {
        TYPE_ITEM, TYPE_FOOTER
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == QrcodeProcessAdapter.ITEM_TYPE.TYPE_ITEM.ordinal()) {
            return new QrcodeProcessAdapter.ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_qrcodeprocess_fragment, parent, false));
        } else if (viewType == QrcodeProcessAdapter.ITEM_TYPE.TYPE_FOOTER.ordinal()) {
            return new QrcodeProcessAdapter.FootViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_foot, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QrcodeProcessAdapter.ItemViewHolder) {
            QrcodeProcessAdapter.ItemViewHolder mItemViewHolder = (QrcodeProcessAdapter.ItemViewHolder) holder;
            mItemViewHolder.cv.setCardBackgroundColor(position % 2 == 0 ? mResources.getColor(R.color.material_teal_50) : mResources.getColor(R.color.material_blue_50));

            QrcodeProcessData.DataEntity dataEntity = itemsData.get(position);
            Log.e(TAG, "item=:"+ dataEntity.toString() );
            mItemViewHolder.tv0_item_operator.setText(dataEntity.getOperator());
            mItemViewHolder.tv_item_recordTime.setText(dataEntity.getRecordTime());
            mItemViewHolder.tv_timestampID.setText(dataEntity.getTimestampID());






            if (mOnItemClickListener != null) {
                mItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        if (itemsData != null && itemsData.size() > 0) {
            //这里的10是根据分页查询，一页该显示的条数
            if (itemsData.size() > 4) {
                return itemsData.size() + 1;
            } else {
                return itemsData.size();
            }
        }
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView        cv;
        TextView        tv0_item_operator;
        TextView        tv_item_recordTime;
        TextView        tv_timestampID;


        public ItemViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.cv_item_recyclerview_qrcodeprocess);
            tv0_item_operator = (TextView) view.findViewById(R.id.tv0_item_operator);
            tv_item_recordTime = (TextView) view.findViewById(R.id.tv_item_recordTime);
            tv_timestampID = (TextView) view.findViewById(R.id.tv_timestampID);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 4 && position + 1 == getItemCount()) {
            return TodaySystemQueryAdapter.ITEM_TYPE.TYPE_FOOTER.ordinal();
        } else {
            return TodaySystemQueryAdapter.ITEM_TYPE.TYPE_ITEM.ordinal();
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }


}
