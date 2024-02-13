package com.questappx.anniversary.AdsWorking;

import static com.questappx.anniversary.Billing.InApp.isPaid;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.questappx.anniversary.Data;

public class BannerAdImplement
{


    private static final String TAG = "BannerAdImplement";

    Context context;
    AdView adView;

    LinearLayout adContainer;

    public BannerAdImplement(Context context, AdView adView) {
        this.context = context;
        this.adView = adView;
        this.adContainer = adContainer;

        BannerAdLoad();
    }
    public BannerAdImplement(Context context, AdView adView, LinearLayout adContainer) {
        this.context = context;
        this.adView = adView;
        this.adContainer = adContainer;

        BannerAdLoad();
    }

    public void BannerAdLoad()
    {
        if(isPaid)
        {
            ((ViewGroup)adView.getParent()).setVisibility(View.GONE);
            return;
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new com.google.android.gms.ads.AdListener(){

            @Override
            public void onAdLoaded() {
//                Toast.makeText( context, "Google Banner Loaded", Toast.LENGTH_SHORT).show();
                // Code to be executed when an ad finishes loading.
//                adView.setVisibility(View.VISIBLE);
//                adContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.i(TAG, "onAdFailedToLoad: "+ loadAdError.getMessage());
//                adView.setVisibility(View.GONE);
//                loadFbBanner();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

}
