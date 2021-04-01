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
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_question_detail.*
import org.json.JSONArray
import org.json.JSONObject

class QuestionDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_detail)
        this.getSupportActionBar()?.hide()


        val question_queue = Volley.newRequestQueue(this)

        Log.e("intent.getStri",intent.getStringExtra("UUID"))

        val stringRequest = StringRequest(
            Request.Method.GET, "http://15.164.226.86:8090/api/question/" + intent.getStringExtra("UUID"),
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                val jsonObject = JSONArray(response).getJSONObject(0)
                Log.e("jsonObject",jsonObject.toString())
                question_content.text = jsonObject.getString("CONTENT")

            },
            Response.ErrorListener { Log.e("SERVER CONNECTION : ","That didn't work!") })


        // Add the request to the RequestQueue.
        question_queue.add(stringRequest)




    }


}