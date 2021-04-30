package com.example.testappforbestapps.other

import androidx.lifecycle.*
import com.example.testappforbestapps.data.JsonData.Common
import com.example.testappforbestapps.data.db.Entityes.NewsItem
import com.example.testappforbestapps.data.repository.Repository
import kotlinx.coroutines.launch

class AppViewModel (private val repository: Repository) : ViewModel() {

    val allWords: LiveData<List<NewsItem>> = repository.allNews

    var filter: MutableLiveData<String> = MutableLiveData()

    fun filterChange(str: String){
        filter.postValue(str)
    }

    fun insert(newsItem: NewsItem) = viewModelScope.launch {
        repository.insert(newsItem)
    }

    fun loadGsonData(): MutableList<NewsItem>{
        return Common.toDataBase()
    }


}
class AppViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

