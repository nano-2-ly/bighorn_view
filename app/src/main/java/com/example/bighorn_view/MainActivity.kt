package com.example.bighorn_view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notice.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var mBtn : Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getSupportActionBar()?.hide()

        val queue = Volley.newRequestQueue(this)


        val stringRequest = StringRequest(
            Request.Method.GET, "http://15.164.226.86:8088/api/devices/267d5914-6934-11eb-1111-001a7dda7113",
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONObject(response).getJSONArray("data").getJSONObject(0).getJSONObject("MONITOR")

                Log.e("asd",jsonObject.getDouble("temperature1").toString())
                textview1.text = jsonObject.getDouble("temperature1").toString()
                textview2.text = jsonObject.getDouble("waterLevel1").toString()
                textview3.text = jsonObject.getDouble("turbidity1").toString()



            },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        queue.add(stringRequest)





        notice_layout.setOnClickListener{
            val intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }

        FAQ_layout.setOnClickListener{
            Toast.makeText(this, "등록된 FAQ가 없습니다.", Toast.LENGTH_LONG).show()
        }
        question_layout.setOnClickListener{
            val intent = Intent(this, CreateQuestionActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0,0)
        }



        button2.setOnClickListener{
            val intent = Intent(this, GraphActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        button3.setOnClickListener{
            Toast.makeText(this, "연결 방법이 정의되지 않았습니다.", Toast.LENGTH_LONG).show()
        }

        button4.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)

        }

        button5.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)

        }





    }
    override fun onPause() {

        super.onPause()
        overridePendingTransition(0, 0)

    }




}