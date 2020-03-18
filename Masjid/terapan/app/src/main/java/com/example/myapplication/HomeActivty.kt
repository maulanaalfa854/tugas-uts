package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_identitas.setOnClickListener(){
            val intent= Intent(this,IdentitasActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_jadwal.setOnClickListener(){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_merquee.setOnClickListener(){
            val intent=Intent(this,marquee::class.java)
            startActivity(intent)
            finish()
        }

        btn_pengumuman.setOnClickListener(){
            val intent= Intent(this,PengumumanActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_slide.setOnClickListener(){
            val intent= Intent(this,SlideActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_tagline.setOnClickListener(){
            val intent= Intent(this,TaglineActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
