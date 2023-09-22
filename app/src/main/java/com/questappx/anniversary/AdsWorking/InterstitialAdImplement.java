package com.questappx.anniversary.AdsWorking;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.questappx.anniversary.Data;
import com.questappx.anniversary.MainActivity;
import com.questappx.anniversary.R;
import com.questappx.anniversary.RecyclerListener;


public class InterstitialAdImplement {

    RecyclerListener itemClickListener;

    private static final String TAG = "InterstitialAdImplement";
    Context context;
    InterstitialAd interstitialAd;
    com.facebook.ads.InterstitialAd fbInterstitial;

    boolean activityOpenAd = false;


    public InterstitialAdImplement(Context context, InterstitialAd interstitialAd, com.facebook.ads.InterstitialAd fbInterstitial) {
        this.context = context;
        this.interstitialAd = interstitialAd;
        this.fbInterstitial = fbInterstitial;
    }

    public void setItemClickListener(RecyclerListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public void setActivityOpenAd(boolean bool)
    {
        activityOpenAd = bool;
    }

    public void loadInterstitialCall() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, context.getResources().getString(R.string.admob_interstitial), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        MainActivity.interstitialAd = interstitialAd;
                        Log.i(TAG, "Admob : onAdLoaded");

                        if(activityOpenAd)
                        {
                            showInterstitial();
                            activityOpenAd = false;
                        }

                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d(TAG, "Admob : The ad was dismissed.");
                                itemClickListener.OnClick(0);
//                                EventBus.getDefault().post(new AdMessageEvent("onAdClosed"));
                                loadInterstitialCall();
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                MainActivity.interstitialAd = null;
                                Log.d(TAG, "Admob : The ad was shown.");
                            }
                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        MainActivity.interstitialAd = null;
                        initializeFB_Interstitial();
                    }


                });

    }
    private void initializeFB_Interstitial() {
//        interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#"+Data.FB_INTERSTITIAL_ID);
        fbInterstitial = new com.facebook.ads.InterstitialAd(context, Data.FB_INTERSTITIAL_ID);
        // Create listeners for the Interstitial Ad
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Fb :Interstitial ad displayed.");
                itemClickListener.OnClick(1);
//                Toast.makeText(EditorActivity.this, "intersitial displayed", Toast.LENGTH_SHORT).show();
//                interstitialAd.loadAd(
//                        interstitialAd.buildLoadAdConfig()
//                                .withAdListener(this)
//                                .build());
            }


            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Fb :Interstitial ad dismissed.");
//                Toast.makeText(EditorActivity.this, "interstitial dismissed", Toast.LENGTH_SHORT).show();
                fbInterstitial.loadAd(
                        fbInterstitial.buildLoadAdConfig()
                                .withAdListener(this)
                                .build());
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                MainActivity.fbInterstitial = null;
                // Ad error callback
                Log.e(TAG, "Fb :Interstitial ad failed to load: " + adError.getErrorMessage());
//                Toast.makeText(EditorActivity.this, adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Fb :Interstitial ad is loaded and ready to be displayed!");
//                Toast.makeText(EditorActivity.this, "on adLoaded", Toast.LENGTH_SHORT).show();
                // Show the ad
//                interstitialAd.show();
                if(activityOpenAd)
                {
                    showInterstitial();
                    activityOpenAd = false;
                }
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Fb :Interstitial ad clicked!");
//                Toast.makeText(EditorActivity.this, "onAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Fb :Interstitial ad impression logged!");
//                Toast.makeText(EditorActivity.this, "onLogingImpression", Toast.LENGTH_SHORT).show();
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        fbInterstitial.loadAd(
                fbInterstitial.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());
    }

    public void showInterstitial()
    {
        if(MainActivity.interstitialAd != null)
        {
            MainActivity.interstitialAd.show(((Activity) context));
        }
        else if(MainActivity.fbInterstitial != null)
        {
            if(MainActivity.fbInterstitial.isAdLoaded())
            {
                MainActivity.fbInterstitial.show();
            }
        }
    }




}
