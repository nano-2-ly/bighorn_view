package com.example.bighorn_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_notice_detail.*
import kotlinx.android.synthetic.main.activity_question_detail.*
import org.json.JSONArray
import org.json.JSONObject

class NoticeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

        this.getSupportActionBar()?.hide()


        val question_queue = Volley.newRequestQueue(this)

        Log.e("intent.getStri",intent.getStringExtra("pk"))

        val stringRequest = StringRequest(
            Request.Method.GET, "http://15.164.226.86:8090/api/notice/" + intent.getStringExtra("pk"),
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONObject(response)
                Log.e("jsonObject",jsonObject.toString())
                notice_title.text = jsonObject.getString("TITLE")
                notice_time.text = jsonObject.getString("time")
                notice_content.text = jsonObject.getString("CONTENT")

            },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        question_queue.add(stringRequest)



    }
}