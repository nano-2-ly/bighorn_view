package com.example.bighorn_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_data_view.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class DataViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_view)



        this.getSupportActionBar()?.hide()

        // ...

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://15.164.226.86:8088/api/devices/${intent.getStringExtra("device")}?len=1&round=1"



        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONObject(response)
                val dataArray = jsonObject.getJSONArray("data")

                val_1_1.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("temperature1").toString()
                val_1_2.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("turbidity1").toString()
                val_1_3.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("waterLevel1").toString()

                val_2_1.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("temperature2").toString()
                val_2_2.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("turbidity2").toString()
                val_2_3.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("waterLevel2").toString()

                val_3_1.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("temperature3").toString()
                val_3_2.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("turbidity3").toString()
                val_3_3.text = dataArray.getJSONObject(0).getJSONObject("MONITOR").getString("waterLevel3").toString()

                var imageUrl = "http://15.164.226.86:8088/api/devices/${intent.getStringExtra("device")}/camera"
                Glide.with(this).load(imageUrl).into(iv_image)

            },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

}