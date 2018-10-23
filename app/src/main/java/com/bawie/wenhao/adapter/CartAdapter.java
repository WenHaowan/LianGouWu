package com.bawie.wenhao.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawie.wenhao.R;
import com.bawie.wenhao.bean.ShowBean;

import java.util.List;

/**
 * Created by HP on 2018/10/23.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private Context context;
    private List<ShowBean.DataBean> list;

    public CartAdapter(Context context, List<ShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.cart_adapter_layout,parent,false);
        CartViewHolder viewHolder = new CartViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartViewHolder holder, int position) {

        holder.name_shang.setText(list.get(position).getSellerName());

        holder.rect_view.setLayoutManager(new LinearLayoutManager(context));
        final ShowAdapter showAdapter = new ShowAdapter(context,list.get(position).getList());
        holder.rect_view.setAdapter(showAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView name_shang;
        private RecyclerView rect_view;

        public CartViewHolder(View itemView) {
            super(itemView);

            name_shang = (TextView) itemView.findViewById(R.id.name_shang);
            rect_view = (RecyclerView) itemView.findViewById(R.id.rect_view);
        }
    }
}
