package com.avanade.mobilet1.views.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.avanade.mobilet1.R
import com.avanade.mobilet1.adapters.CategoriesAdapter
import com.avanade.mobilet1.adapters.Listcategories
import com.avanade.mobilet1.adapters.listCategories
import com.avanade.mobilet1.databinding.FragmentHomeBinding
import com.avanade.mobilet1.databinding.FragmentSearchBinding
import com.avanade.mobilet1.viewmodels.HomeViewModel
import com.avanade.mobilet1.viewmodels.SearchViewModel
import com.google.gson.Gson


class HomeFragment : Fragment() {

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel:HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.NewInstanceFactory().create(HomeViewModel::class.java)

        categoriesAdapter = CategoriesAdapter()
        binding.rcMyMovies.adapter = categoriesAdapter

        viewModel.getMyMovies.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updatelist(it)
        })

        //requestMovies()

    }

    private fun requestMovies() {
        Thread {
            Thread.sleep(1000)
            val listcategories = Gson().fromJson(listCategories(), Listcategories::class.java)

            activity?.runOnUiThread{
                //categoriesAdapter?.add(listcategories.categories)
            }

            //println(listcategories)
        }.start()
    }

}





