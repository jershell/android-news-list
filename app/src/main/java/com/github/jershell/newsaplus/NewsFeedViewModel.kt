package com.github.jershell.newsaplus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.github.jershell.newsaplus.data.ItemOfNews
import com.github.jershell.newsaplus.data.News
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonParsingException
import kotlinx.serialization.list
import kotlinx.serialization.parse
import java.text.ParseException

const val DATA_URL = "https://drive.google.com/uc?id=1wozWr5swgtdV9PLyo2b09mtjaOD6sS2I"

class NewsFeedViewModel : ViewModel() {
    private val client = HttpClient()
    val isPending = MutableLiveData<Boolean>()
    val news: MutableLiveData<List<ItemOfNews>> = MutableLiveData()

    init {
        Log.i("ViewModel", "NewsFeedViewModel.init")
        isPending.value = false
    }

    suspend fun fetch() {
        Log.i("ViewModel", "NewsFeedViewModel.fetch")
        isPending.value = true
        val data = client.get<String>(DATA_URL)

        val parsedNews = try {
            Json.parse(News.serializer(), data)
        } catch (error: Exception) {
            Log.e("ViewModel", "NewsFeedViewModel.fetch() ${error.message}")
            News(emptyList())
        }

        Log.i("ViewModel", "NewsFeedViewModel.fetch() size ${parsedNews.items.size}")
        news.postValue(parsedNews.items)
        isPending.value = false
    }
}
