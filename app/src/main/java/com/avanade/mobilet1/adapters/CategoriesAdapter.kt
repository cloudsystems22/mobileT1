package com.avanade.mobilet1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_categories_movies.view.*

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CategoryHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_categories_movies, parent, false)
    )

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 10

    class CategoryHolder(var view:View) : RecyclerView.ViewHolder(view){
        fun bind(){
            with(itemView){
                this.rc_movies.layoutManager = LinearLayoutManager(
                    itemView.context, RecyclerView.HORIZONTAL, false
                )

                rc_movies.adapter = MoviesAdapter()
            }

        }
    }

}