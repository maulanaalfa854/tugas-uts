package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.update_jadwal.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getdariserver()

        btn_kembali.setOnClickListener(){
            val intent= Intent(this,HomeActivty::class.java)
            startActivity(intent)
            finish()
        }



        btn_update.setOnClickListener(){
            val intent = Intent(this,updatesholat::class.java)
            startActivity(intent)
            finish()
        }




    }

    fun getdariserver(){

            AndroidNetworking.get("http://10.35.170.251/cobarepo-master/contoh_jadwal_json.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        Log.e("kontliResponse",response.toString())

                        val jsonArray = response!!.getJSONArray("result")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            Log.e("kotlinTittle",jsonObject.optString("shubuh"))

                            txt1.setText(jsonObject.optString("shubuh"))
                            txt2.setText(jsonObject.optString("dhuhur"))
                            txt3.setText(jsonObject.optString("ashar"))
                            txt4.setText(jsonObject.optString("maghrib"))
                            txt5.setText(jsonObject.optString("isha"))
                        }



                    }

                    override fun onError(anError: ANError?) {
                        Log.i("err",anError.toString())
                    }
                })

    }


}
