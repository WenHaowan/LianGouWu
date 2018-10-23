package com.bawie.wenhao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawie.wenhao.R;
import com.bawie.wenhao.bean.ShowBean;
import com.bawie.wenhao.widget.MyJiaJianView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by HP on 2018/10/23.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder>{

    private Context context;
    private List<ShowBean.DataBean.ListBean> list;

    public ShowAdapter(Context context, List<ShowBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ShowAdapter.ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_adapter_layout,parent,false);
        ShowViewHolder viewHolder = new ShowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowAdapter.ShowViewHolder holder, int position) {

        holder.price.setText("优惠价：¥" + list.get(position).getBargainPrice());
        holder.title.setText(list.get(position).getTitle());
        String[] imgs = list.get(position).getImages().split("\\|");
        Glide.with(context).load(imgs[0]).into(holder.icon);

        holder.jiajianqi.setNumEt(list.get(position).getTotalNum());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView price;
        private TextView title;
        private MyJiaJianView jiajianqi;

        public ShowViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            icon = (ImageView) itemView.findViewById(R.id.product_icon);
            jiajianqi = (MyJiaJianView) itemView.findViewById(R.id.jiajianqi);

        }
    }
}
