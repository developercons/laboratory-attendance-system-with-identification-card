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
