package com.questappx.anniversary.AdsWorking;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.questappx.anniversary.Data;

public class BannerAdImplement
{


    private static final String TAG = "BannerAdImplement";

    Context context;
    AdView adView;
    com.facebook.ads.AdView fbAdview;

    LinearLayout adContainer;

    public BannerAdImplement(Context context, AdView adView) {
        this.context = context;
        this.adView = adView;
        this.fbAdview = fbAdview;
        this.adContainer = adContainer;

        BannerAdLoad();
    }
    public BannerAdImplement(Context context, AdView adView, com.facebook.ads.AdView fbAdview, LinearLayout adContainer) {
        this.context = context;
        this.adView = adView;
        this.fbAdview = fbAdview;
        this.adContainer = adContainer;

        BannerAdLoad();
    }

    public void BannerAdLoad()
    {
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

    private void loadFbBanner()
    {
//        adContainer.setVisibility(View.VISIBLE);

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
//                    Toast.makeText(
//                                    EditorActivity.this,
//                                    "Error: " + adError.getErrorMessage(),
//                                    Toast.LENGTH_LONG)
//                            .show();
                Log.d(TAG, "onError Banner");
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Ad loaded callback
                Log.d(TAG, "onAdLoaded Banner");
//                adContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "onAdClicked Banner");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "onLogging Impression Banner");
            }
        };


        fbAdview = new com.facebook.ads.AdView(context, Data.FB_BANNER_ID, AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container

        // Add the ad view to your activity layout
        adContainer.addView(fbAdview);

        // Request an ad
        fbAdview.loadAd(fbAdview.buildLoadAdConfig().withAdListener(adListener).build());
        //        adView.loadAd();
    }
}
