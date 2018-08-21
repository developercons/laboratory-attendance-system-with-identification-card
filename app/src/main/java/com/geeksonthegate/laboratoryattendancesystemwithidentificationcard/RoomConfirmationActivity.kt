package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.MotionEvent
import android.view.View

class RoomConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_confirmation)

        val id = intent.getIntExtra("scan_label",0)
        when (id) {
            R.id.enter -> {
                setTitle(R.string.enter)
            }
            R.id.exit -> {
                setTitle(R.string.exit)

            }
        }
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return super.onTouchEvent(event)
    }
}
