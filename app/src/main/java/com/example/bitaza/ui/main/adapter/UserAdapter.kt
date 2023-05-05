package com.example.bitaza.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bitaza.R
import com.example.bitaza.data.model.User

class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    private var userList : ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item,parent,false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.name.text = currentItem.name
        holder.counter.text = currentItem.deployedImg.toString()
        val pos : Int = position+1
        holder.top.text = "#$pos"
    }

    class UserViewHolder(itemViewHolder: View) : RecyclerView.ViewHolder(itemViewHolder){
        val name : TextView = itemView.findViewById(R.id.txtNameCardView)
        val counter : TextView = itemView.findViewById(R.id.counterTxtCardView)
        val top : TextView = itemView.findViewById(R.id.topCardView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list : ArrayList<User>){
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }
}