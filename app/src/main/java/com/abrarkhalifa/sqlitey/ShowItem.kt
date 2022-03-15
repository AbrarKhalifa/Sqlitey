package com.abrarkhalifa.sqlitey

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_item.*
import kotlinx.android.synthetic.main.dialog_update.*
import kotlinx.android.synthetic.main.items_row.*

class ShowItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_item)


        refreshRecycler()


}

    fun refreshRecycler(){
        var db = DatabaseHandler(this)
        var arr : ArrayList<Student> = db.retriveAll()
        var adapter = Adapter(this,arr)
        rvList.adapter = adapter

    }
    fun delete(position:Int){
        var db = DatabaseHandler(this)
        var arr : ArrayList<Student> = db.retriveAll()
        var std = arr.get(position)
        db.delete(std.id)
        refreshRecycler()
    }
    fun update(position:Int){
        var db = DatabaseHandler(this)
        var arr : ArrayList<Student> = db.retriveAll()
        var std = arr.get(position)
        var dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_update)

        dialog.tvUpdateId.setText(std.id.toString())
        dialog.edtUpdateName.setText(std.item)
        dialog.edtUpdateEmail.setText(std.desc)
        dialog.edtUpdatePrice.setText(std.price)

        dialog.tvUpdateBtn.setOnClickListener {
            var id = dialog.tvUpdateId.text.toString().toInt()
            var item = dialog.edtUpdateName.text.toString()
            var desc = dialog.edtUpdateEmail.text.toString()
            var pric = dialog.edtUpdatePrice.text.toString()
            var person = Student(id,item, desc,pric)
            db.update(person)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.tvCancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


}