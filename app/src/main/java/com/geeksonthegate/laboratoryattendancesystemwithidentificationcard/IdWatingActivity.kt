package com.geeksonthegate.laboratoryattendancesystemwithidentificationcard

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.*

class IdWatingActivity : AppCompatActivity() {

    private lateinit var mNfcAdapter: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_wating)

        mNfcAdapter = android.nfc.NfcAdapter.getDefaultAdapter(this@IdWatingActivity)
    }

    override fun onResume() {
        super.onResume()

        val intent: Intent = Intent(this@IdWatingActivity, this@IdWatingActivity.javaClass)
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        mNfcAdapter.enableForegroundDispatch(this@IdWatingActivity, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()

        mNfcAdapter.disableForegroundDispatch(this@IdWatingActivity)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uid: ByteArray? = intent?.getByteArrayExtra(NfcAdapter.EXTRA_ID)
        Toast.makeText(this@IdWatingActivity, Arrays.toString(uid), Toast.LENGTH_SHORT).show()
    }
}
