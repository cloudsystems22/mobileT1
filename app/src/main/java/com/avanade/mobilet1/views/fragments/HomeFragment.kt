package com.avanade.mobilet1.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.adapters.CategoriesAdapter
import com.avanade.mobilet1.adapters.Listcategories
import com.avanade.mobilet1.adapters.Movies
import com.avanade.mobilet1.adapters.listCategories
import com.google.gson.Gson


class HomeFragment : Fragment() {

    lateinit var categoriesAdapter:CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesAdapter = CategoriesAdapter()
        val rcCategoriesMovies = view.findViewById<RecyclerView>(R.id.rc_categories_movies)

        rcCategoriesMovies.adapter = categoriesAdapter

        requestMovies()

    }

    private fun requestMovies() {
        Thread {
            Thread.sleep(1000)
            val listcategories = Gson().fromJson(listCategories(), Listcategories::class.java)

            activity?.runOnUiThread{
                categoriesAdapter?.add(listcategories.categories)
            }

            println(listcategories)
        }.start()
    }

}





