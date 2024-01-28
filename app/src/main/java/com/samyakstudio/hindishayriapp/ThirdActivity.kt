package com.samyakstudio.hindishayriapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class ThirdActivity : AppCompatActivity() {

    private lateinit var title: String
    private lateinit var str: Array<String>
    private lateinit var secondList: ListView
    private lateinit var mAdView: AdView
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        secondList = findViewById(R.id.second_list)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        loadIn()
        title = intent.getStringExtra("title") ?: ""

        str = when (title) {
            "Good morning shayri" -> resources.getStringArray(R.array.goodmorning)
            "Good night shayri" -> resources.getStringArray(R.array.goodnight)
            "Love shayri" -> resources.getStringArray(R.array.loveshayri)
            "Friend shayri" -> resources.getStringArray(R.array.friend)
            "Attitude shayri" -> resources.getStringArray(R.array.attitude)
            "Emoji shayri" -> resources.getStringArray(R.array.emoji)
            "Funny shayri" -> resources.getStringArray(R.array.funny)
            "Birthday shayri" -> resources.getStringArray(R.array.birthday)
            "Romantic shayri" -> resources.getStringArray(R.array.romantic)
            "Life shayri" -> resources.getStringArray(R.array.life)
            "God shayri" -> resources.getStringArray(R.array.god)
            "Sad shayri" -> resources.getStringArray(R.array.sad)
            "Happy shayri" -> resources.getStringArray(R.array.happy)
            "Dard shayri" -> resources.getStringArray(R.array.dard)
            "Intezar shayri" -> resources.getStringArray(R.array.intezar)
            "Bewafa shayri" -> resources.getStringArray(R.array.bewafa)
            "Navratri shayri" -> resources.getStringArray(R.array.navratri)
            "New year shayri" -> resources.getStringArray(R.array.newyear)
            "Chrismas shayri" -> resources.getStringArray(R.array.chismas)
            "Utrayan shayri" -> resources.getStringArray(R.array.utrayan)
            "Republicday shayri" -> resources.getStringArray(R.array.republic)
            "Valentine shayri" -> resources.getStringArray(R.array.valentine)
            "Rakshabandhan shayri" -> resources.getStringArray(R.array.rakshabandhan)
            "Diwali shayri" -> resources.getStringArray(R.array.diwali)
            "Maa shayri" -> resources.getStringArray(R.array.maa)
            "Baap shayri" -> resources.getStringArray(R.array.baap)
            "Family shayri" -> resources.getStringArray(R.array.family)
            "Sharabi shayri" -> resources.getStringArray(R.array.sharabi)
            "Cutness shayri" -> resources.getStringArray(R.array.cutness)
            "2 line shayri" -> resources.getStringArray(R.array.line)
            "All in one" -> resources.getStringArray(R.array.allinone)
            else -> emptyArray()
        }

        val secondAdapter = SecondAdapter(this@ThirdActivity, str)
        secondList.adapter = secondAdapter

        secondList.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, i, _ ->
            val last = Intent(this@ThirdActivity, ShayriLastActivity::class.java)
            last.putExtra("shayri", str)
            last.putExtra("position", i)
            startActivity(last)
        })
    }

    private fun loadIn() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            resources.getString(R.string.in_ad_unit),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    Log.i("TAG", "onAdLoaded")
                    mInterstitialAd?.show(this@ThirdActivity) ?: Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.i("TAG", loadAdError.message)
                    mInterstitialAd = null
                }
            })
    }
}
