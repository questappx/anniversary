package com.questappx.anniversary;


import static com.android.volley.VolleyLog.TAG;
import static com.questappx.anniversary.MainActivity.appOpenManager;
import static com.questappx.anniversary.MainActivity.inApp;
import static com.questappx.anniversary.MainActivity.interstitialAd;
import static com.questappx.anniversary.MainActivity.interstitialAdImplement;
import static com.questappx.anniversary.MainActivity.loadRewardedAdmobAd;
import static com.questappx.anniversary.MainActivity.mRewardedAd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.impl.model.WorkProgressDao_Impl;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.questappx.anniversary.AdsWorking.BannerAdImplement;
import com.questappx.anniversary.AdsWorking.InterstitialAdImplement;
import com.questappx.anniversary.Extras.AppOpenManager;
import com.questappx.anniversary.Extras.RateItDialogFragment;
import com.questappx.anniversary.Extras.SaveImage;
import com.xiaopo.flying.logoSticker.Sticker;
import com.xiaopo.flying.logoSticker.StickerView;
import com.xiaopo.flying.logoSticker.TextSticker;
import com.xiaopo.flying.sticker.DrawableSticker;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import yuku.ambilwarna.AmbilWarnaDialog;


public class EditorActivity extends AppCompatActivity {

    //Admob Rewarded


    private static int LOAD_IMAGE_RESULTS = 1;
    public String TAG = "adderr";

    ArrayList<ArrayList<String>> bgsList = new ArrayList<>();

    CategoryBtnAdapter categoryBtnAdapter;

    ArrayList<String> categorylist = new ArrayList<>();
    RelativeLayout linearlayoutList1;
    int activityOpenAd;
    int fbInterstitialReward;
    int orientationMode;
    String drawableFrameBg;

    RecyclerAdapter adapter_frames;

    public String[] squareWedDrawable = {
//            R.drawable.square11,R.drawable.square2,R.drawable.square3,R.drawable.square4,R.drawable.square9,R.drawable.square6,R.drawable.square13,R.drawable.square8,R.drawable.square5,R.drawable.square10,R.drawable.square1,R.drawable.square12,R.drawable.square7,R.drawable.square14
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square1.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square2.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square3.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square4.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square5.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square6.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square7.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square8.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square9.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square10.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square11.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square12.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square13.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square14.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square15.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square16.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square17.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square18.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square19.webp",
            "https://questappx.website/apps/anniversary/frames/wed/sqr/square20.webp"
    };
    public String[] portraitAnniDrawable = {
//            R.drawable.anni_portrait1,R.drawable.anni_portrait2,R.drawable.anni_portrait3,R.drawable.anni_portrait4,R.drawable.anni_portrait5,R.drawable.anni_portrait6,R.drawable.anni_portrait7,R.drawable.anni_portrait8,R.drawable.anni_portrait9,R.drawable.anni_portrait10,R.drawable.anni_portrait11,R.drawable.anni_portrait12,R.drawable.anni_portrait13,R.drawable.anni_portrait14,R.drawable.anni_portrait15,R.drawable.anni_portrait16,R.drawable.anni_portrait17,R.drawable.anni_portrait18,R.drawable.anni_portrait19,R.drawable.anni_portrait20,R.drawable.anni_portrait21,R.drawable.anni_portrait22,R.drawable.anni_portrait23,R.drawable.anni_portrait24,R.drawable.anni_portrait25,R.drawable.anni_portrait26,R.drawable.anni_portrait27,R.drawable.anni_portrait28,R.drawable.anni_portrait29,R.drawable.anni_portrait30,R.drawable.anni_portrait31,R.drawable.anni_portrait32,R.drawable.anni_portrait33,
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait1.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait2.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait3.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait4.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait5.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait6.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait7.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait8.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait9.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait10.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait11.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait12.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait13.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait14.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait15.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait16.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait17.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait18.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait19.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait20.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait21.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait22.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait23.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait24.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait25.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait26.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait27.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait28.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait29.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait30.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait31.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait32.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_portrait33.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt1.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt2.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt3.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt4.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt5.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt6.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt7.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt8.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt9.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt10.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt11.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt12.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt13.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt14.png",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt15.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt16.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt17.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt18.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt19.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt20.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt21.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt22.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt23.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt24.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt25.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt26.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt27.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt28.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt29.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt30.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt31.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt32.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt33.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt34.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt35.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt36.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt37.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt38.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt39.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt40.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt41.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt42.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt43.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt44.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt45.webp",
            "https://questappx.website/apps/anniversary/frames/anni/prt/anni_prt46.webp"
    };
    public String[] squareAnniDrawable = {
//            R.drawable.anni_square1,R.drawable.anni_square2,R.drawable.anni_square3,R.drawable.anni_square4,R.drawable.anni_square6,R.drawable.anni_square7,R.drawable.anni_square8,R.drawable.anni_square5,R.drawable.anni_square9,R.drawable.anni_square10,R.drawable.anni_square11,R.drawable.anni_square12,R.drawable.anni_square13,R.drawable.anni_square14,R.drawable.anni_square15,R.drawable.anni_square16,R.drawable.anni_square17,R.drawable.anni_square18,R.drawable.anni_square19,R.drawable.anni_square20,R.drawable.anni_square21,R.drawable.anni_square22,R.drawable.anni_square23,R.drawable.anni_square24,R.drawable.anni_square25,R.drawable.anni_square26,R.drawable.anni_square27,R.drawable.anni_square28,R.drawable.anni_square29,R.drawable.anni_square30,R.drawable.anni_square31,R.drawable.anni_square32,R.drawable.anni_square33,R.drawable.anni_square34
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square1.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square2.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square3.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square4.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square5.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square6.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square7.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square8.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square9.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square10.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square11.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square12.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square13.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square14.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square15.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square16.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square17.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square18.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square19.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square20.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square21.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square22.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square23.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square24.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square25.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square26.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square27.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square28.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square29.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square30.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square31.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square32.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square33.webp",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_square34.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square1.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square2.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square3.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square4.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square5.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square6.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square7.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square8.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square9.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square10.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square11.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square12.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square13.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square14.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square15.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square16.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square17.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square18.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square19.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square20.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square21.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square22.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square23.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square24.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square25.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square26.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square27.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square28.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square29.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square30.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square31.webp",
            "https://questappx.website/apps/anniversary/frames/anni/sqr/anni_square32.webp"
    };
    public String[] portraitWedDrawable = {
//            R.drawable.new_wed_portrait1,R.drawable.new_wed_portrait2,R.drawable.new_wed_portrait3,R.drawable.new_wed_portrait4,R.drawable.new_wed_portrait5,R.drawable.new_wed_portrait6,R.drawable.new_wed_portrait7,R.drawable.new_wed_portrait8,R.drawable.new_wed_portrait9,R.drawable.new_wed_portrait10,R.drawable.new_wed_portrait11,R.drawable.new_wed_portrait12,R.drawable.new_wed_portrait13,R.drawable.new_wed_portrait14,R.drawable.new_wed_portrait15
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait1.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait2.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait3.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait4.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait5.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait6.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait7.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait8.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait9.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait10.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait11.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait12.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait13.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait14.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait15.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait16.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait17.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait18.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait19.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait20.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait21.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait22.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait23.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait24.webp",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_portrait25.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait1.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait2.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait3.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait4.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait5.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait6.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait7.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait8.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait9.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait10.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait11.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait12.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait13.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait14.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait15.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait16.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait17.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait18.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait19.webp",
            "https://questappx.website/apps/anniversary/frames/wed/prt/wed_portrait20.webp"
    };
    public String[] anniInviteDrawable = {
//            R.drawable.anni_invitation1,R.drawable.anni_invitation2,R.drawable.anni_invitation3,R.drawable.anni_invitation4,R.drawable.anni_invitation5,R.drawable.anni_invitation6,R.drawable.anni_invitation12, R.drawable.anni_invitation7,R.drawable.anni_invitation8,R.drawable.anni_invitation9,R.drawable.anni_invitation10,R.drawable.anni_invitation11 ,R.drawable.anni_invitation13,R.drawable.anni_invitation14,R.drawable.anni_invitation15,R.drawable.anni_invitation16,R.drawable.anni_invitation17,R.drawable.anni_invitation18,R.drawable.anni_invitation19,R.drawable.anni_invitation20,R.drawable.anni_invitation21,R.drawable.anni_invitation22,R.drawable.anni_invitation23,R.drawable.anni_invitation24,R.drawable.anni_invitation25,R.drawable.anni_invitation26,R.drawable.anni_invitation27,R.drawable.anni_invitation28,R.drawable.anni_invitation29,R.drawable.anni_invitation30,R.drawable.anni_invitation31,R.drawable.anni_invitation32,R.drawable.anni_invitation33,R.drawable.anni_invitation34,R.drawable.anni_invitation36,R.drawable.anni_invitation37,R.drawable.anni_invitation38,R.drawable.anni_invitation39,R.drawable.anni_invitation40,R.drawable.anni_invitation41,R.drawable.anni_invitation42,R.drawable.anni_invitation43,R.drawable.anni_invitation44,R.drawable.anni_invitation45,R.drawable.anni_invitation46,R.drawable.anni_invitation47,R.drawable.anni_invitation48,R.drawable.anni_invitation49,R.drawable.anni_invitation35,R.drawable.anni_invitation50
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation1.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation2.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation3.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation4.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation5.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation6.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation7.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation8.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation9.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation10.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation11.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation12.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation13.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation14.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation15.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation16.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation17.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation18.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation19.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation20.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation21.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation22.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation23.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation24.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation25.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation26.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation27.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation28.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation29.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation30.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation31.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation32.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation33.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation34.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation35.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation36.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation37.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation38.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation39.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation40.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation41.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation42.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation43.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation44.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation45.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation46.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation47.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation48.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation49.png",
//            "https://aster12.000webhostapp.com/Frames/anniversary/anni_invitation50.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation1.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation2.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation3.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation4.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation5.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation6.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation7.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation8.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation9.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation10.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation11.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation12.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation13.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation14.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation15.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation16.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation17.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation18.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation19.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation20.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation21.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation22.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation23.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation24.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation25.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation26.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation27.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation28.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation29.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation30.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation31.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation32.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation33.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation34.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation35.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation36.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation37.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation38.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation39.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation40.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation41.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation42.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation43.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation44.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation45.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation46.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation47.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation48.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation49.png",
            "https://questappx.website/apps/anniversary/frames/anni/invite/anni_invitation50.png"
    };

    public String[] wedInvitationDrawable = {
//            R.drawable.wed_invitation38,R.drawable.wed_invitation39,R.drawable.wed_invitation40,R.drawable.wed_invitation19,R.drawable.wed_invitation15,R.drawable.wed_invitation43,R.drawable.wed_invitation2,R.drawable.wed_invitation21,R.drawable.wed_invitation9,R.drawable.wed_invitation47,R.drawable.wed_invitation48,R.drawable.wed_invitation49,R.drawable.wed_invitation50
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation1.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation2.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation3.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation4.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation5.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation6.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation7.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation8.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation9.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation10.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation11.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation12.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation13.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation14.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation15.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation16.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation17.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation18.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation19.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation20.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation21.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation22.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation23.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation24.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation25.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation26.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation27.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation28.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation29.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation30.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation31.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation32.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation33.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation34.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation35.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation36.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation37.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation38.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation39.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation40.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation41.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation42.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation43.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation44.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation45.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation46.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation47.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation48.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation49.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation50.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation51.png",
//            "https://aster12.000webhostapp.com/Frames/wedding/wed_invitation52.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation1.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation2.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation3.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation4.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation5.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation6.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation7.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation8.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation9.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation10.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation11.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation12.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation13.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation14.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation15.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation16.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation17.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation18.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation19.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation20.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation21.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation22.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation23.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation24.png",
            "https://questappx.website/apps/anniversary/frames/wed/invite/wed_invitation25.png"
    };

    public String[] hny_sqr = {
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr1.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr2.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr3.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr4.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr5.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr6.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr7.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr8.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr9.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr10.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr11.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr12.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr13.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr14.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr15.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr16.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr17.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr18.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr19.png",
            "https://questappx.website/apps/anniversary/frames/hny/sqr/hny_sqr20.png"
    };

    public String[] hny_prt = {
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt1.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt2.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt3.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt4.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt5.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt2.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt6.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt7.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt8.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt9.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt10.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt11.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt12.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt13.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt14.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt15.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt16.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt17.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt18.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt19.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt20.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt21.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt22.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt23.png",
            "https://questappx.website/apps/anniversary/frames/hny/prt/hny_prt24.png",
    };

    public String[] jmrd_sqr = {
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr1.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr2.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr3.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr4.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr5.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr6.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr7.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr8.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr9.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr10.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr11.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr12.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr13.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr14.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr15.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr16.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr17.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr18.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr19.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/sqr/jmr_sqr20.png",
    };


    public String[] jmrd_prt = {
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt1.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt3.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt4.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt5.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt6.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt7.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt8.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt9.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt10.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt11.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt12.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt13.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt14.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt15.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt16.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt17.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt18.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt19.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt20.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt21.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt22.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt23.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt24.png",
            "https://questappx.website/apps/anniversary/frames/jmrd/prt/jmr_prt25.png",
    };

    public String[] eng_sqr = {
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr1.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr2.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr3.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr4.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr5.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr6.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr7.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr8.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr9.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr10.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr11.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr12.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr13.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr14.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr15.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr16.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr17.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr18.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr19.png",
            "https://questappx.website/apps/anniversary/frames/eng/sqr/eng_sqr20.png",
    };

    public String[] eng_prt = {
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt1.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt2.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt3.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt4.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt5.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt6.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt7.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt8.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt9.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt10.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt11.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt12.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt13.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt14.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt15.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt16.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt17.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt18.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt19.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt20.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt21.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt22.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt23.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt24.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt25.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt26.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt27.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt28.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt29.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt30.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt31.png",
            "https://questappx.website/apps/anniversary/frames/eng/prt/eng_prt32.png",

    };

    public int[] colorIDs = {R.color.black, R.color.white, R.color.grey, R.color.purple, R.color.purple_200, R.color.darkpurple, R.color.teal_200, R.color.teal_700, R.color.pink, R.color.blue, R.color.violet, R.color.darkviolet, R.color.darkblue, R.color.grey, R.color.lightgreen, R.color.darkgreen, R.color.yellow, R.color.lightyellow, R.color.lightorange, R.color.darkorange, R.color.brown, R.color.darkbrown, R.color.lightblue, R.color.lightgreen, R.color.parrotlight, R.color.trustviolet, R.color.purplelight, R.color.bluelight, R.color.redlight, R.color.violetdark};
    public int[] fontsIDs = {R.font.minion_concept_font, R.font.font1, R.font.newfonts1, R.font.newfonts2, R.font.font3, R.font.newfonts3, R.font.font4, R.font.newfonts4, R.font.font5, R.font.newfonts5, R.font.font6, R.font.newfonts7, R.font.font8, R.font.newfonts8, R.font.font9, R.font.newfonts9, R.font.font10, R.font.newfonts10, R.font.font11, R.font.newfonts11, R.font.font12, R.font.newfonts12, R.font.font13, R.font.newfonts13, R.font.font14, R.font.newfonts14, R.font.font15, R.font.newfonts15, R.font.font16, R.font.newfonts16, R.font.font17, R.font.newfonts17, R.font.font18, R.font.newfonts18, R.font.font19, R.font.newfonts19, R.font.font20, R.font.newfonts20, R.font.font21, R.font.newfonts21, R.font.font22, R.font.newfonts22, R.font.font23, R.font.font24, R.font.newfonts24, R.font.font25, R.font.newfonts25, R.font.font26, R.font.newfonts26, R.font.font27, R.font.newfonts27, R.font.font28, R.font.newfonts28, R.font.font29, R.font.newfonts29, R.font.font30, R.font.font31, R.font.font32, R.font.font33, R.font.font34, R.font.font35, R.font.font36, R.font.font38, R.font.font39, R.font.montserrat, R.font.besbas};
    public int[] filterIDs = {R.drawable.filter6, R.drawable.filter7, R.color.lightorange, R.drawable.filter8, R.drawable.filter9, R.color.blue, R.drawable.effects_1, R.drawable.effects_2, R.drawable.effects_3, R.drawable.effects_4, R.drawable.effects_5, R.drawable.effects_6, R.drawable.effects_7, R.drawable.effects_8, R.drawable.effects_9, R.drawable.effects_10, R.drawable.effects_11, R.drawable.effects_12, R.drawable.effects_13, R.drawable.effects_14, R.drawable.effects_15, R.drawable.effects_16, R.drawable.effects_17, R.drawable.effects_18, R.drawable.effects_19, R.drawable.effects_20, R.drawable.effects_21, R.drawable.effects_22, R.drawable.effects_23, R.drawable.filter3, R.color.violetdark, R.drawable.filter5, R.drawable.blue_grain_leak, R.drawable.filter1};
    public int[] shapesIDs = {R.drawable.anni_s1, R.drawable.anni_s2, R.drawable.anni_s3, R.drawable.anni_s4, R.drawable.anni_s5, R.drawable.anni_s6, R.drawable.anni_s7, R.drawable.anni_s8, R.drawable.anni_s9, R.drawable.anni_s10, R.drawable.anni_s11, R.drawable.anni_s12, R.drawable.anni_s13, R.drawable.anni_s14, R.drawable.anni_s15, R.drawable.anni_s16, R.drawable.anni_s17, R.drawable.anni_s18, R.drawable.anni_s19, R.drawable.anni_s20, R.drawable.anni_s21, R.drawable.anni_s22, R.drawable.anni_s23, R.drawable.anni_s24, R.drawable.anni_s25, R.drawable.anni_s26, R.drawable.anni_s27, R.drawable.anni_s28, R.drawable.anni_s29, R.drawable.anni_s30, R.drawable.anni_s31, R.drawable.anni_s32, R.drawable.anni_s33, R.drawable.anni_s34, R.drawable.anni_s35, R.drawable.anni_s36, R.drawable.anni_s37, R.drawable.anni_s38, R.drawable.anni_s39, R.drawable.anni_s40, R.drawable.anni_s41, R.drawable.anni_s42, R.drawable.anni_s43, R.drawable.anni_s44, R.drawable.anni_s45, R.drawable.anni_s46, R.drawable.anni_s47, R.drawable.anni_s48, R.drawable.anni_s49, R.drawable.anni_s50, R.drawable.anni_s51, R.drawable.anni_s52, R.drawable.anni_s53, R.drawable.anni_s54, R.drawable.anni_s55, R.drawable.anni_s56, R.drawable.anni_s57, R.drawable.anni_s58, R.drawable.anni_s59, R.drawable.anni_s60, R.drawable.anni_s61, R.drawable.anni_s62, R.drawable.anni_s63, R.drawable.anni_s64, R.drawable.anni_s65, R.drawable.anni_s66, R.drawable.anni_s67, R.drawable.anni_s68, R.drawable.anni_s69, R.drawable.anni_s70, R.drawable.anni_s71, R.drawable.anni_s72, R.drawable.anni_s73, R.drawable.anni_s74, R.drawable.anni_s75, R.drawable.anni_s76, R.drawable.anni_s77, R.drawable.anni_s78, R.drawable.anni_s79, R.drawable.anni_s80, R.drawable.anni_s81, R.drawable.anni_s82, R.drawable.anni_s83, R.drawable.anni_s84, R.drawable.anni_s85, R.drawable.anni_s86, R.drawable.anni_s87, R.drawable.anni_s88, R.drawable.anni_s89, R.drawable.anni_s90, R.drawable.anni_s91, R.drawable.anni_s92, R.drawable.anni_s93, R.drawable.anni_s94, R.drawable.anni_s95, R.drawable.anni_s96, R.drawable.anni_s97, R.drawable.anni_s98, R.drawable.anni_s99, R.drawable.anni_s100, R.drawable.anni_s101, R.drawable.anni_s102, R.drawable.anni_s103, R.drawable.anni_s104, R.drawable.anni_s105, R.drawable.anni_s106, R.drawable.anni_s107, R.drawable.anni_s108, R.drawable.anni_s109, R.drawable.anni_s110, R.drawable.anni_s111, R.drawable.anni_s112, R.drawable.anni_s113, R.drawable.anni_s114, R.drawable.anni_s115, R.drawable.anni_s116, R.drawable.anni_s117, R.drawable.anni_s118, R.drawable.anni_s119, R.drawable.anni_s120, R.drawable.anni_s121, R.drawable.anni_s122, R.drawable.anni_s123, R.drawable.anni_s124, R.drawable.anni_s125, R.drawable.anni_s126, R.drawable.anni_s127, R.drawable.anni_s128, R.drawable.anni_s129, R.drawable.anni_s130, R.drawable.anni_s131, R.drawable.anni_s132, R.drawable.anni_s133, R.drawable.anni_s134, R.drawable.anni_s135, R.drawable.anni_s136, R.drawable.anni_s137, R.drawable.anni_s138, R.drawable.anni_s139, R.drawable.anni_s140, R.drawable.anni_s141, R.drawable.anni_s142, R.drawable.anni_s143, R.drawable.wed_sticker1, R.drawable.wed_sticker2, R.drawable.wed_sticker3, R.drawable.wed_sticker4, R.drawable.wed_sticker5, R.drawable.wed_sticker6, R.drawable.wed_sticker7, R.drawable.wed_sticker8, R.drawable.wed_sticker9, R.drawable.wed_sticker10, R.drawable.wed_sticker11, R.drawable.wed_sticker12, R.drawable.wed_sticker13, R.drawable.wed_sticker14, R.drawable.wed_sticker15, R.drawable.wed_sticker16, R.drawable.wed_sticker17, R.drawable.wed_sticker18, R.drawable.wed_sticker19, R.drawable.wed_sticker20, R.drawable.wed_sticker21, R.drawable.wed_sticker22, R.drawable.wed_sticker23, R.drawable.wed_sticker24, R.drawable.wed_sticker25, R.drawable.wed_sticker26, R.drawable.wed_sticker27, R.drawable.wed_sticker28, R.drawable.sticker28, R.drawable.sticker29, R.drawable.sticker30, R.drawable.sticker31, R.drawable.sticker32, R.drawable.sticker33, R.drawable.sticker34, R.drawable.sticker35, R.drawable.sticker36, R.drawable.sticker37, R.drawable.sticker38, R.drawable.sticker39, R.drawable.sticker40, R.drawable.sticker41, R.drawable.sticker42, R.drawable.sticker43, R.drawable.sticker44, R.drawable.sticker45, R.drawable.sticker46, R.drawable.sticker47, R.drawable.sticker48, R.drawable.sticker49, R.drawable.sticker50, R.drawable.sticker51, R.drawable.sticker52, R.drawable.sticker53, R.drawable.sticker54, R.drawable.sticker55, R.drawable.sticker56, R.drawable.sticker57, R.drawable.sticker58, R.drawable.sticker59, R.drawable.sticker60, R.drawable.sticker61, R.drawable.sticker62, R.drawable.sticker63, R.drawable.sticker64, R.drawable.sticker65, R.drawable.sticker21, R.drawable.sticker22, R.drawable.sticker23, R.drawable.sticker24, R.drawable.sticker25, R.drawable.sticker26, R.drawable.sticker27, R.drawable.sticker66, R.drawable.sticker67, R.drawable.sticker68, R.drawable.sticker69, R.drawable.sticker70, R.drawable.sticker71, R.drawable.sticker72, R.drawable.sticker73, R.drawable.sticker74, R.drawable.sticker75, R.drawable.sticker76, R.drawable.sticker77, R.drawable.sticker78, R.drawable.sticker79, R.drawable.sticker80, R.drawable.sticker81, R.drawable.sticker82, R.drawable.sticker83, R.drawable.sticker84, R.drawable.sticker85, R.drawable.sticker86, R.drawable.sticker87, R.drawable.sticker88, R.drawable.sticker89, R.drawable.sticker90, R.drawable.sticker91, R.drawable.sticker92, R.drawable.sticker93, R.drawable.sticker94, R.drawable.sticker95, R.drawable.sticker96, R.drawable.sticker97, R.drawable.sticker98, R.drawable.sticker99, R.drawable.sticker100, R.drawable.sticker101, R.drawable.sticker102, R.drawable.sticker103, R.drawable.sticker104, R.drawable.sticker105, R.drawable.sticker106, R.drawable.sticker107, R.drawable.sticker108, R.drawable.sticker109, R.drawable.sticker110, R.drawable.sticker111, R.drawable.sticker112, R.drawable.sticker113, R.drawable.sticker114, R.drawable.sticker115, R.drawable.sticker116, R.drawable.sticker117, R.drawable.sticker118, R.drawable.sticker119, R.drawable.sticker120, R.drawable.shapes_lg1, R.drawable.shapes_lg2, R.drawable.shapes_lg3, R.drawable.shapes_lg4, R.drawable.shapes_lg5, R.drawable.shapes_lg6, R.drawable.shapes_lg7, R.drawable.shapes_lg8, R.drawable.shapes_lg9, R.drawable.shapes_lg10, R.drawable.shapes_lg11, R.drawable.shapes_lg12, R.drawable.shapes_lg13, R.drawable.shapes_lg14, R.drawable.shapes_lg15, R.drawable.shapes_lg16, R.drawable.shapes_lg17, R.drawable.shapes_lg18, R.drawable.shapes_lg19, R.drawable.shapes_lg20, R.drawable.shapes_lg21, R.drawable.shapes_lg22, R.drawable.shapes_lg23, R.drawable.shapes_lg24, R.drawable.shapes_lg25, R.drawable.shapes_lg26, R.drawable.shapes_lg27, R.drawable.shapes_lg28, R.drawable.shapes_lg29, R.drawable.shapes_lg30, R.drawable.shapes_lg31, R.drawable.shapes_lg32, R.drawable.shapes_lg33, R.drawable.shapes_lg34, R.drawable.shapes_lg35, R.drawable.shapes_lg36, R.drawable.shapes_lg37, R.drawable.shapes_lg38, R.drawable.shapes_lg39, R.drawable.shapes_lg40, R.drawable.shapes_lg41, R.drawable.shapes_lg42, R.drawable.shapes_lg43, R.drawable.shapes_lg44, R.drawable.shapes_lg45, R.drawable.shapes_lg46, R.drawable.shapes_lg47, R.drawable.shapes_lg48, R.drawable.shapes_lg49, R.drawable.shapes_lg50, R.drawable.shapes_lg51, R.drawable.shapes_lg52, R.drawable.shapes_lg53, R.drawable.shapes_lg54, R.drawable.shapes_lg55, R.drawable.shapes_lg56, R.drawable.shapes_lg57, R.drawable.shapes_lg58, R.drawable.shapes_lg59, R.drawable.shapes_lg60, R.drawable.shapes_lg61, R.drawable.shapes_lg62, R.drawable.shapes_lg63, R.drawable.shapes_lg64, R.drawable.shapes_lg65, R.drawable.shapes_lg66, R.drawable.shapes_lg67, R.drawable.shapes_lg68, R.drawable.shapes_lg69, R.drawable.shapes_lg70, R.drawable.shapes_lg71, R.drawable.shapes_lg72, R.drawable.shapes_lg73, R.drawable.shapes_lg74, R.drawable.shapes_lg75, R.drawable.shapes_lg76, R.drawable.shapes_lg77, R.drawable.shapes_lg78, R.drawable.shapes_lg79, R.drawable.shapes_lg80, R.drawable.shapes_lg81, R.drawable.shapes_lg82, R.drawable.shapes_lg83, R.drawable.shapes_lg84, R.drawable.shapes_lg85, R.drawable.shapes_lg86, R.drawable.shapes_lg87, R.drawable.shapes_lg88, R.drawable.shapes_lg89, R.drawable.shapes_lg90, R.drawable.shapes_lg91, R.drawable.shapes_lg92, R.drawable.shapes_lg93, R.drawable.shapes_lg94, R.drawable.shapes_lg95, R.drawable.shapes_lg96, R.drawable.shapes_lg97, R.drawable.shapes_lg98, R.drawable.shapes_lg99, R.drawable.shapes_lg100, R.drawable.shapes_lg101, R.drawable.shapes_lg102, R.drawable.shapes_lg103, R.drawable.shapes_lg104, R.drawable.shapes_lg105, R.drawable.shapes_lg106, R.drawable.shapes_lg107, R.drawable.shapes_lg108, R.drawable.shapes_lg109, R.drawable.shapes_lg110, R.drawable.shapes_lg111, R.drawable.shapes_lg112, R.drawable.shapes_lg113, R.drawable.shapes_lg114, R.drawable.shapes_lg115, R.drawable.shapes_lg116, R.drawable.shapes_lg117, R.drawable.shapes_lg118, R.drawable.shapes_lg119, R.drawable.shapes_lg120, R.drawable.shapes_lg121, R.drawable.shapes_lg122, R.drawable.shapes_lg123, R.drawable.shapes_lg124, R.drawable.shapes_lg125, R.drawable.shapes_lg126, R.drawable.shapes_lg127, R.drawable.shapes_lg128, R.drawable.shapes_lg129, R.drawable.shapes_lg130, R.drawable.shapes_lg131, R.drawable.shapes_lg132, R.drawable.shapes_lg133, R.drawable.shapes_lg134, R.drawable.shapes_lg135, R.drawable.shapes_lg136, R.drawable.shapes_lg137, R.drawable.shapes_lg138, R.drawable.shapes_lg139, R.drawable.shapes_lg140, R.drawable.shapes_lg141, R.drawable.shapes_lg142, R.drawable.shapes_lg143, R.drawable.shapes_lg144, R.drawable.shapes_lg145, R.drawable.shapes_lg146, R.drawable.shapes_lg147, R.drawable.shapes_lg148, R.drawable.shapes_lg149, R.drawable.shapes_lg150, R.drawable.shapes_lg151, R.drawable.shapes_lg152, R.drawable.shapes_lg153, R.drawable.shapes_lg154, R.drawable.shapes_lg155, R.drawable.shapes_lg156, R.drawable.shapes_lg157, R.drawable.shapes_lg158, R.drawable.shapes_lg159, R.drawable.shapes_lg160, R.drawable.shapes_lg161, R.drawable.shapes_lg162, R.drawable.shapes_lg163, R.drawable.shapes_lg164, R.drawable.shapes_lg165, R.drawable.shapes_lg166, R.drawable.shapes_lg167, R.drawable.shapes_lg168, R.drawable.shapes_lg169, R.drawable.shapes_lg170, R.drawable.shapes_lg171};

    ImageButton framesBtn, filterBtn, choosePhtoto, textBtn, edittextImgBtn, changeColorImgBtn, textShadowImgBtn, changeFontImgBtn, textShadowColorPicker, changeTextOpacityImgBtn, addStickerBtn, saveImageBtn, backArrowBtn;
    ImageView imageviewBg, imageview_filter;
    StickerView stickerView;
    com.xiaopo.flying.sticker.StickerView stickerView_image;
    RelativeLayout layout_artboard;
    public static Uri pickedImage;
    int textShadowRadius, textShadowDx, textShadowDy, textShadowColor;

    TextView tv_frames, tv_filters, tv_crop, tv_text, edittextTv, changeColorTv, textShadowTv, changeFontTv, changeTextOpacityTv, addStickerTv;
    RelativeLayout stickerLayout;

    ImageView shareAppTv, premiumAppVersion;


    RecyclerView recyclerView_filters;
    FontAdapter adapter_filters;
    RelativeLayout linearLayout_frames, linearLayoutColors, linearLayoutFonts, linearLayoutFilters, layoutTextWorkingsLayout, linearLayoutTextOpacity, linearlayoutStickers, linearlayoutStickerAdjustment, linearlayoutStickerColors;
    LinearLayout shadowAdjustmnetLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);


        linkXml();
        clickListener();


        if (interstitialAdImplement == null) {
            interstitialAdImplement = new InterstitialAdImplement(this, interstitialAd);
            interstitialAdImplement.loadInterstitialCall();
        }

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading Ad...");
        dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Random random = new Random();
                int rand = random.nextInt(3);
                if (rand == 1) {
                    if (appOpenManager != null) {
                        appOpenManager.showAdIfAvailable();
                    }

                } else {
                    interstitialAdImplement.setActivityOpenAd(true);
                    interstitialAdImplement.showInterstitial();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            interstitialAdImplement.setActivityOpenAd(false);
                        }
                    }, 1500);
                }

                dialog.dismiss();
            }

        }, 3000);


        new BannerAdImplement(this, findViewById(R.id.adView));


    }

    private void linkXml() {
        framesBtn = findViewById(R.id.framesBtn);
        filterBtn = findViewById(R.id.filterBtn);
        choosePhtoto = findViewById(R.id.cropBtn);
        textBtn = findViewById(R.id.addTextBtn);
        imageviewBg = findViewById(R.id.imageview_Bg);
        imageview_filter = findViewById(R.id.imageview_filter);
        edittextImgBtn = findViewById(R.id.edittextImgBtn);
        changeColorImgBtn = findViewById(R.id.changeColorImg);
        textShadowImgBtn = findViewById(R.id.shadowImg);
        changeFontImgBtn = findViewById(R.id.changeFontImgBtn);
        changeFontTv = findViewById(R.id.changeFontTv);
        changeTextOpacityImgBtn = findViewById(R.id.changeTextOpacityImgBtn);
        textShadowColorPicker = findViewById(R.id.shadowColorpicker);
        changeTextOpacityTv = findViewById(R.id.tvchangeTextOpacity);
        addStickerBtn = findViewById(R.id.addStickerImgBtn);
        addStickerTv = findViewById(R.id.addStickerTv);
        saveImageBtn = findViewById(R.id.saveImgBtn);
        shareAppTv = findViewById(R.id.shareappTv);
        premiumAppVersion = findViewById(R.id.premiumAppVersion);
        backArrowBtn = findViewById(R.id.backArrowBtn);
        linearlayoutList1 = findViewById(R.id.linearlayoutList1);
        orientationMode = getIntent().getIntExtra("frameCategory", 1);

        drawableFrameBg = squareAnniDrawable[0];
        if (orientationMode == 2) {
            drawableFrameBg = portraitAnniDrawable[0];
        } else if (orientationMode == 3) {
            drawableFrameBg = anniInviteDrawable[0];
        }

        getCategoryNames();


        stickerView = findViewById(R.id.stickerview);
        stickerView_image = findViewById(R.id.stickerview_image);
        stickerLayout = findViewById(R.id.stickerLayout);
        shadowAdjustmnetLayout = findViewById(R.id.shadowAdjustlayout);


        stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                stickerView.showBorder = true;
                stickerView.showIcons = true;
                stickerView.invalidate();

            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                stickerView.showBorder = true;
                stickerView.showIcons = true;
                invisibleAllLists();
                stickerView.invalidate();
                if (stickerLayout.getVisibility() == View.GONE) {
                    stickerLayout.setVisibility(View.VISIBLE);
                }

                if (stickerView.getCurrentSticker() instanceof TextSticker) {
                    //if Text is selected
                    textWorkings();
                } else if (stickerView.getCurrentSticker() instanceof com.xiaopo.flying.logoSticker.DrawableSticker) {
                    //If Sticker is selected
                    shapesAdjustmentWorking();
                }

            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                invisibleAllLists();
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {

            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                if (stickerView.getCurrentSticker() instanceof TextSticker) {
                    editText();
                }
            }
        });

        categoryBtnAdapter = new CategoryBtnAdapter(this, categorylist, new RecyclerListener() {
            @Override
            public void OnClick(int position) {
                adapter_frames.list = bgsList.get(position);
                adapter_frames.notifyDataSetChanged();
            }
        });
        RecyclerView recyclerViewCateogories = findViewById(R.id.frameCategoriesRecyclerview);
        recyclerViewCateogories.setLayoutManager(new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCateogories.setHasFixedSize(true);


        recyclerViewCateogories.setAdapter(categoryBtnAdapter);


        textShadowColor = getResources().getColor(R.color.black);
        textShadowDx = 0;
        textShadowDy = 0;
        textShadowRadius = 0;
        activityOpenAd = 0;

        fbInterstitialReward = 0;


        layout_artboard = findViewById(R.id.layoutArtboard);

        linearLayout_frames = findViewById(R.id.linearlayout_frames);
        linearLayoutColors = findViewById(R.id.linearlayout_colors);
        linearLayoutFonts = findViewById(R.id.linearlayout_fonts);
        linearLayoutFilters = findViewById(R.id.linearlayout_filters);
        linearlayoutStickers = findViewById(R.id.linearlayout_stickers);
        linearlayoutStickerAdjustment = findViewById(R.id.linearlayout_StickerAdjustments);
        linearlayoutStickerColors = findViewById(R.id.linearlayout_stickerColors);

        linearLayoutTextOpacity = findViewById(R.id.linearlayoutTextOpacity);

        layoutTextWorkingsLayout = findViewById(R.id.linearlayout_textAdjustments);

        recyclerView_filters = findViewById(R.id.recyclerview_filters);

        tv_filters = findViewById(R.id.filterTv);
        tv_crop = findViewById(R.id.cropTv);
        tv_frames = findViewById(R.id.framesTv);
        tv_text = findViewById(R.id.addTextTv);
        edittextTv = findViewById(R.id.editTextTv);
        changeColorTv = findViewById(R.id.changeColorTv);
        textShadowTv = findViewById(R.id.shadowTv);

        if (orientationMode == 1) {
            imageviewBg.setImageResource(R.drawable.anni_square6);
            setSquareMatrixFrame();
        } else if (orientationMode == 2) {
//            imageviewBg.setImageResource(R.drawable.portrait3);
            imageviewBg.setImageResource(R.drawable.anni_portrait15);
            setPortraitMatrixFrame();
        } else if (orientationMode == 3) {
            imageviewBg.setImageResource(R.drawable.anni_invitation3);

            setPortraitMatrixFrame();
        }


        invisibleAllLists();
        stickerViewImageInit();
        addText("Groom & Bride");
    }

    private void getCategoryNames() {
        if (orientationMode == 1 || orientationMode == 2) {
            categorylist.add("Anniversary");
            categorylist.add("Wedding");
            categorylist.add("Honeymoon");
            categorylist.add("Just Married");
            categorylist.add("Engagement");
        } else {
            categorylist.add("Anniversary");
            categorylist.add("Wedding");
        }

        addFrameData(categorylist.size());
    }

    private void addFrameData(int size) {
        for (int i = 0; i < size; i++) {
            bgsList.add(new ArrayList<>());
        }
        if (orientationMode == 1) {
            addLinksToArray(bgsList.get(0), squareAnniDrawable);
            addLinksToArray(bgsList.get(1), squareWedDrawable);
            addLinksToArray(bgsList.get(2), hny_sqr);
            addLinksToArray(bgsList.get(3), jmrd_sqr);
            addLinksToArray(bgsList.get(4), eng_sqr);
        } else if (orientationMode == 2) {
            addLinksToArray(bgsList.get(0), portraitAnniDrawable);
            addLinksToArray(bgsList.get(1), portraitWedDrawable);
            addLinksToArray(bgsList.get(2), hny_prt);
            addLinksToArray(bgsList.get(3), jmrd_prt);
            addLinksToArray(bgsList.get(4), eng_prt);
        } else {
            addLinksToArray(bgsList.get(0), anniInviteDrawable);
            addLinksToArray(bgsList.get(1), wedInvitationDrawable);
        }
        adapter_frames = new RecyclerAdapter(EditorActivity.this);
        adapter_frames.list = bgsList.get(0);
    }

    private void addLinksToArray(ArrayList arrayList, String[] frames) {
        for (int i = 0; i < frames.length; i++) {
            arrayList.add(frames[i]);
        }
    }

    private void setSquareMatrixFrame() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        ((RelativeLayout.LayoutParams) layout_artboard.getLayoutParams()).removeRule(RelativeLayout.ABOVE);

        layout_artboard.getLayoutParams().height = width;
        layout_artboard.getLayoutParams().width = width;
    }

    private void setPortraitMatrixFrame() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;


        ((RelativeLayout.LayoutParams) layout_artboard.getLayoutParams()).removeRule(RelativeLayout.ABOVE);

        layout_artboard.getLayoutParams().height = (int) (width * 1.6d);
        layout_artboard.getLayoutParams().width = width;
    }


    private void showAdmobRewarded(String drawablelink)
    {
        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mRewardedAd = null;
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mRewardedAd = null;
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.");


            }
        });

        mRewardedAd.show(EditorActivity.this, new OnUserEarnedRewardListener() {
            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                Glide.with(EditorActivity.this).load(drawablelink).into(imageviewBg);
            }
        });
    }

    private void showFbInterstitialForReward(String drawable) {
        drawableFrameBg = drawable;
        fbInterstitialReward = 1;
        showFbInterstitial();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @SuppressLint("ClickableViewAccessibility")
    private void watchAdToUnlock(String drawableId) {
        Dialog dialog = new Dialog(EditorActivity.this);
        dialog.setContentView(R.layout.watchad_dialoge);
        dialog.show();
        ImageView buttonCancel = dialog.findViewById(R.id.dialogBtnCancel);
        ImageView buttonPurchaseApp = dialog.findViewById(R.id.dialogBtnPurchaseApp);
        ImageView buttonOk = dialog.findViewById(R.id.dialogBtnOk);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(false);



        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearlayoutList1.setVisibility(View.GONE);
                loadAdmobRewarded(drawableId);
//                showFbInterstitialForReward(drawableId);
                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonPurchaseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                inApp.startPurchase();
            }
        });



    }

    private void loadAdmobRewarded(String drawableId) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Ad, Please Wait...");
        dialog.setCancelable(false);
        dialog.show();

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, String.valueOf(getResources().getString(R.string.admob_rewarded_id)),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        dialog.dismiss();
                        Log.d(TAG, loadAdError.toString());
                        Toast.makeText(EditorActivity.this, "Error Loading Ad", Toast.LENGTH_SHORT).show();
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        dialog.dismiss();
                        mRewardedAd = ad;
                        Log.d(TAG, "Ad was loaded.");
                        showAdmobRewarded(drawableId);

                    }
                });
    }

    private void invisibleList1() {
        if (linearLayout_frames.getVisibility() == View.VISIBLE) {
            linearLayout_frames.setVisibility(View.GONE);
        }
        if (linearLayoutFilters.getVisibility() == View.VISIBLE) {
            linearLayoutFilters.setVisibility(View.GONE);
        }
        if (linearlayoutStickers.getVisibility() == View.VISIBLE) {
            linearlayoutStickers.setVisibility(View.GONE);
        }

        //textviews
        tv_frames.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        tv_filters.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        tv_crop.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        tv_text.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        addStickerTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));

        //imageviews
    }

    private void invisibleList2() {
        if (linearLayoutColors.getVisibility() == View.VISIBLE) {
            linearLayoutColors.setVisibility(View.GONE);
        }
        if (linearLayoutFonts.getVisibility() == View.VISIBLE) {
            linearLayoutFonts.setVisibility(View.GONE);
        }
        if (shadowAdjustmnetLayout.getVisibility() == View.VISIBLE) {
            shadowAdjustmnetLayout.setVisibility(View.GONE);
        }
        if (linearLayoutTextOpacity.getVisibility() == View.VISIBLE) {
            linearLayoutTextOpacity.setVisibility(View.GONE);
        }

        edittextTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        changeColorTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        textShadowTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        changeFontTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
        changeTextOpacityTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));


    }

    public void invisibleAllLists(View view) {
        invisibleList1();
        invisibleList2();
    }

    public void invisibleAllLists() {
        invisibleList1();
        invisibleList2();
        layoutTextWorkingsLayout.setVisibility(View.GONE);
        linearlayoutStickerAdjustment.setVisibility(View.GONE);

    }


    private void clickListener() {
        framesBtn.setOnClickListener(v -> {
            invisibleAllLists();
            framesWorking();
            linearLayout_frames.setVisibility(View.VISIBLE);
            tv_frames.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));

        });

        premiumAppVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inApp.showDialog(EditorActivity.this);
            }
        });

        filterBtn.setOnClickListener(v -> {
            invisibleAllLists();
            filtersWorking();
            linearLayoutFilters.setVisibility(View.VISIBLE);
            tv_filters.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));

        });

        choosePhtoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleAllLists();
                tv_crop.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
                choosePhotoFromGallery(v);
            }
        });

        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleAllLists();
                tv_text.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
                addTextDialog();
            }
        });

        addStickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleAllLists();
                linearlayoutStickers.setVisibility(View.VISIBLE);
                addStickerTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
                stickersWorking();
            }
        });

        shareAppTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_shareapp();
            }
        });

        saveImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFbInterstitial();
                stickerView.showBorder = false;
                stickerView.showIcons = false;
                stickerView.invalidate();
                Bitmap imagetosave = new SaveImage(EditorActivity.this, layout_artboard).saveAsJPGwORK();
                showSaveImageDialog(imagetosave);
            }
        });


        stickerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                stickerLayout.setVisibility(View.GONE);
                stickerView.showIcons = false;
                stickerView.showBorder = false;
                invisibleAllLists();
                stickerView.invalidate();
                return true;
            }
        });

        backArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void showFbInterstitial() {
        interstitialAdImplement.showInterstitial();
    }


    private void textWorkings() {
        layoutTextWorkingsLayout.setVisibility(View.VISIBLE);

        RelativeLayout editTextBtn = findViewById(R.id.editTextBtn);
        RelativeLayout changeColorBtn = findViewById(R.id.changeColorBtn);
        RelativeLayout shadowTextBtn = findViewById(R.id.shadowBtn);
        RelativeLayout changeFontBtn = findViewById(R.id.changeFontBtn);
        RelativeLayout changeTextOpacity = findViewById(R.id.changeTextOpacityBtn);


        editTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleList2();
                editText();
                edittextTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
            }
        });

        changeColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleList2();
                linearLayoutColors.setVisibility(View.VISIBLE);
                changeColorTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
                colorsWorking();
            }
        });

        shadowTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleList2();
                shadowWorkings();
                shadowAdjustmnetLayout.setVisibility(View.VISIBLE);
                textShadowTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
            }
        });

        changeFontBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleList2();
                linearLayoutFonts.setVisibility(View.VISIBLE);
                changeFontTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
                fontWorking();
            }
        });

        changeTextOpacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invisibleList2();
                linearLayoutTextOpacity.setVisibility(View.VISIBLE);
                changeTextOpacityTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.mainAppColor));
                textOpacityWorking();
            }
        });

    }

    private void textOpacityWorking() {
        SeekBar seekBarOpacity = findViewById(R.id.seekTextOpacity);
        seekBarOpacity.setMax(255);
        seekBarOpacity.setProgress(seekBarOpacity.getMax());

        seekBarOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    if (stickerView.getCurrentSticker() instanceof TextSticker) {
                        (stickerView.getCurrentSticker()).setAlpha(progress);
                        stickerView.invalidate();
                    } else {
                        Log.v("ss", "errr");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void shadowWorkings() {
        SeekBar seekBarDx = findViewById(R.id.seekTextShadowDX);
        seekBarDx.setProgress(textShadowDx);
        seekBarDx.setMax(50);

        SeekBar seekBarDy = findViewById(R.id.seekTextShadowDy);
        seekBarDy.setProgress(textShadowDy);
        seekBarDy.setMax(50);

        SeekBar seekBarRadius = findViewById(R.id.seekTextShadowRadius);
        seekBarRadius.setProgress(textShadowRadius);
        seekBarRadius.setMax(25);


        seekBarDx.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textShadowDx = progress;
                try {
                    if (stickerView.getCurrentSticker() instanceof TextSticker) {


                        ((TextSticker) stickerView.getCurrentSticker()).textPaint.setShadowLayer(textShadowRadius, textShadowDx, textShadowDy, textShadowColor);
                        stickerView.invalidate();
                    } else {
                        Toast.makeText(EditorActivity.this, "Select Text", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarDy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textShadowDy = progress;
                try {
                    if (stickerView.getCurrentSticker() instanceof TextSticker) {

                        ((TextSticker) stickerView.getCurrentSticker()).textPaint.setShadowLayer(textShadowRadius, textShadowDx, textShadowDy, textShadowColor);
                        stickerView.invalidate();
                    } else {
                        Toast.makeText(EditorActivity.this, "Select Text", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textShadowRadius = progress;
                try {
                    if (stickerView.getCurrentSticker() instanceof TextSticker) {

                        ((TextSticker) stickerView.getCurrentSticker()).textPaint.setShadowLayer(textShadowRadius, textShadowDx, textShadowDy, textShadowColor);
                        stickerView.invalidate();
                    } else {
                        Toast.makeText(EditorActivity.this, "Select Text", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textShadowColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(EditorActivity.this, getResources().getColor(R.color.blue), true, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        try {
                            if (stickerView.getCurrentSticker() instanceof TextSticker) {
                                textShadowColor = color;
                                ((TextSticker) stickerView.getCurrentSticker()).textPaint.setShadowLayer(textShadowRadius, textShadowDx, textShadowDy, textShadowColor);
                                stickerView.invalidate();
                            } else {
                                Toast.makeText(EditorActivity.this, "Select Text", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    private void shapesAdjustmentWorking() {
        linearlayoutStickerAdjustment.setVisibility(View.VISIBLE);


        SeekBar stickerOpacitySeebar;
        ImageButton stickerColorPickerBtn;

//        LinearLayoutManager linearLayoutManageStickerColors = new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL,false);
//        RecyclerView recyclerView_stickerColors = findViewById(R.id.recyclerviewStickerColors);
//        recyclerView_stickerColors.setLayoutManager(linearLayoutManageStickerColors);
//        recyclerView_stickerColors.setHasFixedSize(true);

//        RecyclerAdapter adapter_StickerColors = new RecyclerAdapter(EditorActivity.this, MainActivity.colorIDs, 2, new RecyclerListener() {
//            @Override
//            public void OnClick(int position) {
//                changeStickerColor(MainActivity.colorIDs[position]);
//            }
//        });
//        recyclerView_stickerColors.setAdapter(adapter_StickerColors);


        stickerOpacitySeebar = findViewById(R.id.seekbarStickerOpacity);
        stickerOpacitySeebar.setMax(255);
        stickerOpacitySeebar.setProgress(stickerOpacitySeebar.getMax());

        stickerOpacitySeebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    if (stickerView.getCurrentSticker() instanceof com.xiaopo.flying.logoSticker.DrawableSticker) {
                        ((com.xiaopo.flying.logoSticker.DrawableSticker) stickerView.getCurrentSticker()).setAlpha(progress);
                        stickerView.invalidate();
                    } else {
                        Log.v("ss", "errr");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        stickerColorPickerBtn = findViewById(R.id.stickerColorPickerBtn);

        stickerColorPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(EditorActivity.this, getResources().getColor(R.color.blue), true, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        changeStickerColor(color);
                    }
                });
                dialog.show();
            }
        });

    }

    private void changeStickerColor(int color) {
        try {
            if (stickerView.getCurrentSticker() instanceof com.xiaopo.flying.logoSticker.DrawableSticker) {
                ColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY);
                com.xiaopo.flying.logoSticker.DrawableSticker drawableSticker;
                Drawable mStickerDrawable = ((com.xiaopo.flying.logoSticker.DrawableSticker) stickerView.getCurrentSticker()).getDrawable().mutate();

                mStickerDrawable.setColorFilter(colorFilter);
                drawableSticker = (com.xiaopo.flying.logoSticker.DrawableSticker) stickerView.getCurrentSticker().setDrawable(mStickerDrawable);

                stickerView.replace(drawableSticker);
                stickerView.invalidate();
            } else {
                Toast.makeText(EditorActivity.this, "Select Text", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    public void addTextDialog() {
        Dialog dialog = new Dialog(EditorActivity.this);
        dialog.setContentView(R.layout.changetext_dialo);
        dialog.show();
        Button buttonCancel = dialog.findViewById(R.id.dialogBtnCancel);
        Button buttonOk = dialog.findViewById(R.id.dialogBtnOk);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        EditText editTextDialog = dialog.findViewById(R.id.edittextDialog);

//        buttonOk.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                buttonOk.setBackgroundColor(ContextCompat.getColor(EditorActivity.this, R.color.red));
//                return false;
//            }
//        });


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextDialog.getText().toString())) {
                    dialog.dismiss();
                    Toast.makeText(EditorActivity.this, "Please Enter text", Toast.LENGTH_SHORT).show();

                    return;
                }
                addText(editTextDialog.getText().toString());
                dialog.dismiss();
                invisibleAllLists();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                tv_text.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));

            }
        });

//        buttonCancel.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                buttonCancel.setBackgroundColor(ContextCompat.getColor(EditorActivity.this, R.color.red));
//                return false;
//            }
//        });


    }

    private void editText() {
        Dialog dialog = new Dialog(EditorActivity.this);
        dialog.setContentView(R.layout.changetext_dialo);
        dialog.show();
        Button buttonCancel = dialog.findViewById(R.id.dialogBtnCancel);
        Button buttonOk = dialog.findViewById(R.id.dialogBtnOk);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        EditText editTextDialog = dialog.findViewById(R.id.edittextDialog);
        TextSticker textSticker = (TextSticker) stickerView.getCurrentSticker();

        assert textSticker != null;
        editTextDialog.setText(textSticker.getText());


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert textSticker != null;
                edittextTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));

                if (TextUtils.isEmpty(editTextDialog.getText().toString())) {
                    dialog.dismiss();
                    Toast.makeText(EditorActivity.this, "Please Enter text", Toast.LENGTH_SHORT).show();
                    return;
                }

                textSticker.setText(editTextDialog.getText().toString());
                textSticker.resizeText();
                stickerView.invalidate();
                dialog.dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittextTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
                dialog.dismiss();
            }
        });


    }


    @SuppressLint("ResourceAsColor")
    private void addText(String name) {
        TextSticker textSticker = new TextSticker(this);
        textSticker.setText(name);


        textSticker.setTextColor(Color.BLACK);
        textSticker.resizeText();

        Typeface typeface = ResourcesCompat.getFont(EditorActivity.this, R.font.minion_concept_font);
        textSticker.setTypeface(typeface);
        textSticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        stickerView.addSticker(textSticker);

    }

    private void stickerViewImageInit() {
        com.xiaopo.flying.sticker.DrawableSticker sticker = new DrawableSticker(getDrawable(R.drawable.model));
        stickerView_image.addSticker(sticker);
        stickerView_image.invalidate();

    }

    public void choosePhotoFromGallery(View v) {
        // Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i, LOAD_IMAGE_RESULTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            pickedImage = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(pickedImage);
                Drawable drawable = Drawable.createFromStream(inputStream, pickedImage.toString());

                if (orientationMode == 3) {
                    com.xiaopo.flying.logoSticker.DrawableSticker sticker = new com.xiaopo.flying.logoSticker.DrawableSticker(drawable);
                    stickerView.addSticker(sticker);
                    stickerView.invalidate();
                } else {
                    com.xiaopo.flying.sticker.DrawableSticker sticker = new com.xiaopo.flying.sticker.DrawableSticker(drawable);
                    stickerView_image.removeAllStickers();
                    stickerView_image.addSticker(sticker);
                    stickerView_image.invalidate();
                    //to change picture in filter layout
                    adapter_filters.notifyDataSetChanged();
                }

//                appOpenManager.showAdIfAvailable();

            } catch (Exception e) {
//                Toast.makeText(EditorActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        invisibleAllLists();
    }


    public void saveImage() {
        if (ActivityCompat.checkSelfPermission(EditorActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            Toast.makeText(EditorActivity.this, "You Should Grant Permission", Toast.LENGTH_LONG).show();
            if (Build.VERSION.SDK_INT >= 23) {
                EditorActivity.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
            }
        }

        stickerView.showBorder = false;
        stickerView.showIcons = false;
        stickerView.invalidate();

        EditorActivity frameView = EditorActivity.this;
        new EditorActivity.ViewToImage(frameView, layout_artboard, new ActionListeners() {
            @Override
            public void convertedWithError(String str) {
                EditorActivity secondActivity = EditorActivity.this;
//                Toast.makeText(secondActivity, "" + str, Toast.LENGTH_LONG).show();
            }

            @Override
            public void convertedWithSuccess(Bitmap bitmap, String str) {
                EditorActivity secondActivity = EditorActivity.this;
//                Toast.makeText(secondActivity, "" + str, Toast.LENGTH_LONG).show();
            }
        });
    }

    public class ViewToImage {
        Bitmap bitmap = null;
        Context context;
        String fileName = "image";
        String filePath = null;
        String folderName = "DCIM";
        ActionListeners listeners;
        View view;

        public ViewToImage(Context context2, View view2, ActionListeners actionListeners) {
            this.context = context2;
            this.listeners = actionListeners;
            this.view = view2;
            convert();
        }

        private void convert() {
            View view2 = this.view;
            Bitmap bitmapFromView = getBitmapFromView(view2, view2.getWidth(), this.view.getHeight());
            if (this.fileName.equals("image")) {
                saveTheImage(bitmapFromView, (String) null);
            } else {
                saveTheImage(bitmapFromView, this.fileName);
            }
        }

        private Bitmap getBitmapFromView(View view2, int i, int i2) {
            Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Drawable background = view2.getBackground();
            if (background != null) {
                background.draw(canvas);
            } else {
                canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
            }
            view2.draw(canvas);
            return createBitmap;
        }

        //        private void saveTheImage(Bitmap bitmap2, String str) {
        //            String str2;
        //            File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + this.folderName);
        //            if (!file.exists()) {
        //                file.mkdirs();
        //            }
        //            int nextInt = new Random().nextInt(10000);
        //            if (str == null) {
        //                str2 = "image-" + nextInt + ".jpg";
        //            } else {
        //                str2 = str + ".jpg";
        //            }
        //            File file2 = new File(file, str2);
        //            this.context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file2)));
        //            if (file2.exists()) {
        //                file2.delete();
        //            }
        //            try {
        //                FileOutputStream fileOutputStream = new FileOutputStream(file2);
        //                bitmap2.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        //                fileOutputStream.flush();
        //                fileOutputStream.close();
        ////                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        //                imagersaverInt++;
        //                if(imagersaverInt == 1)
        //                {
        //                    File imageFile = new File(Environment.getExternalStorageDirectory().toString() + "/" + this.folderName + "/" + str2);
        //                    if (imageFile.exists())
        //                    {
        //                        Toast.makeText(this.context, "Image is Saved in /" + this.folderName + " Folder in your Device!", Toast.LENGTH_LONG).show();
        //                    }
        //                }
        //                else if(imagersaverInt == 2)
        //                {
        //                    imagersaverInt = 0;
        //                    saveTheImage(bitmap2, str);
        //                }
        //                this.filePath = str2;
        //                ActionListeners actionListeners = this.listeners;
        //                if (actionListeners != null) {
        //                    actionListeners.convertedWithSuccess(this.bitmap, str2);
        //                }
        //            } catch (Exception e) {
        //                e.printStackTrace();
        //                ActionListeners actionListeners2 = this.listeners;
        //                if (actionListeners2 != null) {
        //                    actionListeners2.convertedWithError(e.getMessage());
        //                }
        //            }
        //        }

        private void saveTheImage(Bitmap bitmap2, String str) {
            String str2;

            int nextInt = new Random().nextInt(10000);
            if (str == null) {
                str2 = "image-" + nextInt + ".jpg";
            } else {
                str2 = str + ".jpg";
            }

            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap2, str2, null);
                Toast.makeText(EditorActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                ActionListeners actionListeners2 = this.listeners;
                if (actionListeners2 != null) {
                    actionListeners2.convertedWithError(e.getMessage());
                }
            }
        }
    }

    public void onBackPressed() {
        if (linearlayoutList1.getVisibility() == View.VISIBLE) {
            linearlayoutList1.setVisibility(View.GONE);
        } else {
            AlertDialog diaBox = AskOption();
            diaBox.show();
        }
    }

    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Changes may not be saved. Are you sure you want to exit?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        stickerView.removeAllStickers();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    private void framesWorking() {
        LinearLayoutManager linearLayoutManagerframes = new GridLayoutManager(EditorActivity.this, 2);
//        LinearLayoutManager linearLayoutManagerframes = new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView_frames = findViewById(R.id.recyclerview_frames);
        recyclerView_frames.setLayoutManager(linearLayoutManagerframes);
        recyclerView_frames.setHasFixedSize(true);

        if (linearlayoutList1.getVisibility() == View.GONE) {
            linearlayoutList1.setVisibility(View.VISIBLE);
        }


        adapter_frames = new RecyclerAdapter(EditorActivity.this, bgsList.get(0), 1, new RecyclerListener() {
            @Override
            public void OnClick(int position) {
                Glide.with(EditorActivity.this).load(adapter_frames.list.get(position)).into(imageviewBg);
                linearlayoutList1.setVisibility(View.GONE);
            }
        });
        adapter_frames.setProDataListener(new ProDataListener() {
            @Override
            public void onClick(int position) {
                String drawableId = adapter_frames.list.get(position);
                watchAdToUnlock(drawableId);

            }
        });
        if (orientationMode != 1) {
            adapter_frames.setPortraitSizeLayout(true);
        }
        recyclerView_frames.setAdapter(adapter_frames);


    }

    private void colorsWorking() {
        LinearLayoutManager linearLayoutManageColors = new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView_colors = findViewById(R.id.recyclerview_colors);
        recyclerView_colors.setLayoutManager(linearLayoutManageColors);
        recyclerView_colors.setHasFixedSize(true);

        if (linearlayoutList1.getVisibility() == View.GONE) {
            linearlayoutList1.setVisibility(View.VISIBLE);
        }

        ImageButton colorPickerBtn = findViewById(R.id.textColorPickerBtn);
        colorPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(EditorActivity.this, getResources().getColor(R.color.black), true, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        try {
                            if (stickerView.getCurrentSticker() instanceof TextSticker) {
                                ((TextSticker) stickerView.getCurrentSticker()).setTextColor(color);
                                stickerView.invalidate();
                            } else {
                                Toast.makeText(EditorActivity.this, "Select Text", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                dialog.show();
            }
        });


        FontAdapter adapter_colors = new FontAdapter(EditorActivity.this, colorIDs, 2, new RecyclerListener() {
            @Override
            public void OnClick(int position) {
                TextSticker sticker = (TextSticker) stickerView.getCurrentSticker();
                if (sticker == null) {
                    Toast.makeText(EditorActivity.this, "Please select any text first", Toast.LENGTH_SHORT).show();
                    return;
                }
                sticker.setTextColor(ContextCompat.getColor(EditorActivity.this, colorIDs[position]));
                stickerView.invalidate();
//                changeColorTv.setTextColor(ContextCompat.getColor(EditorActivity.this, R.color.lightblack));
//                changeColorImgBtn.setBackgroundTintList(ContextCompat.getColorStateList(EditorActivity.this, R.color.lightblack));
            }
        });
        recyclerView_colors.setAdapter(adapter_colors);
    }

    private void fontWorking() {
        LinearLayoutManager linearLayoutManagerfonts = new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView_fonts = findViewById(R.id.recyclerview_fonts);
        recyclerView_fonts.setLayoutManager(linearLayoutManagerfonts);
        recyclerView_fonts.setHasFixedSize(true);

        if (linearlayoutList1.getVisibility() == View.GONE) {
            linearlayoutList1.setVisibility(View.VISIBLE);
        }

        FontAdapter adapter_fonts = new FontAdapter(EditorActivity.this, fontsIDs, 3, new RecyclerListener() {
            @Override
            public void OnClick(int position) {
                TextSticker sticker = (TextSticker) stickerView.getCurrentSticker();
                if (sticker == null) {
                    Toast.makeText(EditorActivity.this, "Please select any text first", Toast.LENGTH_SHORT).show();
                    return;
                }
                Typeface typeface = ResourcesCompat.getFont(EditorActivity.this, fontsIDs[position]);
                sticker.setTypeface(typeface);
                stickerView.invalidate();

            }
        });
        recyclerView_fonts.setAdapter(adapter_fonts);
    }

    private void showSaveImageDialog(Bitmap bitmap) {
        Dialog dialog = new Dialog(EditorActivity.this);
        dialog.setContentView(R.layout.imagesaved_dialog);
        dialog.show();
        TextView buttonOk = dialog.findViewById(R.id.dialogBtnSavedOk);
        ImageView savedImaged = dialog.findViewById(R.id.savedImageIv);
        RelativeLayout localAdlayout = dialog.findViewById(R.id.localAdLayout);
        ImageView adImg = dialog.findViewById(R.id.localAdIv);
        TextView adTitle = dialog.findViewById(R.id.localAdTitle);
        TextView adDesc = dialog.findViewById(R.id.localAdDesc);
        String appLink;

        int randomAd = randomLocalAd();
        if (randomAd == 1) {
            Glide.with(EditorActivity.this).load(Data.App1Drawable).into(adImg);
//            adImg.setImageResource(Data.App1Drawable);
            adTitle.setText(Data.App1Title);
            adDesc.setText(Data.App1Desc);
            appLink = Data.App1Link;
        } else if (randomAd == 2) {
            Glide.with(EditorActivity.this).load(Data.App2Drawable).into(adImg);
//            adImg.setImageResource(Data.App2Drawable);
            adTitle.setText(Data.App2Title);
            adDesc.setText(Data.App2Desc);
            appLink = Data.App2Link;
        } else if (randomAd == 3) {
            Glide.with(EditorActivity.this).load(Data.App3Drawable).into(adImg);
//            adImg.setImageResource(Data.App3Drawable);
            adTitle.setText(Data.App3Title);
            adDesc.setText(Data.App3Desc);
            appLink = Data.App3Link;
        } else if (randomAd == 4) {
            Glide.with(EditorActivity.this).load(Data.App4Drawable).into(adImg);
//            adImg.setImageResource(Data.App4Drawable);
            adTitle.setText(Data.App4Title);
            adDesc.setText(Data.App4Desc);
            appLink = Data.App4Link;
        } else if (randomAd == 5) {
            Glide.with(EditorActivity.this).load(Data.App5Drawable).into(adImg);
//            adImg.setImageResource(Data.App5Drawable);
            adTitle.setText(Data.App5Title);
            adDesc.setText(Data.App5Desc);
            appLink = Data.App5Link;
        } else if (randomAd == 6) {
            Glide.with(EditorActivity.this).load(Data.App6Drawable).into(adImg);
//            adImg.setImageResource(Data.App6Drawable);
            adTitle.setText(Data.App6Title);
            adDesc.setText(Data.App6Desc);
            appLink = Data.App6Link;
        } else if (randomAd == 7) {
            Glide.with(EditorActivity.this).load(Data.App7Drawable).into(adImg);
//            adImg.setImageResource(Data.App7Drawable);
            adTitle.setText(Data.App7Title);
            adDesc.setText(Data.App7Desc);
            appLink = Data.App7Link;
        } else {
            Glide.with(EditorActivity.this).load(Data.App1Drawable).into(adImg);
//            adImg.setImageResource(Data.App1Drawable);
            adTitle.setText(Data.App1Title);
            adDesc.setText(Data.App1Desc);
            appLink = Data.App1Link;
        }


        Drawable d = new BitmapDrawable(getResources(), bitmap);
        savedImaged.setImageDrawable(d);

        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                RateItDialogFragment.show(EditorActivity.this, getSupportFragmentManager());
            }
        });

        localAdlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreapp(appLink);
            }
        });

    }

    private void moreapp(String link) {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private int randomLocalAd() {
        Random rn = new Random();
        int answer = rn.nextInt(7) + 1;

        return answer;
    }


    private void filtersWorking() {
        ImageView imageViewfilterUpside = findViewById(R.id.imageview_filterUpside);
        LinearLayoutManager linearLayoutManagerfilters = new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_filters.setLayoutManager(linearLayoutManagerfilters);
        recyclerView_filters.setHasFixedSize(true);
        adapter_filters = new FontAdapter(EditorActivity.this, filterIDs, 4, new RecyclerListener() {
            @Override
            public void OnClick(int position) {
                if (position == 0) {
                    if (orientationMode == 3) {
                        if (imageViewfilterUpside.getVisibility() == View.VISIBLE) {
                            imageViewfilterUpside.setVisibility(View.GONE);
                        }
                    } else {
                        if (imageview_filter.getVisibility() == View.VISIBLE) {
                            imageview_filter.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (orientationMode == 3) {
                        imageViewfilterUpside.setImageResource(filterIDs[position]);
                        if (imageViewfilterUpside.getVisibility() == View.GONE) {
                            imageViewfilterUpside.setVisibility(View.VISIBLE);
                        }
                    } else {
                        imageview_filter.setImageResource(filterIDs[position]);
                        if (imageview_filter.getVisibility() == View.GONE) {
                            imageview_filter.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        recyclerView_filters.setAdapter(adapter_filters);

        SeekBar seekBarFilters = findViewById(R.id.filterSeekOpacity);
        seekBarFilters.setProgress(255);
        seekBarFilters.setMax(255);
        seekBarFilters.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (orientationMode == 3) {
                    imageViewfilterUpside.setAlpha(progress);
                    imageViewfilterUpside.invalidate();
                } else {
                    imageview_filter.setAlpha(progress);
                    imageview_filter.invalidate();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void stickersWorking() {
        LinearLayoutManager linearLayoutManagerStickers = new GridLayoutManager(EditorActivity.this, 4);
//        LinearLayoutManager linearLayoutManagerStickers = new LinearLayoutManager(EditorActivity.this, LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView_stickers = findViewById(R.id.recyclerview_stickers);
        recyclerView_stickers.setLayoutManager(linearLayoutManagerStickers);
        recyclerView_stickers.setHasFixedSize(true);

        if (linearlayoutList1.getVisibility() == View.GONE) {
            linearlayoutList1.setVisibility(View.VISIBLE);
        }
        FontAdapter adapter_stickers = new FontAdapter(EditorActivity.this, shapesIDs, 5, new RecyclerListener() {
            @Override
            public void OnClick(int position) {
                Drawable drawable = ContextCompat.getDrawable(EditorActivity.this, shapesIDs[position]);
                com.xiaopo.flying.logoSticker.DrawableSticker drawableSticker = new com.xiaopo.flying.logoSticker.DrawableSticker(drawable);
                stickerView.addSticker(drawableSticker);
                stickerView.invalidate();

                linearlayoutList1.setVisibility(View.GONE);
            }
        });
        recyclerView_stickers.setAdapter(adapter_stickers);
    }

    public void invisibleList1(View view) {
        invisibleList1();
        invisibleList2();
    }

    private void func_shareapp() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String shareMessage = "\nLet your Friends know that you are using this app... Install now from link below:\n\n";
            String appLink = "https://play.google.com/store/apps/details?id=" + getPackageName();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage + appLink);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void func_NewYearEditor() {
        Uri uri = Uri.parse(Data.NewYearEditorLink);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}
