package com.mobeewave.githubbrowserapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mobeewave.githubbrowserapp.GithubBrowserApplication
import com.mobeewave.githubbrowserapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //Injections
    @Inject lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel> { mainViewModelFactory }
    private val repositoriesAdapter = RepositoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as GithubBrowserApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recycler.adapter = repositoriesAdapter
        doSearchRepositories()
    }

    private fun doSearchRepositories(){
        mainViewModel.searchRepositories("language:kotlin", 1).observe(this) {
            if (it != null) {
                if (it.isSuccessful){
                    binding.text.visibility = View.GONE
                    binding.recycler.visibility = View.VISIBLE
                    repositoriesAdapter.submitList(it.body()!!.items)
                } else {

                }
            }
        }
    }
}