package com.questappx.anniversary.Extras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.questappx.anniversary.AdsWorking.AdClass;
import com.questappx.anniversary.AdsWorking.LocalAd;
import com.questappx.anniversary.Data;
import com.questappx.anniversary.EditorActivity;
import com.questappx.anniversary.R;

public class CategoryActivity extends AppCompatActivity {

    TextView squareFramesBtn, portraitFramesBtn, invitationCard, localAppName;

    RelativeLayout localAdLayout;

    String adToLoadLink;
    ImageView localAppIcon, localAppFeature;
    AdClass adClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        linkXml();

        clickListener();

    }

    private void clickListener() {
        squareFramesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, EditorActivity.class);
                intent.putExtra("frameCategory", 1);
                startActivity(new Intent(intent));
            }
        });

        portraitFramesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, EditorActivity.class);
                intent.putExtra("frameCategory", 2);
                startActivity(new Intent(intent));
            }
        });

//         adToLoadLink = Data.WeddingLink;
        localAdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localAd(adToLoadLink);
            }
        });

        invitationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, EditorActivity.class);
                intent.putExtra("frameCategory", 3);
                startActivity(new Intent(intent));
            }
        });
    }

    private void linkXml() {
        squareFramesBtn = findViewById(R.id.squareFramesBtn);
        portraitFramesBtn = findViewById(R.id.portraitFramesBtn);
        invitationCard = findViewById(R.id.invitaionCardBtn);

        localAdLayout = findViewById(R.id.localAdLayout);
        localAppName = findViewById(R.id.localAdText);
        localAppIcon = findViewById(R.id.localAdIcon);
        localAppFeature = findViewById(R.id.feature_place);

        setRandomLocalAd();
    }

    private void setRandomLocalAd()
    {
        LocalAd ad = new LocalAd();
        adClass = ad.getRandom();

        localAppName.setText(adClass.getAppName());
        localAppIcon.setImageResource(adClass.getDrawable());
        localAppFeature.setBackgroundResource(adClass.getFeature());
        adToLoadLink = adClass.getLink();


    }

    private void localAd(String link)
    {
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}