package com.questappx.anniversary.Extras;

import static com.questappx.anniversary.MainActivity.appOpenManager;
import static com.questappx.anniversary.MainActivity.interstitialAdImplement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.questappx.anniversary.AdsWorking.BannerAdImplement;
import com.questappx.anniversary.Data;
import com.questappx.anniversary.QuoteAdapter;
import com.questappx.anniversary.QuoteListener;
import com.questappx.anniversary.R;

import java.util.ArrayList;
import java.util.Random;

public class WishesActivity extends AppCompatActivity {

    RecyclerView  recyclerViewQuotes;
    ArrayList<String>  quoteList;
    QuoteAdapter quoteAdapter;
    public String TAG = "err";

    int activityOpenAd = 0;

    public String[] Quotes = {
            "Once in awhile, right in the middle of an ordinary life, love gives us a fairy tale.",
            "And I knew exactly how old Walt Disney’s Cinderella felt when she found her prince.",
            "A hundred hearts would be too few to carry all my love for you.",
            "In the arithmetic of love, one plus one equals everything, and two minus one equals nothing.",
            "The highest happiness on earth is the happiness of marriage.",
            "Nobody has ever measured, even poets, how much a heart can hold.",
            "Gravitation is not responsible for people falling in love.",
            "Oh the heart that has truly loved never forgets, But as truly loves on to the close.",
            "Doubt thou the stars are fire; Doubt that the sun doth move; Doubt truth to be a liar; But never doubt I love.",
            "I would rather share one lifetime with you than face all the ages of this world alone.",
            "Being someone’s first love may be great, but to be their last is beyond perfect.",
            "Keep love in your heart. A life without it is like a sunless garden when the flowers are.",
            "You are every reason, every hope and every dream I’ve ever had.",
            "True love stories never have endings.",
            "You don’t love someone for their looks, or their clothes or their fancy car, but because they sing a song only you can hear.",
            "If we look at the world with a love of life, the world will reveal its beauty to us.",
            "Soul meets soul on lovers’ lips.",
            "What lies behind us, and what lies before us are tiny matters compared to what lies within us.",

// Funny


            "Marriage has no guarantees. If that’s what you’re looking for, go live with a car battery.",
            "Real love amounts to withholding the truth, even when you’re offered the perfect opportunity to hurt someone’s feelings.",
            "Only married people understand you can be miserable and happy at the same time.",
            "The groom always smiles proudly because he’s convinced he’s accomplished something quite wonderful. The bride smiles because she’s been able to convince him of it.",
            "A good marriage is like a casserole: only those responsible for it really know what goes in it.",
            "Marriage is like twirling a baton, turning a handspring, or eating with chopsticks: It looks easy until you try it.",
            "Romance is the icing, but love is the cake.",
            "Love is a lot like a backache, it doesn’t show up on X-rays, but you know it’s there. ",
            "I love you more than coffee, but please don’t make me prove it. ",
            "All my friends are getting married. I guess I’m just at that age where people give up.",
            "You can’t put a price tag on love. But if you could, I’d wait for it to go on sale.",
            "I love being married. It’s so great to find one special person you want to annoy for the rest of your life.",
            "Being a good husband is like being a stand-up comic. You need 10 years before you can call yourself a beginner.",
            "If you can stay in love for more than two years, you’re on something.",
            "The ideal husband understands every word his wife doesn’t say.",
            "A happy marriage is the union of two good forgivers.",


//Wise

            "When you look for the right person, you always end up with the wrong one. But when you just sit by the corner and wait, he comes along and shares the corner with you.",
            "You know when you have found your prince because you not only have a smile on your face, but in your heart as well.",
            "Love is life. And if you miss love, you miss life.",
            "Being deeply loved by someone gives you strength, while loving someone deeply gives you courage.",
            "You know you’re in love when you can’t fall asleep because reality is finally better than your dreams.",
            "Grief can take care of itself, but to get the full value of joy, you must have somebody to divide it with.",
            "You will find, as you look back upon your life, that the moments when you have truly lived are the moments when you have done things in the spirit of love.",
            "Love doesn’t make the world go round. Love is what makes the ride worthwhile.",
            "In dreams and in love there are no impossibilities.",
            "A successful marriage requires falling in love many times, always with the same person.",
            "A good marriage is one which allows for change and growth in the individuals and in the way they express their love.",
            "The greatest marriages are built on teamwork. A mutual respect, a healthy dose of admiration, and a never-ending portion of love and grace.",
            "There is no such thing as a perfect man or a perfect marriage. But the one I have is perfect for me.",
            "Remember, we all stumble; every one of us. That is why it’s a comfort to go hand in hand.",
            "The beauty of marriage is not always seen from the very beginning—but rather as love grows and develops over time.",
            "A marriage is not a noun; it’s a verb. It isn’t something you get. It’s the way you love your partner everyday.",
            "To love or have loved, that is enough. Ask nothing more.",
            "You don’t marry the person you can live with—you marry the person you can’t live without.",
            "You should be kissed and often, and by someone who knows how.",
            "Tis better to have loved and lost, Than never to have loved at all.",
            "A happy marriage is a long conversation which always seems too short.",

//Heart Felt

            "For you see, each day I love you more. Today more than yesterday and less than tomorrow.",
            "I love you, not only for what you are, but for what I am when I am with you. I love you, not only for what you have made of yourself, but for what you are making of me.",
            "Love recognizes no barriers. It jumps hurdles, leaps fences, penetrates walls to arrive at its destination full of hope.",
            "Walking with your hands in mine and mine in yours, that’s exactly where I want to be always.",
            "Two souls with but a single thought; two hearts that beat as one.",
            "Whatever our souls are made of, his and mine are the same.",
            "Grow old along with me; the best is yet to be.",
            "If you live to be a hundred, I want to live to be a hundred minus one day, so I never have to live without you.",
            "True love is never blind, but rather brings an added light.",
            "In all the world, there is no heart for me like yours. In all the world, there is no love for you like mine.",
            "If I loved you less, I might be able to talk about it more.",
            "And think not you can direct the course of love, for love, if it finds you worthy, directs your course.",
            "I believe love is always eternal. Even if eternity is only five minutes.",
            "Love is not about staring at each other, but staring off in the same direction.",
            "Your wide eyes are the only light I know from extinguished constellations.",
            "My love for you is a journey, starting at forever and ending at never.",
            "We loved with a love that was more than love.",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishes);

        init();

        if(interstitialAdImplement != null)
        {
            interstitialAdImplement.showInterstitial();
        }
        else {
            if(appOpenManager != null)
            {
                appOpenManager.showAdIfAvailable();
            }
        }
    }

    private void init()
    {
        recyclerViewQuotes = findViewById(R.id.recyclerview_quotes);
        recyclerViewQuotes.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(WishesActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewQuotes.setLayoutManager(linearLayoutManager1);

        quoteList = new ArrayList<>();
        for(int i=0;i< Quotes.length;i++)
        {
            quoteList.add(""+Quotes[i]);
        }

        quoteAdapter = new QuoteAdapter(WishesActivity.this, quoteList, new QuoteListener() {
            @Override
            public void onImageSwitched() {
                showRandomAd(1);
            }

            @Override
            public void onImageSaved() {
                showRandomAd(5);

            }
        });
        recyclerViewQuotes.setAdapter(quoteAdapter);

    }




    private void showRandomAd(int bound) {
        Random random = new Random();
        int rand = random.nextInt(bound);
        if(rand == 0)
        {
            if(interstitialAdImplement != null)
            {
                interstitialAdImplement.showInterstitial();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


}