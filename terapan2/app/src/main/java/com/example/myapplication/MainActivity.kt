package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.myapplication.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager=LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<User>()


        AndroidNetworking.get("http://10.200.54.89/cobarepo-master/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("shubuh"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("shubuh").toString()
                        var isi2=jsonObject.optString("dhuhur").toString()
                        var isi3=jsonObject.optString("ashar").toString()
                        var isi4=jsonObject.optString("maghrib").toString()
                        var isi5=jsonObject.optString("isha").toString()

                        users.add(User("$isi1", "$isi2","$isi3","$isi4","$isi5"))


                    }

                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter


                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })

    }

}
