package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.adapter.coretimeRealmAdapter
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.CoreTime
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Student
import io.realm.RealmList
import kotlinx.android.synthetic.main.activity_student_setting.*
import java.util.*

class StudentSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_setting)
/*
        val scanLabel = intent.getStringExtra("scan_label")
        val idm = intent.getStringExtra("idm")
        var coretimeArray = RealmList<CoreTime>()
        for (i in 0..6) {
            coretimeArray.add(CoreTime(GregorianCalendar(2000, 0, 1, 11, 0).time,
                    GregorianCalendar(2000, 0, 1, 11, 0).time, false))
        }
        if (scanLabel.compareTo(getString(R.string.edit))) {
            val student: Student = intent.getSerializableExtra("student") as Student
            nameBox.setText(student.name)
            studentIdBox.setText(student.studentId)
            // TODO: spinnerの選択肢を動的に設定する機能
            coretimeArray = student.lab?.coretimeArray ?: throw NullPointerException("Error")
        }
        val adapter = coretimeRealmAdapter(coretimeArray)
        coretimeList.adapter = adapter
*/
    }
}
