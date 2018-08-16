package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*

class IdWaitingActivity : AppCompatActivity() {

    private lateinit var mNfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_wating)

        mNfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this@IdWaitingActivity)
    }

    override fun onResume() {
        super.onResume()

        val intent: Intent = Intent(this@IdWaitingActivity, this@IdWaitingActivity.javaClass)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        mNfcAdapter.enableForegroundDispatch(this@IdWaitingActivity, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()

        mNfcAdapter.disableForegroundDispatch(this@IdWaitingActivity)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uid: ByteArray? = intent?.getByteArrayExtra(NfcAdapter.EXTRA_ID)
        Toast.makeText(this@IdWaitingActivity, Arrays.toString(uid), Toast.LENGTH_SHORT).show()
    }
}
