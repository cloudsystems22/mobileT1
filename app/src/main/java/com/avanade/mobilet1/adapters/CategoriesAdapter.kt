package com.avanade.mobilet1.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.entities.Movies
import kotlinx.android.synthetic.main.item_categories_movies.view.*

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    private var catgList = emptyList<Movies>()
    private var moviesList = emptyList<Movies>()

    private var onClickItem: ((Movies) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CategoryHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_categories_movies, parent, false)
    )

    fun updatelist(listCategory: MutableList<Movies>){
        val newList = ArrayList<Movies>()
        val newMovies = ArrayList<Movies>()
        val groupCateg = listCategory.groupBy { it.category }
        groupCateg.forEach {
            newList.add(Movies(category=it.key))
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
        fun bind(movies: Movies, listMoveis:List<Movies>){

            val newMovies = listMoveis.filter { it.category == movies.category }

            with(itemView){
                this.title_movie.text = movies.category

                this.rc_movies.layoutManager = LinearLayoutManager(
                    itemView.context, RecyclerView.HORIZONTAL, false
                )

                rc_movies.adapter = MoviesAdapter(newMovies)
            }

        }
    }

}