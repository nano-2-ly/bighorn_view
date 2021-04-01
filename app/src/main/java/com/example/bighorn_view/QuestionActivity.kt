package com.example.bighorn_view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_question.question_button3
import kotlinx.android.synthetic.main.activity_question.question_button4
import kotlinx.android.synthetic.main.activity_question.question_button5
import kotlinx.android.synthetic.main.activity_setting.*
import org.json.JSONArray

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        this.getSupportActionBar()?.hide()

        val location = IntArray(2)
        question_create_btn.getLocationOnScreen(location)

        Log.e("location",location[0].toString())
        Log.e("location",location[1].toString())

// Instantiate the RequestQueue.
        val question_queue = Volley.newRequestQueue(this)




        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, "http://15.164.226.86:8090/api/question",
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONArray(response)
                val noticeArray = jsonObject.getJSONObject(0)


                var questionList = arrayListOf<Notice>()
                for (i in 0 until jsonObject.length()) {
                    var question_title = jsonObject.getJSONObject(i).getString("CONTENT")
                    var question_content = ""
                    if (question_title.length>20){
                        question_title = jsonObject.getJSONObject(i).getString("TITLE").take(20) + "..."
                    }
                    if (question_content.length>30){
                        question_content = jsonObject.getJSONObject(i).getString("CONTENT").take(30) + "..."
                    }
                    questionList.add(Notice(question_title,question_content))
                }

//                    var modelList = arrayListOf<Notice>(
//                        Notice(jsonObject.getJSONObject(0).getString("TITLE").take(20) + "...",jsonObject.getJSONObject(0).getString("CONTENT").take(20) + "..."),
//                        Notice(jsonObject.getJSONObject(1).getString("TITLE").take(20) + "...",jsonObject.getJSONObject(1).getString("CONTENT").take(20) + "...")
//
//
//                    )

                val question_mAdapter = NoticeRvAdapter(this, questionList)
                question_mRecyclerView.adapter = question_mAdapter

                question_mAdapter.itemClick = object : NoticeRvAdapter.ItemClick{
                    override fun onClick(view: View, position: Int) {
                        Log.e("position : ", position.toString())
                        val intent = Intent(this@QuestionActivity,QuestionDetailActivity::class.java)
                        intent.putExtra("UUID", jsonObject.getJSONObject(position).getString("UUID"))
                        startActivity(intent)
                    }
                }

                //layout
                val lm = LinearLayoutManager(this)
                question_mRecyclerView.layoutManager = lm


            },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        question_queue.add(stringRequest)


        question_create_btn.setOnClickListener{
            val intent = Intent(this, CreateQuestionActivity::class.java)
            startActivity(intent)
        }



        question_button1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        question_button2.setOnClickListener{
            val intent = Intent(this, GraphActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }


        question_button3.setOnClickListener{
            Toast.makeText(this, "연결 방법이 정의되지 않았습니다.", Toast.LENGTH_LONG).show()
        }

//        data_button4.setOnClickListener{
//            val intent = Intent(this, QuestionActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent)
//
//        }

        question_button5.setOnClickListener{
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