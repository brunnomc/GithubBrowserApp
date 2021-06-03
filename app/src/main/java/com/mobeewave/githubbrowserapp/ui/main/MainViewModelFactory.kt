package com.mobeewave.githubbrowserapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobeewave.githubbrowserapp.repository.RepositoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class MainViewModelFactory @Inject constructor(private val repositoryRepository: RepositoryRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(repositoryRepository) as T
        } else {
            throw IllegalArgumentException("MainViewModel Not Found")
        }
    }
}