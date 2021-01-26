package com.NewsNow.newsnowMa;

import android.content.Intent;
import android.util.Log;

import com.NewsNow.newsnowMa.ApiService.MainActivity;
import com.daimajia.androidanimations.library.Techniques;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {

    private InterstitialAd mInterstitialAd;

    @Override
    public void initSplash(ConfigSplash configSplash) {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        //under initialize
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1377508661904967/9073144270");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.purble); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH;

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.news); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash.setTitleSplash("News Now");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(35f); //float value
//        configSplash.setTitleFont("font/bungee.ttf");
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.DropOut);

    }

    @Override
    public void animationsFinished() {

        if (mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        } else {
            openActivity();
        }

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                Log.i("TAG",adError.toString());
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                openActivity();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
    private void openActivity()
    {
      
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}