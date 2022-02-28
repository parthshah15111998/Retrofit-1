package com.example.retrofit1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context,val userList:List<MyData.MyDataItem>):RecyclerView.Adapter<MyAdapter.ViewHolder> (){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var userId:TextView
        var title:TextView
        init {
            userId=itemView.findViewById(R.id.userId)
            title=itemView.findViewById(R.id.title)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
         var userid=LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(userid)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
            holder.userId.text=userList[position].userId.toString()
          holder.title.text=userList[position].title
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}