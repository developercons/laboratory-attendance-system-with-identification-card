package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_scan_studentcard.*
import java.util.*

class ScanStudentcardActivity : AppCompatActivity() {

    /**
     * NFCアダプタのインスタンスを格納するプロパティ
     */
    private lateinit var mNfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_studentcard)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 前画面で押されたボタンに応じてラベルの内容を変更
        val id = intent.getIntExtra("scan_label", 0)
        when (id) {
            R.id.enter -> {
                scanCardLabel.setText(R.string.enter_label)
                setTitle(R.string.enter)
            }
            R.id.exit -> {
                scanCardLabel.setText(R.string.exit_label)
                setTitle(R.string.exit)
            }
        }

        // NFCアダプタのインスタンスを生成
        mNfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this)
    }

    override fun onResume() {
        super.onResume()

        // NFCがかざされたとき、このActivityに読み込まれるようにする
        val intent = Intent(this, ScanStudentcardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()

        // このアプリが前面にない時はNFCがかざされても反応しないようにする
        mNfcAdapter.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        val nextIntent = Intent(this, RoomConfirmationActivity::class.java)

        // NFCのEXTRA_IDを読み込み、前画面で押されたボタンと共に表示する
        val uid: ByteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: run {
            Toast.makeText(this, "Failed to read NFC", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, Arrays.toString(uid) + title, Toast.LENGTH_SHORT).show()

        // 次に表示するActivityへnfc_idと前画面に押されたボタンを送る
        nextIntent.putExtra("nfc_idm", uid)
        nextIntent.putExtra("scan_label", title)
        startActivity(nextIntent)

    }
}
