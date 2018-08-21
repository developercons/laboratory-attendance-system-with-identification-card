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
        val coreTimeArray = RealmList<CoreTime>()
        for (i in 0..6) {
            coreTimeArray.add(CoreTime(i, GregorianCalendar(2000, 0, 1, 11, 0).time,
                    GregorianCalendar(2000, 0, 1, 11, 0).time, false))
        }
        val lab = Lab(labName = "福田研究室", coreTimeArray = coreTimeArray)
        mRealm?.executeTransaction { it.copyToRealm(lab) }
        Toast.makeText(this, "福田研究室を追加しました", Toast.LENGTH_SHORT).show()
    }

    // カード読み取り(入退室)画面に遷移
    private fun scanStudentcartButtonTapped(view: View?) {
        val intent = Intent(this, ScanStudentcardActivity::class.java)
        intent.putExtra("scan_label", view?.id)
        startActivity(intent)
    }

}
