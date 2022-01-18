package com.example.fakenews2.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.DeleteNewsFromDatabaseUseCase
import com.example.domain.usecase.GetAllNewsFromInternetUseCase
import com.example.domain.usecase.GetAllNewsFromDatabaseUseCase
import com.example.domain.usecase.SaveNewsToDatabaseUseCase
import com.example.fakenews2.presentation.models.News
import com.example.fakenews2.toNews
import com.example.fakenews2.toNewsDomain
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// многомодульность решает проблему скорости сборки приложения
// true story

class NewsFragmentViewModel(
    private val deleteNewsFromDatabaseUseCase: DeleteNewsFromDatabaseUseCase,
    private val getAllNewsFromDatabaseUseCase: GetAllNewsFromDatabaseUseCase,
    private val getAllNewsFromInternetUseCase: GetAllNewsFromInternetUseCase,
    private val saveNewsToDatabaseUseCase: SaveNewsToDatabaseUseCase
) : ViewModel() {

    private val _newsForRecyclerView = MutableLiveData<List<News>>()
    val newsForRecyclerView: LiveData<List<News>> get() = _newsForRecyclerView

    init {
        loadNews()
    }

    fun deleteNewsFromDatabase(news: News) {
        viewModelScope.launch {
            deleteNewsFromDatabaseUseCase.execute(news.toNewsDomain())
        }
    }

    fun saveNewsToDatabase(news: News) {
        viewModelScope.launch {
            saveNewsToDatabaseUseCase.execute(news.toNewsDomain())
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            val resultServer = async { getNewsFromInternet() }
            val  resultDatabase = async { showSavedNews() }

            val res = resultServer.await()
            val res2 = resultDatabase.await()

            mixeNews(res,res2)
        }
    }

    private suspend fun getNewsFromInternet(): List<News> {
        return getAllNewsFromInternetUseCase.execute().map { newsDomain ->
            newsDomain.toNews()
        }
    }

    private suspend fun showSavedNews(): List<News> {
        return getAllNewsFromDatabaseUseCase.execute().map { newsDomain ->
            newsDomain.toNews()
        }
    }

    private fun mixeNews(internetNews: List<News>?, databaseNews: List<News>?) {
        val newsApiList = internetNews?.toMutableList() ?: return
        newsApiList.forEachIndexed { index, newsApi ->
            databaseNews?.forEach { newsRoom ->
                if (newsApi.title == newsRoom.title) {
                    newsApiList[index] = newsRoom
                }
            }
        }
        _newsForRecyclerView.value = newsApiList
    }
}