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
import kotlinx.android.synthetic.main.activity_slide.*
import kotlinx.android.synthetic.main.activity_slide.txts1
import kotlinx.android.synthetic.main.activity_slide.txts2
import kotlinx.android.synthetic.main.activity_tagline.*
import org.json.JSONArray
import org.json.JSONObject

class TaglineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tagline)

        btn_kmbl1.setOnClickListener(){
            val intent= Intent(this,HomeActivty::class.java)
            startActivity(intent)
            finish()
        }

        getdariserver()

        btn_simpantagline.setOnClickListener(){
            val data1:String=edittagline.text.toString()
            postkerserver(data1)

            val intent = Intent(this,TaglineActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.200.24.101/cobarepo-master/tagline_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("kontliResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTittle",jsonObject.optString("isi_tagline"))

                        txtt1.setText(jsonObject.optString("isi_tagline"))
                    }



                }

                override fun onError(anError: ANError?) {
                    Log.i("err",anError.toString())
                }
            })
    }

    fun postkerserver(data1:String)
    {
        AndroidNetworking.post("http://10.200.24.101/cobarepo-master/proses_tagline.php")
            .addBodyParameter("isi_tagline", data1)
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
