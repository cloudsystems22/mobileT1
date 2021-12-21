package com.avanade.mobilet1.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avanade.mobilet1.entities.Categories
import com.avanade.mobilet1.adapters.CategoriesAdapter
import com.avanade.mobilet1.adapters.Listcategories
import com.avanade.mobilet1.adapters.listCategories
import com.avanade.mobilet1.databinding.FragmentSearchBinding
import com.avanade.mobilet1.viewmodels.SearchViewModel
import com.google.gson.Gson

class SearchFragment : Fragment() {

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    private lateinit var listcategories: List<Categories>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider.NewInstanceFactory().create(SearchViewModel::class.java)


        categoriesAdapter = CategoriesAdapter()
        binding.rcSearchedMovies.adapter = categoriesAdapter

        viewModel.getCategories.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updatelist(it)
        })

        //requestMovies()

        binding.searchAction.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.filterList(s.toString())
            }

        })

    }

}