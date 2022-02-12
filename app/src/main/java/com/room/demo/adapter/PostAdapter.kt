package com.room.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.room.demo.databinding.EachRowBinding
import com.room.demo.model.Post

class PostAdapter(private var listPost:List<Post>) :RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding:EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.tasks.text = listPost[position].body
    }

    override fun getItemCount():Int= listPost.size

    class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {

    }
    fun setData(postList:List<Post>)
    {
        this.listPost = postList
        notifyDataSetChanged()
    }
}