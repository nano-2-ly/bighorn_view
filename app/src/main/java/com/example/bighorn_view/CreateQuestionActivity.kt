package com.example.bighorn_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_create_question.*
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.activity_question.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class CreateQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_question)

        this.getSupportActionBar()?.hide()


        submit.setOnClickListener{
            loginUser()
        }

    }
    private fun loginUser() {
        val jsonobj = JSONObject()



        val stringRequest: StringRequest = object : StringRequest( Method.POST, "http://15.164.226.86:8090/api/question",
            Response.Listener { response ->
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                try {
                    val jsonObject = JSONObject(response)
                    //Parse your api responce here
                    /*val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)*/
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()

                params["content"] = noteMessage.getText().toString()
                params["email"] = "test@test.test"

                return params


            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}

