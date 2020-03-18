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
import kotlinx.android.synthetic.main.activity_marquee.txtm1

import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONArray
import org.json.JSONObject

class PengumumanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        getdariserver()

        btn_kembal.setOnClickListener(){
            val intent= Intent(this,HomeActivty::class.java)
            startActivity(intent)
            finish()
        }

        btn_simpanmuman.setOnClickListener(){
            val data1:String=editmuman.text.toString()
            val data2:String=editmuman1.text.toString()
            postkerserver(data1,data2)

            val intent = Intent(this,PengumumanActivity::class.java)
            startActivity(intent)
            finish()
        }



    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.200.24.101/cobarepo-master/pengumuman_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("kontliResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTittle",jsonObject.optString("judul_pengumuman"))

                        txtp1.setText(jsonObject.optString("judul_pengumuman"))
                        txtp2.setText(jsonObject.optString("isi_pengumuman"))
                    }



                }

                override fun onError(anError: ANError?) {
                    Log.i("err",anError.toString())
                }
            })
    }

    fun postkerserver(data1:String,data2:String)
    {
        AndroidNetworking.post("http://10.200.24.101/cobarepo-master/proses_pengumuman.php")
            .addBodyParameter("judul_pengumuan", data1)
            .addBodyParameter("isi_pengumuman", data2)
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
