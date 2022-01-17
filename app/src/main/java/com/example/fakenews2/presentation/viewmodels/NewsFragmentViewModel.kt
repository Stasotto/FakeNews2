package com.example.fakenews2.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.DeleteNewsFromRoomUseCase
import com.example.domain.usecase.GetAllNewsFromNewsApiUseCase
import com.example.domain.usecase.GetAllNewsFromRoomUseCase
import com.example.domain.usecase.SaveNewsInRoomUseCase
import com.example.fakenews2.presentation.models.News
import com.example.fakenews2.toNews
import com.example.fakenews2.toNewsDomain
import kotlinx.coroutines.launch

// многомодульность решает проблему скорости сборки приложения
// true story

class NewsFragmentViewModel(
    private val deleteNewsFromRoomUseCase: DeleteNewsFromRoomUseCase,
    private val getAllNewsFromRoomUseCase: GetAllNewsFromRoomUseCase,
    private val getAllNewsFromNewsApiUseCase: GetAllNewsFromNewsApiUseCase,
    private val saveNewsInRoomUseCase: SaveNewsInRoomUseCase
) : ViewModel() {

    private val _newsForRecyclerView = MutableLiveData<List<News>>()
    val newsForRecyclerView: LiveData<List<News>> get() = _newsForRecyclerView

    private val newsFromRoom = MutableLiveData<List<News>>()
    private val newsFromNewsApi = MutableLiveData<List<News>>()


    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            launch { getNewsFromNewsApi() }.join()
            launch { showSavedNews() }.join()
            mixeNews()
        }
    }

    private suspend fun getNewsFromNewsApi() {
        newsFromNewsApi.value = getAllNewsFromNewsApiUseCase.execute().map { newsDomain ->
            newsDomain.toNews()
        }
    }

    private suspend fun showSavedNews() {
        newsFromRoom.value = getAllNewsFromRoomUseCase.execute().map { newsDomain ->
            newsDomain.toNews()
        }
    }

    fun deleteNewsFromRoom(news: News) {
        viewModelScope.launch {
            deleteNewsFromRoomUseCase.execute(news.toNewsDomain())
        }
    }

    fun saveNewsToRoom(news: News) {
        viewModelScope.launch {
            saveNewsInRoomUseCase.execute(news.toNewsDomain())
        }
    }

    private fun mixeNews() {
        val newsApiList = newsFromNewsApi.value?.toMutableList() ?: return
        val newsRoomList = newsFromRoom.value ?: return

        newsApiList.forEachIndexed { index, newsApi ->
            newsRoomList.forEach { newsRoom ->
                if (newsApi.title == newsRoom.title) {
                    newsApiList[index] = newsRoom
                }
            }
        }
        _newsForRecyclerView.value = newsApiList
    }
}