package com.samyakstudio.hindishayriapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.BaseAdapter
import android.widget.TextView

class SecondAdapter(private val thirdPage: ThirdActivity, private val str: Array<String>) : BaseAdapter() {

    private var animation: Animation? = null

    override fun getCount(): Int {
        return str.size
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
            view = LayoutInflater.from(thirdPage).inflate(R.layout.shayri_view, parent, false)
        }

        animation = AnimationUtils.loadAnimation(thirdPage, R.anim.animation1)
        val textView: TextView = view!!.findViewById(R.id.last_view)
        textView.text = str[position]
        textView.animation = animation

        return view
    }
}