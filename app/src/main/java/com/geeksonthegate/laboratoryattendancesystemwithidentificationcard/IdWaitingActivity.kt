package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*

class IdWaitingActivity : AppCompatActivity() {

    /**
     * NFCアダプタのインスタンスを格納するプロパティ
     */
    private lateinit var mNfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_wating)

        // NFCアダプタのインスタンスを生成
        mNfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this@IdWaitingActivity)
    }

    override fun onResume() {
        super.onResume()

        // NFCがかざされたとき、このActivityに読み込まれるようにする
        val intent: Intent = Intent(this@IdWaitingActivity, this@IdWaitingActivity.javaClass)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        mNfcAdapter.enableForegroundDispatch(this@IdWaitingActivity, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()

        // このアプリが前面にない時はNFCがかざされても反応しないようにする
        mNfcAdapter.disableForegroundDispatch(this@IdWaitingActivity)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // NFCのEXTRA_IDを読み込み表示する
        val uid: ByteArray = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID) ?: run {
            Toast.makeText(this@IdWaitingActivity, "Failed to read NFC", Toast.LENGTH_SHORT)
            return
        }
        Toast.makeText(this@IdWaitingActivity, Arrays.toString(uid), Toast.LENGTH_SHORT).show()
    }
}
