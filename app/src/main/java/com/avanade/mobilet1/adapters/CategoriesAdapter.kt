package com.avanade.mobilet1.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.entities.Categories
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_categories_movies.view.*

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    private var catgList = emptyList<Categories>()
    private var moviesList = emptyList<Categories>()

    private var onClickItem: ((Categories) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CategoryHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_categories_movies, parent, false)
    )

    fun updatelist(listCategory: MutableList<Categories>){
        val newList = ArrayList<Categories>()
        val newMovies = ArrayList<Categories>()
        val groupCateg = listCategory.groupBy { it.title }
        groupCateg.forEach {
            newList.add(Categories(title=it.key))
        }
        newMovies.addAll(listCategory)

        catgList = newList
        moviesList = newMovies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoryHolder, position: Int) {
        holder.bind(catgList[position], moviesList)
    }

    override fun getItemCount(): Int = catgList.size

    class CategoryHolder(var view:View) : RecyclerView.ViewHolder(view){
        fun bind(categories: Categories, listMoveis:List<Categories>){

            val newMovies = listMoveis.filter { it.title == categories.title }

            with(itemView){
                this.title_movie.text = categories.title

                this.rc_movies.layoutManager = LinearLayoutManager(
                    itemView.context, RecyclerView.HORIZONTAL, false
                )

                rc_movies.adapter = MoviesAdapter(newMovies)
            }

        }
    }

}