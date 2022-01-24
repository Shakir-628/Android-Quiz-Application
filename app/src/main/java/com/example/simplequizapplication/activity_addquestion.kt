package com.example.simplequizapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.simplequizapplication.R
import com.example.simplequizapplication.DbHelper
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.simplequizapplication.welcomeActivity
import android.widget.Toast

class activity_addquestion : AppCompatActivity() {
    var back: Button? = null
    var add: Button? = null
    var q: EditText? = null
    var opt1: EditText? = null
    var opt2: EditText? = null
    var opt3: EditText? = null
    var opt4: EditText? = null
    var ans: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addquestion)
        back = findViewById<View>(R.id.btn_back) as Button
        add = findViewById<View>(R.id.btn_add) as Button
        q = findViewById<View>(R.id.etQuesion) as EditText
        opt1 = findViewById<View>(R.id.txtOption1) as EditText
        opt2 = findViewById<View>(R.id.txtOption2) as EditText
        opt3 = findViewById<View>(R.id.txtOption3) as EditText
        opt4 = findViewById<View>(R.id.txtOption4) as EditText
        ans = findViewById<View>(R.id.txt_ans) as EditText
        val db = DbHelper(this)
        back!!.setOnClickListener {
            val intent = Intent(this@activity_addquestion, welcomeActivity::class.java)
            startActivity(intent)
        }
        add!!.setOnClickListener {
            val ques = q!!.text.toString()
            val option1 = opt1!!.text.toString()
            val option2 = opt2!!.text.toString()
            val option3 = opt3!!.text.toString()
            val option4 = opt4!!.text.toString()
            val answer = ans!!.text.toString()
            if (ques.isEmpty() || option1.isEmpty() || option2.isEmpty() ||
                option3.isEmpty() || option4.isEmpty() || answer.isEmpty()) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Please Fill missing Field",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                db.addQuestions(ques, option1, option2, option3, option4, answer)
                val toast = Toast.makeText(
                    applicationContext,
                    "Question is saved Successfully",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                emptyfield()
            }
        }
    }
    fun emptyfield()
    {
        q!!.text=null
        opt1!!.text=null
        opt2!!.text=null
        opt3!!.text=null
        opt4!!.text=null
        ans!!.text=null
    }
}