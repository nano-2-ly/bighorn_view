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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notice.*
import org.json.JSONArray

class NoticeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        this.getSupportActionBar()?.hide()

        // ...

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://15.164.226.86:8088/api/devices"


        val stringRequest2 = StringRequest(Request.Method.GET, "http://15.164.226.86:8090/api/notice",
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONArray(response)
                val noticeArray = jsonObject.getJSONObject(0).getString("CONTENT")
                Log.e("deviceArray",noticeArray.toString())
             },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, "http://15.164.226.86:8090/api/notice",
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    val jsonObject = JSONArray(response)
                    val noticeArray = jsonObject.getJSONObject(0)


                    var modelList = arrayListOf<Notice>()
                    for (i in 0 until jsonObject.length()) {
                        var title = jsonObject.getJSONObject(i).getString("TITLE")
                        var content = jsonObject.getJSONObject(i).getString("CONTENT")
                        if (title.length>20){
                            title = jsonObject.getJSONObject(i).getString("TITLE").take(20) + "..."
                        }
                        if (content.length>30){
                            content = jsonObject.getJSONObject(i).getString("CONTENT").take(30) + "..."
                        }
                        modelList.add(Notice(title,content))
                    }

//                    var modelList = arrayListOf<Notice>(
//                        Notice(jsonObject.getJSONObject(0).getString("TITLE").take(20) + "...",jsonObject.getJSONObject(0).getString("CONTENT").take(20) + "..."),
//                        Notice(jsonObject.getJSONObject(1).getString("TITLE").take(20) + "...",jsonObject.getJSONObject(1).getString("CONTENT").take(20) + "...")
//
//
//                    )

                    val mAdapter = NoticeRvAdapter(this, modelList)
                    mRecyclerView.adapter = mAdapter

                    mAdapter.itemClick = object : NoticeRvAdapter.ItemClick{
                        override fun onClick(view: View, position: Int) {
                            Log.e("position : ", position.toString())
                            val intent = Intent(this@NoticeActivity,NoticeDetailActivity::class.java)

                            intent.putExtra("pk", jsonObject.getJSONObject(position).getString("primaryKey"))
                            startActivity(intent)
                        }
                    }

                    //layout
                    val lm = LinearLayoutManager(this)
                    mRecyclerView.layoutManager = lm


                },
                Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        queue.add(stringRequest2)


        back_btn.setOnClickListener{
            finish()
        }
    }
}