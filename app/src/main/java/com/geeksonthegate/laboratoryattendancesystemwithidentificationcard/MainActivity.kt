package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val readIdButton: Button = findViewById(R.id.read_id) as Button
        readIdButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent: Intent = Intent(this@MainActivity, IdWaitingActivity::class.java)
                startActivity(intent)
            }
        })
    }
}
