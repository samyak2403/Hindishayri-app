package com.samyakstudio.hindishayriapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var start: Button? = null
    private var rate: Button? = null
    private var exit: Button? = null
    private var ani: Animation? = null
    private var mInterstitialAd: InterstitialAd? = null
    private var adView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        initializeAds()
        setClickListeners()
    }

    private fun initializeViews() {
        ani = AnimationUtils.loadAnimation(this, R.anim.animation3)
        start = findViewById(R.id.start)
        rate = findViewById(R.id.rate)
        exit = findViewById(R.id.exits)
        adView = findViewById(R.id.adView)
    }

    private fun initializeAds() {
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
        loadInterstitialAd()
    }

    private fun setClickListeners() {
        start?.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }

        rate?.setOnClickListener {
            openAppInPlayStore()
        }

        exit?.setOnClickListener {
            showAlert()
        }
    }

    private fun openAppInPlayStore() {
        val appPackageName = packageName
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }

    private fun showAlert() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("Exit")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss() }
            .show()
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            getString(R.string.in_ad_unit),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    Log.i("TAG", "onAdLoaded")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.e("TAG", "Ad failed to load: ${loadAdError.message}")
                    mInterstitialAd = null
                }
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showAlert()
    }
}
