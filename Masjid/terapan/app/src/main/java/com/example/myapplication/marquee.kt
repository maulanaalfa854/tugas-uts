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
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_marquee.*
import org.json.JSONArray
import org.json.JSONObject

class marquee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)

        getdariserver()

        btn_kmbali.setOnClickListener(){
            val intent= Intent(this,HomeActivty::class.java)
            startActivity(intent)
            finish()
        }

        btn_simpanmar.setOnClickListener(){
            val data1:String=editmar.text.toString()
            postkerserver(data1)

            val intent = Intent(this,marquee::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.35.170.251/cobarepo-master/merque_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("kontliResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTittle",jsonObject.optString("isi_marquee"))

                        txtm1.setText(jsonObject.optString("isi_marquee"))
                    }



                }

                override fun onError(anError: ANError?) {
                    Log.i("err",anError.toString())
                }
            })
    }

    fun postkerserver(data1:String)
    {
        AndroidNetworking.post("http://10.35.170.251/cobarepo-master/proses_marquee.php")
            .addBodyParameter("isi_marquee", data1)
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
