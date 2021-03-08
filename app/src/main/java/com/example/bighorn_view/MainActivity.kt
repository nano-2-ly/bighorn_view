package com.example.bighorn_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import kotlin.math.log

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.getSupportActionBar()?.hide()

        // ...

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://15.164.226.86:8088/api/devices"



        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    val jsonObject = JSONObject(response)
                    val deviceArray = jsonObject.getJSONArray("deviceList")



                    var modelList = arrayListOf<Device>(
                        Device(deviceArray[0].toString(),"b")

                    )

                    val mAdapter = DeviceRvAdapter(this, modelList)
                    mRecyclerView.adapter = mAdapter

                    mAdapter.itemClick = object : DeviceRvAdapter.ItemClick{
                        override fun onClick(view: View, position: Int) {
                            Log.e("position : ", position.toString())
                            val intent = Intent(this@MainActivity,DataViewActivity::class.java)
                            intent.putExtra("device", deviceArray[position].toString())
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





    }
}