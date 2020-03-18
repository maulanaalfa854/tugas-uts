package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.update_jadwal.*
import org.json.JSONArray

class updatesholat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updatesholat)

        btn_smpn.setOnClickListener(){
            val data1:String=edt1.text.toString()
            val data2:String=edt2.text.toString()
            val data3:String=edt3.text.toString()
            val data4:String=edt4.text.toString()
            val data5:String=edt5.text.toString()
            postkerserver(data1,data2,data3,data4,data5)

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    fun postkerserver(data1:String,data2:String,data3:String,data4:String,data5:String)
    {
        AndroidNetworking.post("http://10.35.170.251/cobarepo-master/proses_jadwal.php")
            .addBodyParameter("shubuh", data1)
            .addBodyParameter("dhuhur", data2)
            .addBodyParameter("ashar", data3)
            .addBodyParameter("maghrib", data4)
            .addBodyParameter("isha", data5)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                }

                override fun onError(error: ANError?) {

                }
            })
    }

}
