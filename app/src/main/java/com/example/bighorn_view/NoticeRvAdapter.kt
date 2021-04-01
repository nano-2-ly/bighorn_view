package com.example.bighorn_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoticeRvAdapter(val context: Context, val list:ArrayList<Notice>) : RecyclerView.Adapter<NoticeRvAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeRvAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.device_rv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null


    override fun onBindViewHolder(holder: NoticeRvAdapter.Holder, position: Int) {
        if(itemClick != null){
            holder.itemView?.setOnClickListener{v ->
                itemClick?.onClick(v,position)
            }
        }

        holder.bind(list[position], context)
    }



    inner class Holder(itemView: View):RecyclerView.ViewHolder(itemView){

        val title = itemView?.findViewById<TextView>(R.id.name_area)
        val content = itemView?.findViewById<TextView>(R.id.text_area)

        fun bind(model:Notice, context:Context){
            title.text = model.title
            content.text = model.content
        }
    }


}


