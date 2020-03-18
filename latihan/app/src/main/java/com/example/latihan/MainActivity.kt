package com.example.latihan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log
import kotlin.math.log10

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        var ip = 80
//        if (ip >=80 && ip < 100)
//        {
//            Log.i("nilai anda","A")
//            tampil.text="nilai anda A"
//        }
//        else if (ip >= 80 && ip < 90)
//        {
//            Log.i("hasil anda","AB")
//            tampil.text="nilai anda AB"
//        }
//        else if (ip >= 70 && ip < 80)
//        {
//            Log.i("nilai anda","B")
//            tampil.text="nilai anda B"
//        }
//        else if (ip >= 60 && ip < 70)
//        {
//            Log.i("nilai anda","BC")
//            tampil.text="nilai anda BC"
//        }
//        else if (ip < 60 )
//        {
//            Log.i("nilai anda","C")
//            tampil.text="nilai anda C"
//        }

//        for (x in 0..10)
//            Log.i("Hasil","$x")
//
//        var  i = 1
//        while (i <= 5) {
//            Log.i("Line", "$i")
//            ++i
//        }
//
//        var num = 2
//        var i = 1
//
//    do {
//    Log.i("hasil","2 * $i =" + num * i )
//        ++i
//    }while (i < 11)


        var a=5
        var b=6
        var c=10
        penjumlahan(a,b,c)
    }

    fun penjumlahan(a : Int, b : Int, c : Int){

    var d : Int
    d = (a*b-10)/c
    Log.i("hasil","$d")
    }
}
