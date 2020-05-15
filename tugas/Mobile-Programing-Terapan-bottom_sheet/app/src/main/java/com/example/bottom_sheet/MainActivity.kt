package com.example.bottom_sheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.menu.ShowableListMenu
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    var mBottomSheetDialog:BottomSheetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ShowBottomDialog(view: View) {
        val bottomSheetLayout = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(bottomSheetLayout)
        mBottomSheetDialog!!.show()

    }
}
