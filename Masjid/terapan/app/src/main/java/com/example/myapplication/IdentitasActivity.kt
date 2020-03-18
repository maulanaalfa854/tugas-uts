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
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class IdentitasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)

        getdariserver()

        btn_kembli.setOnClickListener(){
            val intent= Intent(this,IdentitasActivity::class.java)
            startActivity(intent)
            finish()
        }



        btn_simpan.setOnClickListener(){
            val data1:String=edit1.text.toString()
            val data2:String=edit2.text.toString()
            postkerserver(data1,data2)

            val intent = Intent(this,IdentitasActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun getdariserver(){

        AndroidNetworking.get("http://10.200.40.175/cobarepo-master/identitasmasjid_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("kontliResponse",response.toString())

                    val jsonArray = response!!.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTittle",jsonObject.optString("nama_masjid"))

                        txti2.setText(jsonObject.optString("nama_masjid"))
                        txti3.setText(jsonObject.optString("alamat_masjid"))
                    }



                }

                override fun onError(anError: ANError?) {
                    Log.i("err",anError.toString())
                }
            })
    }

    fun postkerserver(data1:String,data2:String)
    {
        AndroidNetworking.post("http://10.200.40.175/cobarepo-master/proses_identitas.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray?) {
                }

                override fun onError(error: ANError?) {

                }
            })
    }
}
