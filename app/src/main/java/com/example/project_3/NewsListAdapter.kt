package com.example.project_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter< NewsViewHolder>(){
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false) //this will basically convert the whole itemnews code which is in xml form to view
       val viewHolder=NewsViewHolder(view)
        view.setOnClickListener{
        listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder // this function handles clicks
    }
    override fun getItemCount(): Int {
        return items.size // this fucntion tells how many items should come in
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title  // this basically binds your data and your item
        holder.author.text=currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageurl).into(holder.image)
        }

    fun UpdateNews(UpdateNews:ArrayList<News>){
        items.clear()
        items.addAll(UpdateNews)

        notifyDataSetChanged()    // due to this the above all three functions will get called again
    }
}
// all the above code is basically adapter starting from  override fun

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView:TextView = itemView.findViewById(R.id.title)
    val image:ImageView = itemView.findViewById(R.id.image)
    val author:TextView = itemView.findViewById(R.id.author)
}
interface NewsItemClicked{
    fun onItemClicked(item:News)
}
