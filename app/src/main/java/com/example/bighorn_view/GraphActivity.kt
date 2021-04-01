package com.example.bighorn_view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_setting.*
import org.json.JSONArray
import org.json.JSONObject

class GraphActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        this.getSupportActionBar()?.hide()

        val queue = Volley.newRequestQueue(this)

        var data_mAdapter = NoticeRvAdapter(this, arrayListOf<Notice>(Notice("wait","")))
        data_mRecyclerView.adapter = data_mAdapter

        var lm = LinearLayoutManager(this)
        data_mRecyclerView.layoutManager = lm

        val stringRequest = StringRequest(
            Request.Method.GET, "http://15.164.226.86:8088/api/devices/267d5914-6934-11eb-1111-001a7dda7113",
            Response.Listener<String> { response ->


                val jsonObject = JSONObject(response).getJSONArray("data") //.getJSONObject(0).getJSONObject("MONITOR")

                var dataList = arrayListOf<Notice>()
                for (i in 0 until jsonObject.length()) {
                    var question_title = jsonObject.getJSONObject(i).getString("UUID")
                    var question_content = jsonObject.getJSONObject(i).getString("TIME")
                    if (question_title.length>20){
                        question_title = jsonObject.getJSONObject(i).getString("UUID").take(20) + "..."
                    }
                    if (question_content.length>30){
                        question_content = jsonObject.getJSONObject(i).getString("TIME").take(30) + "..."
                    }
                    dataList.add(Notice(question_title,question_content))
                }

//                    var modelList = arrayListOf<Notice>(
//                        Notice(jsonObject.getJSONObject(0).getString("TITLE").take(20) + "...",jsonObject.getJSONObject(0).getString("CONTENT").take(20) + "..."),
//                        Notice(jsonObject.getJSONObject(1).getString("TITLE").take(20) + "...",jsonObject.getJSONObject(1).getString("CONTENT").take(20) + "...")
//
//
//                    )

                data_mAdapter = NoticeRvAdapter(this, dataList)
                data_mRecyclerView.adapter = data_mAdapter

                data_mAdapter.itemClick = object : NoticeRvAdapter.ItemClick{
                    override fun onClick(view: View, position: Int) {
                        Log.e("position : ", position.toString())
                        val intent = Intent(this@GraphActivity,DataDetailActivity::class.java)
                        intent.putExtra("UUID", jsonObject.getJSONObject(position).getString("UUID"))
                        intent.putExtra("TIME", jsonObject.getJSONObject(position).getString("TIME"))
                        intent.putExtra("MONITOR", jsonObject.getJSONObject(position).getString("MONITOR"))
                        startActivity(intent)
                    }
                }

                //layout
                lm = LinearLayoutManager(this)
                data_mRecyclerView.layoutManager = lm


            },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        data_button1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }
//        button2.setOnClickListener{
//            val intent = Intent(this, GraphActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent)
//        }

        data_button3.setOnClickListener{
            Toast.makeText(this, "연결 방법이 정의되지 않았습니다.", Toast.LENGTH_LONG).show()
        }

        data_button4.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        data_button5.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)

        }

        webgraph_btn.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://15.164.226.86:8088/device/267d5914-6934-11eb-1111-001a7dda7113"))
            startActivity(i)


        }



    }
    override fun onPause() {

        super.onPause()
        overridePendingTransition(0, 0)

    }
}