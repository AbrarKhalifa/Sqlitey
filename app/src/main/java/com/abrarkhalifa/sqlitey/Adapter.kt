package com.abrarkhalifa.sqlitey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration.get
import android.view.ViewGroup
import androidx.appcompat.view.ActionBarPolicy.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_row.view.*

class Adapter(val context:Context, private val arrayList: ArrayList<Student>) : RecyclerView.Adapter<Adapter.myadapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadapter {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_row,parent,false)
        return myadapter(view)
    }

    override fun onBindViewHolder(holder: myadapter, position: Int) {

       holder.bind(arrayList[position])
        holder.itemView.imgDelete.setOnClickListener{
            if (context is ShowItem){
                context.delete(position)
            }
        }
        holder.itemView.imgUpdate.setOnClickListener{
            if (context is ShowItem){
                context.update(position)
            }
        }
        holder.itemView.imgPlus.setOnClickListener{
            var count:Int = 1
            var qty = holder.itemView.tvQuentity
            qty.text = count.toString()
            count++

        }
        holder.itemView.imgMinus.setOnClickListener{
            var qnt = arrayList.get(position).quentity
            arrayList.get(position).quentity


        }



    }

    override fun getItemCount(): Int {
       return arrayList.size
    }


    class myadapter(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bind(p:Student){
            itemView.tvItem.text= p.item.toString()
            itemView.tvDesc.text= p.desc.toString()
            itemView.tvPrice.text= p.price.toString()
            itemView.tvQuentity.text= p.quentity.toString()
            itemView.tvId.text = p.id.toString()


        }


    }

}
