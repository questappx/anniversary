package com.questappx.anniversary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>{

    Context context;
    String[] list;
     int method;
    RecyclerListener listener;
    ProDataListener proDataListener;
    boolean isPortraitSizeLayout;

    //Method 1 == frames
    //Method 2 == Colors
    //Method 3 == Fonts
    //Method 4 == Filter
    //Method 5 == Stickers
    //Method 10 == Other Apps Pro Frames

    public RecyclerAdapter(Context context, String[] list, int method, RecyclerListener listener) {
        this.context = context;
        this.list = list;
        this.method = method;
        this.listener = listener;
        isPortraitSizeLayout = false;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.griditem_frames, null, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, @SuppressLint("RecyclerView") int position) {

        //Frame
        if(method == 1)
        {

            if(isPortraitSizeLayout)
            {
                //to make adapter list card's height portrait
                holder.cardGridItem.getLayoutParams().height = (int) ((double)(holder.cardGridItem.getLayoutParams().width) * (1.4d));
                holder.cardGridItem.invalidate();
            }
//            holder.imageView.setImageResource(list[position]);
            Glide.with(context).load(list[position]).into(holder.imageView);

            //to manage pro frames
            if(isProContent(holder.getBindingAdapterPosition()))
            {
                if(holder.proData.getVisibility() == View.GONE)
                {
                    holder.proData.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                if(holder.proData.getVisibility() == View.VISIBLE)
                {
                    holder.proData.setVisibility(View.GONE);
                }
            }


            //set this as visible as proData
//            if(proDataListener.setVisible(list[position]))
//            {
//                holder.proData.setVisibility(View.VISIBLE);
//
                holder.proData.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        proDataListener.onClick(position);
                    }
                });
//            }
        }
        //Colors
        else if(method == 2)
        {
            holder.imageView.setBackgroundResource(Integer.parseInt(list[position]));
//            Glide.with(context).load(list[position]).listener(new RequestListener<Drawable>() {
//                @Override
//                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                    return false;
//                }
//
//                @Override
//                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                    holder.imageView.setBackground(resource);
//                    return false;
//                }
//            });

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
                holder.imageviewFilter.setImageResource(Integer.parseInt(list[position]));
//                Glide.with(context).load(list[position]).into(holder.imageviewFilter);
        }

        //Stickers
        else if(method == 5)
        {
            //setting black background
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

    int proFrames[] = { 6, 12, 18, 22, 27, 33, 43, 38, 41, 46, 54, 49, 59, 61};
    private boolean isProContent(int position) {
        if(list[position].contains("4") || list[position].contains("3"))
        {
            return true;
        }

//        for(int i=0;i<proFrames.length;i++)
//        {
//            if(list[position].contains(String.valueOf(proFrames[i])))
//            {
//                return true;
//            }
//        }
        return false;
    }

    public void setProDataListener(ProDataListener listener)
    {
        this.proDataListener = listener;
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

    public void setPortraitSizeLayout(boolean bool)
    {
        isPortraitSizeLayout = bool;
    }



}
