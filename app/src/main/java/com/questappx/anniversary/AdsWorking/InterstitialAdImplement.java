package com.questappx.anniversary.AdsWorking;

import static com.questappx.anniversary.Billing.InApp.isPaid;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
    long lastInterstitialShowTime = 0;
    boolean activityOpenAd = false;
    public InterstitialAdImplement(Context context, InterstitialAd interstitialAd) {
        this.context = context;
        this.interstitialAd = interstitialAd;
        if(isPaid)
        {
            return;
        }

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
        if(isPaid)
        {
            return;
        }
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, context.getResources().getString(R.string.admob_interstitial), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        MainActivity.interstitialAd = interstitialAd;
                        Log.i(TAG, "Admob : onAdLoaded");

                        if (activityOpenAd) {
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
                    }


                });

    }

    public void showInterstitial()
    {
        if(isPaid) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTimeSinceLastShow = currentTime - lastInterstitialShowTime;
        long oneMinuteInMillis = 60 * 1000;

        if (MainActivity.interstitialAd != null && elapsedTimeSinceLastShow >= oneMinuteInMillis) {
            MainActivity.interstitialAd.show(((Activity) context));
            lastInterstitialShowTime = currentTime;
        }
    }




}
