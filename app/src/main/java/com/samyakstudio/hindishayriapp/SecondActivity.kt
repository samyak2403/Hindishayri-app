package com.samyakstudio.hindishayriapp
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class SecondActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var title: Array<String>
    private lateinit var ani: Animation
    private lateinit var mAdView: AdView
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        listView = findViewById(R.id.listview)
        title = resources.getStringArray(R.array.title)
        val adapter = Mainadapter(this@SecondActivity, title)

        ani = AnimationUtils.loadAnimation(this, R.anim.animation1)

        listView.adapter = adapter

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        loadIn()

        listView.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, i, _ ->
            val secondIntent = Intent(this@SecondActivity, ThirdActivity::class.java)
            secondIntent.putExtra("title", title[i])
            startActivity(secondIntent)
        })
    }

    fun loadIn() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this, getString(R.string.in_ad_unit), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd
                Log.i(TAG, "onAdLoaded")

                if (mInterstitialAd != null) {
                    mInterstitialAd!!.show(this@SecondActivity)

                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                // Handle the error

                Log.i(TAG, loadAdError.message)
                mInterstitialAd = null
            }
        })
    }
}