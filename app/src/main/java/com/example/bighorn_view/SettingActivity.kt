package com.example.bighorn_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_setting.setting_button3
import kotlinx.android.synthetic.main.activity_setting.setting_button4
import kotlinx.android.synthetic.main.activity_setting.setting_button5

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        this.getSupportActionBar()?.hide()

        setting_text.setOnClickListener{
            Toast.makeText(this, "등록된 이용약관이 없습니다.", Toast.LENGTH_LONG).show()
        }




        setting_button1.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }
        setting_button2.setOnClickListener{
            val intent = Intent(this, GraphActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

        setting_button3.setOnClickListener{
            Toast.makeText(this, "연결 방법이 정의되지 않았습니다.", Toast.LENGTH_LONG).show()
        }

        setting_button4.setOnClickListener{
            val intent = Intent(this, QuestionActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent)
        }

//        setting_button5.setOnClickListener{
//            val intent = Intent(this, SettingActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            startActivity(intent)
//
//        }



    }

    override fun onPause() {

        super.onPause()
        overridePendingTransition(0, 0)

    }

}