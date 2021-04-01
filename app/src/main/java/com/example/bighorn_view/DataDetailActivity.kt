package com.example.bighorn_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_data_detail.*

class DataDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)

        Log.e("hello",intent.getStringExtra("UUID"))
        Log.e("hello",intent.getStringExtra("TIME"))
        Log.e("hello",intent.getStringExtra("MONITOR"))


        uuidText.text = intent.getStringExtra("UUID")
        timeText.text = intent.getStringExtra("TIME")
        dataText.text = intent.getStringExtra("MONITOR")
    }
    override fun onPause() {

        super.onPause()
        overridePendingTransition(0, 0)

    }
}