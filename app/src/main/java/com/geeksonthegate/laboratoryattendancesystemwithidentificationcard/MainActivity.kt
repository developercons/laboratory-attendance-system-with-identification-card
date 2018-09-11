package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.AttendanceLog
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.CoreTime
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Lab
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Student
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()

        // 以降、テスト用ダミーデータの登録
        // 研究室
        val coretimeArray = RealmList<CoreTime>()
        for (i in 0..6) {
            coretimeArray.add(CoreTime(GregorianCalendar(2000, 0, 1, 10, 0).time,
                    GregorianCalendar(2000, 0, 1, 17, 0).time, false))
        }
        val labArray = mutableListOf<Lab>()
        try {
            labArray.add(Lab(labName = "福田研究室", coretimeArray = coretimeArray))
            labArray.add(Lab(labName = "菅谷研究室", coretimeArray = coretimeArray))
            labArray.add(Lab(labName = "宇佐美研究室", coretimeArray = coretimeArray))
            labArray.add(Lab(labName = "平川研究室", coretimeArray = coretimeArray))
        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
        } finally {
            if (labArray.size != 0) {
                realm?.executeTransaction { for (item in labArray) it.insertOrUpdate(item) }
            }
        }

        // 学生
        val studentArray = mutableListOf<Student>()
        try {
            studentArray.add(Student(listOf<Byte>(1, 16, 14, 0, 60, 20, -31, 0).toString(), "AL15026", "岸本太郎", Lab(labName = "菅谷研究室", coretimeArray = coretimeArray)))
            studentArray.add(Student(listOf<Byte>(1, 16, 8, 0, 60, 20, -99, 1).toString(), "AL15082", "迫真古川先輩", Lab(labName = "平川研究室", coretimeArray = coretimeArray)))
        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
        } finally {
            if (studentArray.size != 0) {
                realm?.executeTransaction { for (item in studentArray) it.insertOrUpdate(item) }
            }
        }
        realm?.executeTransaction { for (item in studentArray) it.insertOrUpdate(item) }

        // 入退出ログ
        val logArray = mutableListOf<AttendanceLog>()
        logArray.add(AttendanceLog(listOf<Byte>(1, 16, 8, 0, 60, 20, -99, 1).toString(), GregorianCalendar(2018, 8, 20, 11, 0).time, GregorianCalendar(2018, 8, 20, 18, 0).time))
        logArray.add(AttendanceLog(listOf<Byte>(1, 16, 8, 0, 60, 20, -99, 1).toString(), GregorianCalendar(2018, 8, 20, 11, 0).time, GregorianCalendar(2018, 8, 20, 18, 0).time))
        logArray.add(AttendanceLog(listOf<Byte>(1, 18, 3, 18, -45, 24, 34, 28).toString(), GregorianCalendar(2018, 8, 20, 11, 0).time, GregorianCalendar(2018, 8, 20, 18, 0).time))
        realm?.executeTransaction { for (item in logArray) it.insertOrUpdate(item) }

        enter.setOnClickListener { scanStudentcardButtonTapped(it) }
        exit.setOnClickListener { scanStudentcardButtonTapped(it) }
        register.setOnClickListener { scanStudentcardButtonTapped(it) }
        edit.setOnClickListener { scanStudentcardButtonTapped(it) }
        history.setOnClickListener {}
    }

    // カード読み取り(入退室)画面に遷移
    private fun scanStudentcardButtonTapped(view: View?) {
        val intent = Intent(this, ScanStudentcardActivity::class.java)
        intent.putExtra("scan_label", view?.id)
        startActivity(intent)
    }

}
