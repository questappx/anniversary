package com.questappx.anniversary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryBtnAdapter extends RecyclerView.Adapter<CategoryBtnAdapter.CategoryBtnHolder> {

    Context context;
    List<String> list;
    RecyclerListener listener;


    public CategoryBtnAdapter()
    {}

    public CategoryBtnAdapter(Context context, List<String> list, RecyclerListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryBtnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_btn_itemlayout,
                        parent,
                        false);

        return new CategoryBtnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryBtnHolder holder, int position) {
            holder.button.setText(list.get(holder.getAdapterPosition()));

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(holder.getAbsoluteAdapterPosition());
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CategoryBtnHolder extends RecyclerView.ViewHolder{

        Button button;

        public CategoryBtnHolder(@NonNull View itemView) {
            super(itemView);

            button = itemView.findViewById(R.id.categoryBtn);

        }
    }
}
