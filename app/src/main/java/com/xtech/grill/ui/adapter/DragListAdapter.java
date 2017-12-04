package com.xtech.grill.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xtech.grill.R;
import com.xtech.grill.widget.DynamicArrayAdapter;
import com.xtech.grill.widget.DynamicListView;

import java.util.List;

/**
 * author : 武昌丶鱼
 * email  : reyzarc@163.com
 * time   : 2017-12-04
 * desc   :
 */
public class DragListAdapter extends DynamicArrayAdapter<String>{
    private static final int LAYOUT = R.layout.row_layout;
    private List<String> titles;
    private DynamicListView listView;
    private Context mContext;

    public DragListAdapter(Context context,List<String> objects) {
        super(context,LAYOUT,objects);
        this.titles = objects;
        mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        Resources r = getContext().getResources();
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            convertView = inflater.inflate(LAYOUT, parent, false);
            holder = new Holder();

            holder.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);
            holder.rlItem = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            holder.ivRight = (ImageView) convertView.findViewById(R.id.iv_right);

            convertView.setTag(holder);
        } else {
            holder = (Holder)convertView.getTag();
        }

        if(listView.getIsEnableDrag()){//编辑模式
            holder.ivDelete.setVisibility(View.VISIBLE);
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO 删除列表
                }
            });
        }else{
            holder.ivDelete.setVisibility(View.INVISIBLE);
        }

        holder.tvTitle.setText(getItem(position));

        holder.rlItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onRowDragViewTouch(position);
            }
        });
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getIsEnableDrag()){//编辑模式不可点击
                    return;
                }
                Toast.makeText(mContext, position+"",Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public void bindToListView(DynamicListView lv) {
        listView = lv;
        listView.setDynamicAdapter(this);
    }

    @Override
    protected void doItemSwap(int pos1, int pos2) {
//        String o1 = objects.get(pos1);
//        String o2 = objects.get(pos2);
//        objects.set(pos1, o2);
//        objects.set(pos2, o1);
    }

    private boolean onRowDragViewTouch(int position) {
        if (listView.canHoverRows()) {
            // The row clicked is now placed above the other rows, and follows the touch position
            listView.hoverRow(position);
            return true;
        }
        return false;
    }

    private static class Holder {
        TextView tvTitle;
        ImageView ivDelete;
        ImageView ivRight;
        RelativeLayout rlItem;
    }
}
