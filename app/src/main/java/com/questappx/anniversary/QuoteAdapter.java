package com.questappx.anniversary;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.questappx.anniversary.Extras.RateItDialogFragment;
import com.questappx.anniversary.Extras.SaveImage;

import java.util.List;
import java.util.Random;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteHolder>{

    Context context;
    List<String> list;
    QuoteListener listener;


    public QuoteAdapter(Context context, List<String> list, QuoteListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quote_item, null, false);
        return new QuoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteHolder holder, int position) {
        holder.quoteTv.setText(list.get(position));

//        holder.quoteBg.setImageResource(getRandBg());
        Glide.with(context).load(getRandBg()).into(holder.quoteBg);

        holder.copyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", holder.quoteTv.getText());
                manager.setPrimaryClip(clipData);
                Toast.makeText(context, "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveImage(context, holder.quoteLayout).saveAsJPGwORK();
                listener.onImageSaved();;
            }
        });

        holder.quoteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.quoteBg.setImageResource(getRandBg());
                Glide.with(context).load(getRandBg()).into(holder.quoteBg);
                listener.onImageSwitched();
            }
        });

        holder.swapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(getRandBg()).into(holder.quoteBg);
                listener.onImageSwitched();
            }
        });



    }

    private int getRandBg()
    {
        int drawableId = R.drawable.bg1;
        Random random = new Random();

        int x = 1+random.nextInt(39);

        if(x == 1)
        {
            drawableId = R.drawable.bg1;
        }
        else if(x == 2)
        {
            drawableId = R.drawable.bg2;
        }
        else if(x == 3)
        {
            drawableId = R.drawable.bg3;
        }
        else if(x == 4)
        {
            drawableId = R.drawable.bg28;
        }
        else if(x == 5)
        {
            drawableId = R.drawable.bg5;
        }
        else if(x == 6)
        {
            drawableId = R.drawable.bg29;
        }
        else if(x == 7)
        {
            drawableId = R.drawable.bg30;
        }
        else if(x == 8)
        {
            drawableId = R.drawable.bg8;
        }
        else if(x == 9)
        {
            drawableId = R.drawable.bg9;
        }
        else if(x == 10)
        {
            drawableId = R.drawable.bg10;
        }
        else if(x == 11)
        {
            drawableId = R.drawable.bg11;
        }
        else if(x == 12)
        {
            drawableId = R.drawable.bg12;
        }
        else if(x == 13)
        {
            drawableId = R.drawable.bg13;
        }
        else if(x == 14)
        {
            drawableId = R.drawable.bg14;
        }
        else if(x == 15)
        {
            drawableId = R.drawable.bg15;
        }
        else if(x == 16)
        {
            drawableId = R.drawable.bg16;
        }
        else if(x == 17)
        {
            drawableId = R.drawable.bg17;
        }
        else if(x == 18)
        {
            drawableId = R.drawable.bg18;
        }
        else if(x == 19)
        {
            drawableId = R.drawable.bg19;
        }
        else if(x == 20)
        {
            drawableId = R.drawable.bg20;
        }
        else if(x == 21)
        {
            drawableId = R.drawable.bg21;
        }
        else if(x == 22)
        {
            drawableId = R.drawable.bg22;
        }
        else if(x == 23)
        {
            drawableId = R.drawable.bg26;
        }
        else if(x == 24)
        {
            drawableId = R.drawable.bg24;
        }
        else if(x == 25)
        {
            drawableId = R.drawable.bg25;
        }
        else if(x == 26)
        {
            drawableId = R.drawable.bg26;
        }
        else if(x == 27)
        {
            drawableId = R.drawable.bg27;
        }
        else if(x == 28)
        {
            drawableId = R.drawable.bg28;
        }
        else if(x == 29)
        {
            drawableId = R.drawable.bg29;
        }
        else if(x == 30)
        {
            drawableId = R.drawable.bg30;
        }
        else if(x == 31)
        {
            drawableId = R.drawable.bg31;
        }
        else if(x == 32)
        {
            drawableId = R.drawable.bg32;
        }
        else if(x == 33)
        {
            drawableId = R.drawable.bg33;
        }
        else if(x == 34)
        {
            drawableId = R.drawable.bg34;
        }
        else if(x == 35)
        {
            drawableId = R.drawable.bg35;
        }
        else if(x == 36)
        {
            drawableId = R.drawable.bg36;
        }
        else if(x == 37)
        {
            drawableId = R.drawable.bg37;
        }
        else if(x == 38)
        {
            drawableId = R.drawable.bg38;
        }
        else if(x == 39)
        {
            drawableId = R.drawable.bg39;
        }




        return drawableId;
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class QuoteHolder extends RecyclerView.ViewHolder {

        TextView quoteTv;
        ImageView copyText, saveImage, quoteBg, swapImage;
        RelativeLayout quoteLayout;

        public QuoteHolder(View view) {
            super(view);

            quoteTv = view.findViewById(R.id.quoteTv);
            copyText = view.findViewById(R.id.copyText);
            saveImage = view.findViewById(R.id.saveImage);
            quoteLayout = view.findViewById(R.id.quoteLayout);
            quoteBg = view.findViewById(R.id.quoteBg);
            swapImage = view.findViewById(R.id.swapImage);
        }
    }
}
