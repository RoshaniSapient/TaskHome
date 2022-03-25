package com.example.hometask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hometask.R
import com.example.hometask.databinding.ActivityMainBinding
import com.example.hometask.helper.Status
import com.example.hometask.helper.stockAdaptor
import com.example.hometask.model.stockList
import com.example.hometask.viewModel.stockListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class stockListClass : AppCompatActivity() {
    private lateinit var viewModel: stockListViewModel
    private lateinit var binding: ActivityMainBinding
    private var adapter: stockAdaptor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[stockListViewModel::class.java]
        lifecycleScope.launch{

           viewModel.commentState.collect {
               when(it.status)
               {
                   Status.LOADING -> {
                       binding.progressBar.isVisible = true
                   }

                   Status.SUCCESS -> {
                       binding.progressBar.isVisible = false

                       it.data?.let { comment ->
                           adapter = stockAdaptor(comment)
                           val manager = LinearLayoutManager(this)
                           binding.recyclerView.setHasFixedSize(true)
                           binding.recyclerView.setLayoutManager(manager)
                           binding.recyclerView.setAdapter(adapter)
                       }
                   }
                   else -> {
                       binding.progressBar.isVisible = false

                   }

               }
           }
           }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.search_item,menu)
        var searchItem=menu?.findItem(R.id.action_search)
        var searchView=searchItem?.actionView as SearchView;
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return false
            }
        })
        return true
    }
}