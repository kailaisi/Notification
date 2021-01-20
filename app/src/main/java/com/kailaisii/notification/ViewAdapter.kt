package com.kailaisii.notification

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * 描述:
 * <p/>作者：wu
 * <br/>创建时间：2021/1/12 17:29
 */
class ViewAdapter(var list: List<String>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {
        var view: View? = null
        view = LayoutInflater.from(p2.context).inflate(R.layout.item, null)
        view!!.findViewById<TextView>(R.id.textView).text = list.get(p0)
        return view
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}