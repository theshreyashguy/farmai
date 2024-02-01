package com.example.farmai.ui.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmai.ArticleActivity
import com.example.farmai.R

class BlogAdapter1(private val blogItemList: List<BlogItem>, private val context: Context) :
    RecyclerView.Adapter<BlogAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogItem = blogItemList[position]

        // Set the title
        holder.blogTitle.text = blogItem.title
        if(blogItem.img == "a1") {
            holder.blogImage.setBackgroundResource(R.drawable.a1)
        }
        else if(blogItem.img == "a2"){
            holder.blogImage.setBackgroundResource(R.drawable.a2)
        }
        else if(blogItem.img == "a3"){
            holder.blogImage.setBackgroundResource(R.drawable.a3)
        }
        else if(blogItem.img == "a4"){
            holder.blogImage.setBackgroundResource(R.drawable.a4)
        }
        else if(blogItem.img == "a5"){
            holder.blogImage.setBackgroundResource(R.drawable.a5)
        }
        else {
            holder.blogImage.setBackgroundResource(R.drawable.a6)
        }


        // Load the image (you can use a library like Picasso or Glide for better image loading)
//        holder.blogImage.setImageResource(blogItem.imageResource)
//        holder.itemView.setOnClickListener {
//            // When the item is clicked, open the new activity
//            val intent = Intent(context, ArticleActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            intent.putExtra("articleTitle", blogItem.title)
//            intent.putExtra("articleImg", blogItem.img)
//            intent.putExtra("articleContent", blogItem.Content)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return blogItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val blogImage: ImageView = itemView.findViewById(R.id.blogImage)
        val blogTitle: TextView = itemView.findViewById(R.id.blogTitle)
    }
}
