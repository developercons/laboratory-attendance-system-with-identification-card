package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.R.id.*
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Lab
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Student
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.*
import java.util.*

class RoomConfirmationActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_confirmation)
        realm = Realm.getDefaultInstance()

        val id = intent.getStringExtra("scan_label")
        when (id) {
            getString(R.string.enter) -> {
                setTitle(R.string.enter)
            }
            getString(R.string.exit) -> {
                setTitle(R.string.exit)
            }
        }
        val idm: ByteArray = intent.getByteArrayExtra("idm")
        //Toast.makeText(this,Arrays.toString(idm),Toast.LENGTH_SHORT).show()
        val student = realm.where<Student>().contains("idm", Arrays.toString(idm)).findFirst()
        //val student: Student? = scanStudent
        val lab: String? = student?.lab?.labName
        val name: String? = student?.name
        val studentId: String? = student?.studentId

        //Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show()
        val cal = Calendar.getInstance()
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        val minutes: Int = cal.get(Calendar.MINUTE)
        //Toast.makeText(this,minutes.toString(),Toast.LENGTH_SHORT).show()

        val lab_view: TextView = findViewById(R.id.lab)
        val name_view: TextView = findViewById(R.id.name)
        val studentId_view: TextView = findViewById(R.id.studentId)
        val currentTime_view: TextView = findViewById(R.id.currentTime)

        lab_view.text=lab.toString()
        name_view.text=name.toString()
        studentId_view.text=studentId.toString()
       // lab_view.text=lab.toString()
        currentTime_view.text = hour.toString() + ":" + minutes.toString()
        //textView.text = scanStudent.toString()
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

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}