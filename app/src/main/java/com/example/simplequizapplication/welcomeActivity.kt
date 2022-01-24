package com.example.simplequizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplequizapplication.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.simplequizapplication.activity_login
import com.example.simplequizapplication.HomeActivity

class welcomeActivity : AppCompatActivity() {
    var admin: Button? = null
    var user: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_welcome)
        admin = findViewById<View>(R.id.btn_admin) as Button
        user = findViewById<View>(R.id.btn_user) as Button
        admin!!.setOnClickListener {
            val intent = Intent(this@welcomeActivity, activity_login::class.java)
            startActivity(intent)
        }
        user!!.setOnClickListener {
            val intent = Intent(this@welcomeActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}