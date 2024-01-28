package com.samyakstudio.hindishayriapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

class ShayriLastActivity : AppCompatActivity() {

    private var pos: Int = 0
    private var shayri: Array<String>? = null
    private lateinit var textView: TextView
    private lateinit var next: Button
    private lateinit var previous: Button
    private lateinit var share: Button
    private lateinit var copy: Button
    private lateinit var builder: AlertDialog.Builder
    private lateinit var animation: Animation
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shayri_last)

        animation = AnimationUtils.loadAnimation(this, R.anim.animation2)
        textView = findViewById(R.id.shayri_last_view)
        builder = AlertDialog.Builder(this)
        next = findViewById(R.id.next)
        previous = findViewById(R.id.previous)
        share = findViewById(R.id.share)
        copy = findViewById(R.id.copy)

        pos = intent.getIntExtra("position", 0)
        shayri = intent.getStringArrayExtra("shayri")

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView?.loadAd(adRequest)

        textView.text = shayri?.get(pos)
        textView.startAnimation(animation)

        previous.setOnClickListener {
            pos--
            try {
                textView.startAnimation(animation)
                textView.text = shayri?.get(pos)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        next.setOnClickListener {
            pos++
            try {
                textView.startAnimation(animation)
                textView.text = shayri?.get(pos)
            } catch (e: Exception) {
            }
        }

        copy.setOnClickListener {
            val ss = textView.text.toString()
            val clipbord = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("test", ss)
            clipbord.setPrimaryClip(clip)
            showtoast()
        }

        share.setOnClickListener {
            val ss = textView.text.toString()
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n$ss\n\n"
                shareMessage =
                    "$shareMessage https://play.google.com/store/apps/details?id=com.arrowwould.hindishayriapp\n\n download this"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
            }
        }
    }

    private fun showtoast() {
        val inflater: LayoutInflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.toast_layout, findViewById(R.id.toast) as? ViewGroup)
            ?: return  // Handle the case when findViewById returns null
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()
    }
}
