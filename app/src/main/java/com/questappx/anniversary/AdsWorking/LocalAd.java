package com.questappx.anniversary.AdsWorking;

import com.questappx.anniversary.R;

import java.util.Random;


public class LocalAd {


    int[] appIcon = {R.drawable.watersort_icon, R.drawable.tictac_appicon, R.drawable.wedding_icon, R.drawable.menu_icon};
     String[] appName = {"Color Sort - Puzzle Game",
            "Tic Tac Toe - Puzzle Game",
            "Wedding Photo Frame",
            "Menu Design Maker",
            };
    int[] appFeature = {
            R.drawable.watersort_feature,
            R.drawable.ttt_feature,
            R.drawable.feature_wedding,
            R.drawable.menu_feature
    };
    String[] appLinks = {"https://play.google.com/store/apps/details?id=com.questappx.watersort.puzzle.games",
            "https://play.google.com/store/apps/details?id=com.questappx.tictactoe.glow.puzzle.games",
            "https://play.google.com/store/apps/details?id=com.questappx.wedding.framemaker",
            "https://play.google.com/store/apps/details?id=com.QuestAppx.MenuDesigner.MenuMaker"
    };

    public AdClass getRandom()
    {
        Random random = new Random();
        int rand = random.nextInt(appLinks.length);
        return new AdClass(appLinks[rand],appIcon[rand],appFeature[rand],appName[rand]);
    }
}

