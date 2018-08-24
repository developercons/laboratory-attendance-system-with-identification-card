package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Lab

class LabAdapter(context: Context, resource: Int, objects: List<Lab>) : ArrayAdapter<Lab>(
        context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = super.getView(position, convertView, parent) as TextView
        val lab = getItem(position)
        textView.text = lab.labName
        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        val lab = getItem(position)
        textView.text = lab.labName
        return textView
    }
}
