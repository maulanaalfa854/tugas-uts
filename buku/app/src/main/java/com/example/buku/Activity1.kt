package com.example.buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity1.*

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity1)

        val context = this

        val intentObject:Intent=intent
        val name=intentObject.getStringExtra("name")
        textview.text = "Hey $name"

        BtnIntentUtama1.setOnClickListener{
            val intent= Intent(context,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
