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
import com.shtoone.gdbhz.bean.TodaySystemQueryData;
import com.shtoone.gdbhz.ui.SlantedTextView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class TodaySystemQueryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = TodaySystemQueryAdapter.class.getSimpleName();
    private Context             context;
    private OnItemClickListener mOnItemClickListener;
    private Resources           mResources;
    private List<TodaySystemQueryData.DataEntity> itemsData;

    public enum ITEM_TYPE {
        TYPE_ITEM, TYPE_FOOTER
    }

    public TodaySystemQueryAdapter(Context context, List<TodaySystemQueryData.DataEntity> itemsData) {
        this.context = context;
        this.itemsData = itemsData;
        mResources = context.getResources();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TodaySystemQueryAdapter.ITEM_TYPE.TYPE_ITEM.ordinal()) {
            return new TodaySystemQueryAdapter.ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_todaysystem_fragment, parent, false));
        } else if (viewType == TodaySystemQueryAdapter.ITEM_TYPE.TYPE_FOOTER.ordinal()) {
            return new TodaySystemQueryAdapter.FootViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item_foot, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof TodaySystemQueryAdapter.ItemViewHolder) {
            TodaySystemQueryAdapter.ItemViewHolder mItemViewHolder = (TodaySystemQueryAdapter.ItemViewHolder) holder;
            mItemViewHolder.cv.setCardBackgroundColor(position % 2 == 0 ? mResources.getColor(R.color.material_teal_50) : mResources.getColor(R.color.material_blue_50));

            TodaySystemQueryData.DataEntity item = itemsData.get(position);
            Log.e(TAG, "item=:"+ item.toString() );
            mItemViewHolder.tv0_item_recyclerview_lab.setText(item.getGlcname());
            mItemViewHolder.tv_item_projectname.setText(item.getGcmc());
            mItemViewHolder.tv_postion.setText(item.getSgbw());
            mItemViewHolder.tv_sjqd.setText(item.getSjqd());
            mItemViewHolder.tv_maintaintime.setText(item.getStartTime());
            mItemViewHolder.tv_lingqi.setText(item.getLq()+"");





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

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() > 4 && position + 1 == getItemCount()) {
            return TodaySystemQueryAdapter.ITEM_TYPE.TYPE_FOOTER.ordinal();
        } else {
            return TodaySystemQueryAdapter.ITEM_TYPE.TYPE_ITEM.ordinal();
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView        cv;
        TextView        tv0_item_recyclerview_lab;
        TextView        tv_item_projectname;
        TextView        tv_postion;
        TextView        tv_maintaintime;
        TextView        tv_sjqd;
        TextView        tv_lingqi;
        SlantedTextView stv_todaysys;

        public ItemViewHolder(View view) {
            super(view);
            cv = (CardView) view.findViewById(R.id.cv_item_recyclerview_todaysystemquery);
            tv0_item_recyclerview_lab = (TextView) view.findViewById(R.id.tv0_item_recyclerview_lab);
            tv_item_projectname = (TextView) view.findViewById(R.id.tv_item_projectname);
            tv_postion = (TextView) view.findViewById(R.id.tv_postion);
            tv_maintaintime = (TextView) view.findViewById(R.id.tv_maintaintime);
            tv_sjqd = (TextView) view.findViewById(R.id.tv_sjqd);
            tv_lingqi = (TextView) view.findViewById(R.id.tv_lingqi);
            stv_todaysys = (SlantedTextView) view.findViewById(R.id.stv_todaysys);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
