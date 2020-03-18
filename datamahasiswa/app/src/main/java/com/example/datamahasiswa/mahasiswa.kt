package com.example.datamahasiswa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_mahasiswa.*
import org.json.JSONArray
import org.json.JSONObject

class mahasiswa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mahasiswa)

        getdariserver()

        btnTambah.setOnClickListener {
            var data_nama: String = editNama.text.toString()
            var data_nim: String = editNIM.text.toString()
            var data_alamat: String = editAlamat.text.toString()

            postkeserver(data_nama,data_nim,data_alamat)
            getdariserver()
        }

        btnLogout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("STATUS", "0")
            editor.apply()

            startActivity(Intent(this@mahasiswa, MainActivity::class.java))
            finish()
        }

    }

    fun getdariserver(){
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<DataMhs>()
//        "https://mhs-data.000webhostapp.com/mahasiswa-json.php"
        AndroidNetworking.get("http://172.20.10.2/mahasiswa-kotlin/mahasiswa-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_mahasiswa"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1 = jsonObject.optString("nama_mahasiswa").toString()
                        var isi2 = jsonObject.optString("nomor_mahasiswa").toString()
                        var isi3 = jsonObject.optString("alamat_mahasiswa").toString()

                        users.add(DataMhs("$isi1", "$isi2", "$isi3"))
                    }

                    val adapter = CustomAdapter(users)
                    recyclerView.adapter = adapter
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postkeserver(data1:String, data2:String, data3:String){
        AndroidNetworking.post("http://172.20.10.2/mahasiswa-kotlin/proses-mahasiswa.php")
            .addBodyParameter("nama_mahasiswa", data1)
            .addBodyParameter("nomor_mahasiswa", data2)
            .addBodyParameter("alamat_mahasiswa", data3)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray?) {
                }

                override fun onError(anError: ANError?) {
                }
            })
    }
}
