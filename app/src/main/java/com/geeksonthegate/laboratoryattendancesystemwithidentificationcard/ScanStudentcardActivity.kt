package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.geeksonthegate.laboratoryattendancesystemwithidentificationcard.model.Student
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_scan_studentcard.*

class ScanStudentcardActivity : AppCompatActivity() {

    /**
     * NFCアダプタのインスタンスを格納するプロパティ
     */
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_studentcard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        realm = Realm.getDefaultInstance()
        // 前画面で押されたボタンに応じてラベルの内容を変更
        val id = intent.getIntExtra("scan_label", 0)
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
        }

        // NFCアダプタのインスタンスを生成
        nfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
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
        val uid: ByteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: run {
            Toast.makeText(this, "Failed to read NFC", Toast.LENGTH_SHORT).show()
            return
        }
        // TODO:汚いstringを直す
        val scanStudent =  realm.where<Student>().contains("idm",uid.toString()).findFirst()
        Toast.makeText(this, scanStudent.toString(), Toast.LENGTH_SHORT).show()


        // 次に表示するActivityへnfc_idと前画面に押されたボタンを送る
        nextIntent.putExtra("scan_student", scanStudent)
        nextIntent.putExtra("scan_label", title)
        startActivity(nextIntent)

    }
}
