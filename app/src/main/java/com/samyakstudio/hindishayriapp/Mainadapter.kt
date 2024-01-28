package com.samyakstudio.hindishayriapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.TextView

class Mainadapter(private val secondPage: SecondActivity, private val title: Array<String>) : BaseAdapter() {

    private var ani: Animation? = null

    override fun getCount(): Int {
        return title.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(secondPage).inflate(R.layout.view, parent, false)
        }

        ani = AnimationUtils.loadAnimation(secondPage, R.anim.animation1)
        val textView: TextView = view!!.findViewById(R.id.textview)
        textView.text = title[position]
        textView.animation = ani

        return view
    }
}



