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
import kotlinx.android.synthetic.main.activity_pengumuman.*
import kotlinx.android.synthetic.main.activity_pengumuman.txtp1
import kotlinx.android.synthetic.main.activity_pengumuman.txtp2
import kotlinx.android.synthetic.main.activity_slide.*
import org.json.JSONArray
import org.json.JSONObject

class SlideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        getdariserver()

        btn_kmbl.setOnClickListener(){
            val intent= Intent(this,SlideActivity::class.java)
            startActivity(intent)
            finish()
        }



        btn_simpanslide.setOnClickListener(){
            val data1:String=editslide.text.toString()
            val data2:String=editslide1.text.toString()
            postkerserver(data1,data2)

            val intent = Intent(this,HomeActivty::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.200.24.101/cobarepo-master/slideshow_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("kontliResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTittle",jsonObject.optString("judul_slideshow"))

                        txts1.setText(jsonObject.optString("judul_slideshow"))
                        txts2.setText(jsonObject.optString("isi_slideshow"))

                    }



                }

                override fun onError(anError: ANError?) {
                    Log.i("err",anError.toString())
                }
            })
    }

    fun postkerserver(data1:String,data2:String)
    {
        AndroidNetworking.post("http://10.200.24.101/cobarepo-master/proses_slideshow.php")
            .addBodyParameter("judul_slideshow", data1)
            .addBodyParameter("isi_slideshow", data2)
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
