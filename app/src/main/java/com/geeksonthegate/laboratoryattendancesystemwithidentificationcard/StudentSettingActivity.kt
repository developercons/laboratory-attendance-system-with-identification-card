package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.adapter.coretimeRealmAdapter
=======
import android.text.format.DateFormat
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
>>>>>>> 追加: 学生情報登録/編集画面で情報を取得・生成し表示する機能
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.CoreTime
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Student
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_student_setting.*
import java.util.*

class StudentSettingActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var startCoreTimeLabelList: List<TextView>
    private lateinit var endCoreTimeLabelList: List<TextView>
    private lateinit var isCoreDayBoxList: List<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_setting)

        startCoreTimeLabelList = listOf<TextView>(monday_start_core_time_label,
                tuesday_start_core_time_label, wednesday_start_core_time_label,
                thursday_start_core_time_label, friday_start_core_time_label,
                saturday_start_core_time_label, sunday_start_core_time_label)
        endCoreTimeLabelList = listOf<TextView>(monday_end_core_time_label,
                tuesday_end_core_time_label, wednesday_end_core_time_label,
                thursday_end_core_time_label, friday_end_core_time_label,
                saturday_end_core_time_label, sunday_end_core_time_label)
        isCoreDayBoxList = listOf<CheckBox>(is_monday_core_day_box,
                is_tuesday_core_day_box, is_wednesday_core_day_box,
                is_thursday_core_day_box, is_friday_core_day_box,
                is_saturday_core_day_box, is_sunday_core_day_box)
        realm = Realm.getDefaultInstance()
        val scanLabel = intent.getStringExtra("scan_label")
        val idm = intent.getStringExtra("idm")
        var student: Student? = realm.where(Student::class.java).equalTo("idm", idm).findFirst()
        val coreTimeList = mutableListOf<CoreTime>()
        for (i in 0..6) {
            coreTimeList.add(CoreTime(
                    GregorianCalendar(
                            2000, 0, 1, 10, 0).time,
                    GregorianCalendar(
                            2000, 0, 1, 17, 0).time,
                    true))
        }

        when (scanLabel) {
            getString(R.string.register) -> {
                when (student) {
                    null -> {
                        setCoreTimeArea(coreTimeList)
                    }
                    else -> Toast.makeText(this,
                            "ここで登録済みモーダルを表示", Toast.LENGTH_SHORT).show()
                }
            }
            getString(R.string.edit) -> {
                when (student) {
                    null -> Toast.makeText(this,
                            "ここで未登録モーダルを表示", Toast.LENGTH_SHORT).show()
                    else -> {
                        setCoreTimeArea(student.lab?.coreTimeArray ?: coreTimeList)
                        name_entry.setText(student.name)
                        student_id_entry.setText(student.studentId)
                        // TODO: spinnerはまだ全く触ってない
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun setCoreTimeArea(coreTimeList: List<CoreTime>) {
        for (i in 0..6) {
            startCoreTimeLabelList[i].text = DateFormat.format("kk:mm", coreTimeList[i].startCoreTime)
            endCoreTimeLabelList[i].text = DateFormat.format("kk:mm", coreTimeList[i].endCoreTime)
            isCoreDayBoxList[i].isChecked = coreTimeList[i].isCoreDay ?: true
        }
    }
}
