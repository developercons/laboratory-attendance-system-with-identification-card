package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.adapter

import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.R
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.coretime
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter
import kotlinx.android.synthetic.main.coretime_row.view.*
import java.lang.IllegalStateException
import java.text.SimpleDateFormat

class coretimeRealmAdapter(@Nullable data: OrderedRealmCollection<coretime>) : RealmBaseAdapter<coretime>(data) {
    companion object {
        class ViewHolder(var day: TextView,
                         var startcoretime: EditText,
                         var endcoretime: EditText,
                         var isCoreDay: CheckBox)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(
                R.layout.coretime_row, parent, false).apply {
            this.tag = ViewHolder(this.dayLabel, this.startcoretimeBox,
                    this.endcoretimeBox, this.isCoreDayBox)
        }
        val viewHolder = view.tag as ViewHolder
        val sdf = SimpleDateFormat("HH:dd")
        val coretime = adapterData?.get(position) ?: throw IllegalStateException("Error")
        viewHolder.day.setText(when (position) {
            0 -> R.string.monday
            1 -> R.string.tuesday
            2 -> R.string.wednesday
            3 -> R.string.thursday
            4 -> R.string.friday
            5 -> R.string.saturday
            6 -> R.string.sunday
            else -> throw IllegalStateException("Error")
        })
        viewHolder.startcoretime.setText(sdf.format(coretime.endcoretime))
        viewHolder.endcoretime.setText(sdf.format(coretime.endcoretime))
        viewHolder.isCoreDay.isChecked = coretime.isCoreDay ?: false
        return view as View
    }
}
