package com.questappx.anniversary;

import static com.android.volley.VolleyLog.TAG;
import static com.questappx.anniversary.Billing.InApp.isPaid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;
import com.questappx.anniversary.AdsWorking.Gdpr;
import com.questappx.anniversary.AdsWorking.InterstitialAdImplement;
import com.questappx.anniversary.Billing.InApp;
import com.questappx.anniversary.Extras.AppOpenManager;
import com.questappx.anniversary.Extras.CategoryActivity;
import com.questappx.anniversary.Extras.RateItDialogFragment;
import com.questappx.anniversary.Extras.Utility;
import com.questappx.anniversary.Extras.WishesActivity;


public class MainActivity extends AppCompatActivity {
    RelativeLayout splashLayout;
    LinearLayout adsLayout;
    ImageButton createFramesBtn, shareApp, moreApp, quoteBtn, rateusBtn;
    TextView disclaimerBtn,privacyPolicy;
    ImageView premiumVersionApp;

    public static InApp inApp;

    public static AppOpenManager appOpenManager;

    public static RewardedAd mRewardedAd;

    public static InterstitialAdImplement interstitialAdImplement;
    public static InterstitialAd interstitialAd;


    ImageButton quoteapp, birthdayapp, weddingApp;


    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);
        Gdpr gdpr = new Gdpr(this);
        gdpr.setGdpr();

        SplashWorking();


//        loadRewardedAdmobAd(getApplicationContext());

        interstitialAdImplement = new InterstitialAdImplement(this, interstitialAd);
        interstitialAdImplement.loadInterstitialCall();
        interstitialAdImplement.setItemClickListener(new RecyclerListener() {
            @Override
            public void OnClick(int position) {

            }
        });


        linkXml();
        clicklisteners();


    }

    private void linkXml() {
        inApp = new InApp(this);
        splashLayout = findViewById(R.id.splashScreen);
        adsLayout = findViewById(R.id.adslayout);

        premiumVersionApp = findViewById(R.id.premiumVersionApp);

        createFramesBtn = findViewById(R.id.btn_create);
        shareApp = findViewById(R.id.btn_shareapp);
        moreApp = findViewById(R.id.btn_moreapp);
        disclaimerBtn = findViewById(R.id.tv_Disclaimer);
        quoteBtn = findViewById(R.id.btn_quote);
        rateusBtn = findViewById(R.id.btn_rateus);
        privacyPolicy = findViewById(R.id.tv_privacyPolicy);



        quoteapp = findViewById(R.id.quoteadIv);
        birthdayapp = findViewById(R.id.tictacAdIv);
        weddingApp = findViewById(R.id.weddingappTv);

        if(isPaid)
        {
            premiumVersionApp.setVisibility(View.GONE);
            adsLayout.setVisibility(View.GONE);
//            premiumVersionApp.setText("(Subscribed)");
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        if(isPaid)
        {
            premiumVersionApp.setVisibility(View.GONE);
            adsLayout.setVisibility(View.GONE);
//            premiumVersionApp.setText("(Subscribed)");
        }
    }

    public static void loadRewardedAdmobAd(Context context) {
//        RewardedAd.load(context, String.valueOf(R.string.admob_rewarded_id), new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
        RewardedAd.load(context, context.getResources().getString(R.string.admob_rewarded_id), new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                mRewardedAd = null;
//                Log.d(TAG, "Rewarded : onAdFailedToLoad");
//                Toast.makeText(context.getApplicationContext(), "Rewarded Error : " + loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("adErr", "Rewarded : " + loadAdError.getMessage());
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                super.onAdLoaded(rewardedAd);
//                Toast.makeText(context.getApplicationContext(), "Rewarded Ad loaded", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Rewarded : onAdLoaded");
                mRewardedAd = rewardedAd;
            }
        });


    }

    private void clicklisteners() {
        createFramesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

        premiumVersionApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPaid)
                {
                    Toast.makeText(MainActivity.this, "Already using Premium Version", Toast.LENGTH_SHORT).show();
                    return;
                }
                inApp.showDialog(MainActivity.this);
            }
        });

        quoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WishesActivity.class);
                startActivity(intent);
            }
        });


        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_shareapp();;
            }
        });

        moreApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_moreapps();
            }
        });

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_privacypolicy();;
            }
        });

        rateusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_rateus();
            }
        });

        disclaimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDisclaimerDialog();
            }
        });


        quoteapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localAdsLink(1);
            }
        });

        birthdayapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localAdsLink(2);
            }
        });

        weddingApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localAdsLink(3);
            }
        });
    }

    private void showDisclaimerDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.disclaimer);
        dialog.show();
        Button buttonOk = dialog.findViewById(R.id.dialogBtnOk);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void func_privacypolicy()
    {
        Uri uri = Uri.parse(Data.PrivacyPolicy);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void func_rateus()
    {
        String appLink = "https://play.google.com/store/apps/details?id=" + getPackageName();
        Uri uri = Uri.parse(appLink);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    private void func_shareapp()
    {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String shareMessage= "\nLet your Friends know that you are using this app... Install now from link below:\n\n";
            String appLink = "https://play.google.com/store/apps/details?id=" + getPackageName();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage+appLink);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void func_moreapps()
    {
        Uri uri = Uri.parse("https://play.google.com/store/apps/developer?id=Quest%20Appx");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

//    private void checkInternetConnection() {
//
//        if (internet.isConnected()) {
//            new isInternetActive().execute();
//        } else {
//            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
//        }
//
//    }

    private void checkPermissions()
    {
        Utility utility = new Utility();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Utility.checkPermissionContects(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {


                    if (Utility.checkPermissionContects(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    }
                }
            }
        }
    }



    private void SplashWorking() {
        appOpenManager = new AppOpenManager(MainActivity.this);
        appOpenManager.method = 1;


        splashLayout = findViewById(R.id.splashScreen);
        splashLayout.setVisibility(View.VISIBLE);
//        ProgressBar progressBar = findViewById(R.id.splashProgressBar);
//        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.MULTIPLY);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashLayout.setVisibility(View.GONE);
                checkPermissions();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        appOpenManager.method = 0;
                    }
                },1000);

            }
        }, 4500);
    }

    private void localAdsLink(int i)
    {
        String appLink;
        if(i == 1)
        {
            appLink = Data.WATERSORTGAME_LINK;
        }
        else if(i == 2)
        {
            appLink = Data.TicTacToeLink;
        }
        else
        {
            appLink = Data.WeddingLink;
        }

            Uri uri = Uri.parse(appLink);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        rateusDialog();
    }

    private void rateusDialog() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.rateus_dialog);
        dialog.show();
        ImageView buttonCancel = dialog.findViewById(R.id.dialogBtnCancel);
        ImageView buttonNoThanks = dialog.findViewById(R.id.dialogNoThanks);
        ImageView buttonOk = dialog.findViewById(R.id.dialogBtnSubmit);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(false);



        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                func_rateus();
                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        buttonNoThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });


    }



}
