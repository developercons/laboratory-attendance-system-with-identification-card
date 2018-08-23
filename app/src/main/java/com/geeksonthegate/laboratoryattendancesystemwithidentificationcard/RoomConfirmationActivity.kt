package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.R.id.textView8
import kotlinx.android.synthetic.*
import java.util.*

class RoomConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_confirmation)

        val id = intent.getStringExtra("scan_label")
        when (id) {
            getString(R.string.enter) -> {
                setTitle(R.string.enter)
            }
            getString(R.string.exit) -> {
                setTitle(R.string.exit)
            }
        }
        
        val cal = Calendar.getInstance()
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        val minutes: Int = cal.get(Calendar.MINUTE)
        //Toast.makeText(this,minutes.toString(),Toast.LENGTH_SHORT).show()
        val textView: TextView = findViewById(R.id.textView8)
        textView.text = hour.toString() + ":" + minutes.toString()
        //Toast.makeText(this,  title, Toast.LENGTH_SHORT).show()
    }

    //画面がタッチされるとMainActivityに遷移する
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
        return true
    }
}

