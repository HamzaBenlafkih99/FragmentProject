package com.example.fragmentrecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var listData: List<DataModel>, val clickListener: Clicklistener):RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         var img: ImageView
         var name: TextView
         var description: TextView

         init {
             img = view.findViewById(R.id.img1)
             name = view.findViewById(R.id.t1)
             description = view.findViewById(R.id.t2)
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = listData.get(position).itemName
        holder.description.text = listData.get(position).itemDescription
        holder.img.setImageResource(listData.get(position).image)

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(listData.get(position))
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface Clicklistener{
        fun onItemClick(dataModel: DataModel)
    }

}