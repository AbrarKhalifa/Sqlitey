package com.abrarkhalifa.sqlitey

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.widget.Toast

class DatabaseHandler(var context: Context):SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){
    companion object{
        const val DB_NAME = "studentData"
        const val DB_VERSION = 1
        const val ID = "id"
        const val TB_NAME = "studentdetail"
        const val ITEM_NAME = "item"
        const val DESC = "description"
        const val  PRICE= "price"
        const val QUETITY = "quentity"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        var query = "CREATE TABLE $TB_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT, $ITEM_NAME TEXT,$DESC TEXT,$PRICE INTEGER,$QUETITY INTEGER)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var query = "DROP TABLE IF EXISTS $TB_NAME"
        db?.execSQL(query)
        onCreate(db)
    }

    fun insert(sdf : Student){
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(ITEM_NAME,sdf.item)
        cv.put(DESC,sdf.desc)
        cv.put(PRICE,sdf.price)
        cv.put(QUETITY,sdf.quentity)
        var result = db.insert(TB_NAME,null,cv)
        db.close()
        if (result == (-1).toLong()){
            Toast.makeText(context, "failed to insert", Toast.LENGTH_SHORT).show()
        }else{

            Toast.makeText(context, "User inserted successfully", Toast.LENGTH_SHORT).show()

        }

    }

    fun retriveAll():ArrayList<Student>{
        var db = readableDatabase
        var arr : ArrayList<Student> = ArrayList()
        var cursor = db.query(TB_NAME,null,null,null,null,null,null)
        while (cursor.moveToNext()){
            var id = cursor.getInt(0)
            var iteam = cursor.getString(1)
            var descr = cursor.getString(2)
            var price = cursor.getString(3)
            var quent = cursor.getString(4)
            var data = Student(id,iteam,descr,price,quent)
            arr.add(data)
        }
        return arr

    }

    fun delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_NAME,"$ID=$id",null)
        db.close()
    }
    fun update(p:Student){
        var db = writableDatabase
        var cv = ContentValues()
        cv.put(ITEM_NAME,p.item)
        cv.put(DESC,p.desc)
        cv.put(PRICE,p.price)
        var flag = db.update(TB_NAME,cv,"$ID=${p.id}",null)
        if (flag> 0){
            Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show()
        }
        db.close()

    }

}