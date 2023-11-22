package com.questappx.anniversary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryBtnAdapter extends RecyclerView.Adapter<CategoryBtnAdapter.CategoryBtnHolder> {

    Context context;
    List<String> list;
    RecyclerListener listener;

    int recentClickedPos = 0;


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
    public void onBindViewHolder(@NonNull CategoryBtnHolder holder, @SuppressLint("RecyclerView") int position) {

        if(position == recentClickedPos)
        {
            holder.button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.darkgreen)));
//            holder.button.invalidate();
        }
        else
        {
            holder.button.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.mainAppColor)));
//            holder.button.invalidate();
        }
            holder.button.setText(list.get(holder.getAdapterPosition()));

            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(holder.getAbsoluteAdapterPosition());
                    recentClickedPos = position;
                    notifyDataSetChanged();
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
