package com.questappx.anniversary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class FontAdapter extends RecyclerView.Adapter<FontAdapter.RecyclerHolder>{

    Context context;
    int[] list;
     int method;
    RecyclerListener listener;

    WindowManager windowManager;
    Display display;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    int screenWidth;


    //Method 1 == frames
    //Method 2 == Colors
    //Method 3 == Fonts
    //Method 4 == Filter
    //Method 5 == Stickers
    //Method 10 == Other Apps Pro Frames

    public FontAdapter(Context context, int[] list, int method, RecyclerListener listener) {
        this.context = context;
        this.list = list;
        this.method = method;
        this.listener = listener;

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display =  windowManager.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.griditem_frames, null, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, @SuppressLint("RecyclerView") int position) {
        if(method == 2)
        {
            holder.imageView.setBackgroundResource(list[position]);

        }
        //Fonts
        else if(method == 3)
        {
            holder.textFont.setText("Font");
            Typeface typeface = ResourcesCompat.getFont(context, list[position]);
            holder.textFont.setTypeface(typeface);
        }

        //Filters
        else if(method == 4)
        {
            if(EditorActivity.pickedImage != null)
            {
//                holder.imageView.setImageURI(EditorActivity.pickedImage);
                Glide.with(context).load(EditorActivity.pickedImage).into(holder.imageView);
            }
            else
            {
//                holder.imageView.setImageResource(R.drawable.model);
                Glide.with(context).load(R.drawable.model).into(holder.imageView);
            }



            if(!(holder.getAdapterPosition() == 0))
                holder.imageviewFilter.setImageResource(list[position]);
//                Glide.with(context).load(list[position]).into(holder.imageviewFilter);
        }

        //Stickers
        else if(method == 5)
        {
            //setting black background
            holder.cardGridItem.getLayoutParams().width = screenWidth/4;
            holder.cardGridItem.getLayoutParams().height = screenWidth/4;
            holder.cardGridItem.invalidate();
            holder.imageView.setBackgroundResource(R.color.lightgrey);
//            holder.imageviewSticker.setImageResource(list[position]);
            Glide.with(context).load(list[position]).into(holder.imageviewSticker);
        }


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.length;
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        ImageView imageView, imageviewSticker;
        TextView textFont;
        ImageView imageviewFilter;
        RelativeLayout proData;
        CardView cardGridItem;

        public RecyclerHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.imageview_frameitem);
            textFont = view.findViewById(R.id.textFont);
            imageviewFilter = view.findViewById(R.id.imageview_filteritem);
            imageviewSticker = view.findViewById(R.id.imageviewSticker);
            proData = view.findViewById(R.id.proDataLayout);
            cardGridItem = view.findViewById(R.id.cardGridItem);
        }
    }




}
