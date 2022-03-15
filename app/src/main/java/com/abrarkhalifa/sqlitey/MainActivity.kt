package com.abrarkhalifa.sqlitey

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.enterEmail
import kotlinx.android.synthetic.main.activity_main.enterName
import kotlinx.android.synthetic.main.activity_show_item.*
import kotlinx.android.synthetic.main.dialog_update.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd.setOnClickListener {
            var items = enterName.text.toString().trim()
            var desc = enterEmail.text.toString().trim()
            var prices= enterPrice.text.toString().trim()
            var quetitys = enterQuent.text.toString().trim()
            if (TextUtils.isEmpty(items) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(prices.toString() ) || TextUtils.isEmpty(quetitys.toString())){
                Toast.makeText(this, "fill all the data", Toast.LENGTH_SHORT).show()
            }else{
                var db = DatabaseHandler(this)
                var user = Student(items,desc,prices,quetitys)
                db.insert(user)
                enterName.setText("")
                enterEmail.setText("")
                enterPrice.setText("")
                enterQuent.setText("")

            }
        }

        btnShow.setOnClickListener {
            var intent = Intent(this,ShowItem::class.java)
            startActivity(intent)
        }

    }






}