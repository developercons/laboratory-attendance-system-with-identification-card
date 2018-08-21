package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mRealm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRealm = Realm.getDefaultInstance()

        enter.setOnClickListener { scanStudentcartButtonTapped(it) }
        exit.setOnClickListener { scanStudentcartButtonTapped(it) }

        // 以降、テスト用ダミーデータの登録
        // 研究室
        val coreTimeArray = RealmList<CoreTime>()
        for (i in 0..6) {
            coreTimeArray.add(CoreTime(i, GregorianCalendar(2000, 0, 1, 11, 0).time,
                    GregorianCalendar(2000, 0, 1, 11, 0).time, false))
        }
        val labArray = mutableListOf<Lab>()
        labArray.add(Lab(labName = "福田研究室", coreTimeArray = coreTimeArray))
        labArray.add(Lab(labName = "菅谷研究室", coreTimeArray = coreTimeArray))
        labArray.add(Lab(labName = "宇佐美研究室", coreTimeArray = coreTimeArray))
        labArray.add(Lab(labName = "平川研究室", coreTimeArray = coreTimeArray))
        mRealm?.executeTransaction { for (item in labArray) it.insertOrUpdate(item) }
        Toast.makeText(this, "研究室のダミーデータを追加しました", Toast.LENGTH_SHORT).show()

        // 学生
        val studentArray = mutableListOf<Student>()
        studentArray.add(Student(listOf<Byte>(1, 16, 14, 0, 60, 20, -31, 0).toString(), "AL15026", "岸本太郎", Lab(labName = "菅谷研究室", coreTimeArray = coreTimeArray)))
        studentArray.add(Student(listOf<Byte>(1, 16, 8, 0, 60, 20, -99, 1).toString(), "AL15082", "迫真古川先輩", Lab(labName = "平川研究室", coreTimeArray = coreTimeArray)))
        studentArray.add(Student(listOf<Byte>(1, 18, 3, 18, -45, 24, 34, 28).toString(), "AL15065", "Gitの鬼", Lab(labName = "福田研究室", coreTimeArray = coreTimeArray)))
        mRealm?.executeTransaction { for (item in studentArray) it.insertOrUpdate(item) }
        Toast.makeText(this, "学生のダミーデータを追加しました", Toast.LENGTH_SHORT).show()

        // 入退出ログ
        val logArray = mutableListOf<AttendanceLog>()
        logArray.add(AttendanceLog(UUID.randomUUID().toString(), listOf<Byte>(1, 16, 8, 0, 60, 20, -99, 1).toString(), GregorianCalendar(2018, 8, 20, 11, 0).time, GregorianCalendar(2018, 8, 20, 18, 0).time))
        logArray.add(AttendanceLog(UUID.randomUUID().toString(), listOf<Byte>(1, 16, 8, 0, 60, 20, -99, 1).toString(), GregorianCalendar(2018, 8, 20, 11, 0).time, GregorianCalendar(2018, 8, 20, 18, 0).time))
        logArray.add(AttendanceLog(UUID.randomUUID().toString(), listOf<Byte>(1, 18, 3, 18, -45, 24, 34, 28).toString(), GregorianCalendar(2018, 8, 20, 11, 0).time, GregorianCalendar(2018, 8, 20, 18, 0).time))
        mRealm?.executeTransaction { for (item in logArray) it.insertOrUpdate(item) }
        Toast.makeText(this, "ログのダミーデータを追加しました", Toast.LENGTH_SHORT).show()

    }

    // カード読み取り(入退室)画面に遷移
    private fun scanStudentcartButtonTapped(view: View?) {
        val intent = Intent(this, ScanStudentcardActivity::class.java)
        intent.putExtra("scan_label", view?.id)
        startActivity(intent)
    }

}
