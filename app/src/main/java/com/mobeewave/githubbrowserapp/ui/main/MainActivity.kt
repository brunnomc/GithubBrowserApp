package com.mobeewave.githubbrowserapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.mobeewave.githubbrowserapp.GithubBrowserApplication
import com.mobeewave.githubbrowserapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //Injections
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> { mainViewModelFactory }
    private val repositoriesAdapter = RepositoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as GithubBrowserApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRepositoriesRecycler()
        search()
    }


    private fun setupRepositoriesRecycler() {
        binding.recycler.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.recycler.adapter = repositoriesAdapter
    }

    private fun search(){
        lifecycleScope.launch {
            mainViewModel.searchRepositories("kotlin").collectLatest {
                repositoriesAdapter.submitData(it)
            }
        }
    }
}