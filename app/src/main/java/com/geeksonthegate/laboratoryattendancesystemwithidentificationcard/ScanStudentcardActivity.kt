package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Student
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_scan_studentcard.*
import java.util.*

class ScanStudentcardActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    /**
     * NFCアダプタのインスタンスを格納するプロパティ
     */
    private lateinit var nfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_studentcard)
        realm = Realm.getDefaultInstance()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        realm = Realm.getDefaultInstance()

        val id = intent.getIntExtra("scan_label", 0)
        // 前画面で押されたボタンに応じてラベルの内容を変更
        when (id) {
            R.id.enter -> {
                scanCardLabel.setText(R.string.enter_label)
                setTitle(R.string.enter)
                background.setImageResource(R.drawable.bg_enter)
            }
            R.id.exit -> {
                scanCardLabel.setText(R.string.exit_label)
                setTitle(R.string.exit)
                background.setImageResource(R.drawable.bg_exit)
            }
            R.id.register -> {
                scanCardLabel.setText(R.string.register_label)
                setTitle(R.string.register)
            }
            R.id.edit -> {
                scanCardLabel.setText(R.string.edit_label)
                setTitle(R.string.edit)
            }
        }

        // NFCアダプタのインスタンスを生成
        nfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this)
    }

    override fun onResume() {
        super.onResume()

        // NFCがかざされたとき、このActivityに読み込まれるようにする
        val intent = Intent(this, ScanStudentcardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()

        // このアプリが前面にない時はNFCがかざされても反応しないようにする
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val nextIntent = Intent(this, RoomConfirmationActivity::class.java)

        // NFCのEXTRA_IDを読み込み、前画面で押されたボタンと共に表示する
        val idm: ByteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: run {
            Toast.makeText(this, "Failed to read NFC", Toast.LENGTH_SHORT).show()
            return
        }
        val scanStudent = realm.where<Student>().contains("idm", Arrays.toString(idm)).findFirst()
        Toast.makeText(this, scanStudent.toString(), Toast.LENGTH_SHORT).show()

        nextIntent.putExtra("scan_label", title)
        val id = intent.getIntExtra("scan_label", 0)
        when (id) {
            R.id.enter -> {
                nextIntent.putExtra("scan_student", scanStudent)
            }
            R.id.exit -> {
                nextIntent.putExtra("scan_student", scanStudent)
            }
            R.id.register -> {
                nextIntent.putExtra("idm", idm)
            }
            R.id.edit -> {
                nextIntent.putExtra("idm", idm)
                nextIntent.putExtra("scan_student", scanStudent)
            }
        }
        startActivity(nextIntent)

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
