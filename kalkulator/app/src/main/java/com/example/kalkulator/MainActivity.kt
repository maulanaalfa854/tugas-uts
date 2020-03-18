package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jumlahkan.setOnClickListener {

            tambah()

        }

        pengurangan.setOnClickListener{

            kurang()
        }

        perkalian.setOnClickListener{

            kali()
        }

        pembagian.setOnClickListener{

            bagi()
        }


    }
    fun tambah(){
        var bilangan1:String=bil1.text.toString()
        var bilangan2:String=bil2.text.toString()

        var a:Int=bilangan1.toInt()
        var b:Int=bilangan2.toInt()

        var c:Int=a+b

        hasil.setText(c.toString())
    }

    fun kurang(){
        var bilangan1:String=bil1.text.toString()
        var bilangan2:String=bil2.text.toString()

        var a:Int=bilangan1.toInt()
        var b:Int=bilangan2.toInt()

        var c:Int=a-b

        hasil.setText(c.toString())
    }
    fun kali(){
        var bilangan1:String=bil1.text.toString()
        var bilangan2:String=bil2.text.toString()

        var a:Int=bilangan1.toInt()
        var b:Int=bilangan2.toInt()

        var c:Int=a*b

        hasil.setText(c.toString())
    }
    fun bagi(){

        var bilangan1:String=bil1.text.toString()
        var bilangan2:String=bil2.text.toString()

        var a:Int=bilangan1.toInt()
        var b:Int=bilangan2.toInt()

        var c:Int=a/b

        hasil.setText(c.toString())
    }
}
