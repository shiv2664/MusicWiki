package com.shivam.musicwiki.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.shivam.musicwiki.api.ApiClient
import com.shivam.musicwiki.api.ApiInterface
import com.shivam.musicwiki.models.genres.Json2KotlinTopTags
import com.shivam.musicwiki.models.genre.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val liveDataMovieList: MutableLiveData<MutableList<Tag>> = MutableLiveData()
    private var genreListSmall: MutableList<Tag> = ArrayList<Tag>()
    private var genreListBig: MutableList<Tag> = ArrayList<Tag>()
//    private var genreList: ArrayList<Json2KotlinTopTags> = ArrayList()


    @JvmName("getGenreList")
    fun getGenreList(): MutableLiveData<MutableList<Tag>> {
        return liveDataMovieList
    }

    fun expand(){
        viewModelScope.launch(Dispatchers.IO){
            liveDataMovieList.postValue(genreListBig)
        }
    }

    fun loadJSON1() {
        val apiInterface: ApiInterface? = ApiClient.apiClient?.create(ApiInterface::class.java)
        val call: Call<Json2KotlinTopTags?>? = apiInterface?.getTopGenres(
            "tag.getTopTags",
            "21efd60691998d773ccc2693a9ddce78", "json"
        )
        call!!.enqueue(object : Callback<Json2KotlinTopTags?> {
            override fun onResponse(
                call: Call<Json2KotlinTopTags?>,
                response: Response<Json2KotlinTopTags?>
            ) {
                if (response.isSuccessful && response.body()?.toptags != null) {

//                    Log.d("MyTag", "Successful1"+ response.body()?.toptags!!.tag.toString())
                    genreListBig = response.body()?.toptags!!.tag.toMutableList()
                    genreListSmall=genreListBig.subList(0,9)


                    viewModelScope.launch(Dispatchers.IO) {
                        genreListBig = response.body()?.toptags!!.tag.toMutableList()
                        genreListSmall=genreListBig.subList(0,9)
                        liveDataMovieList.postValue(genreListSmall)
//                        liveDataMovieList.postValue(response.body()!!.toptags?.tag!!.toMutableList())
                    }
                } else {
                    val errorCode: String = when (response.code()) {
                        2 -> "Invalid service - This service does not exist"
                        3 -> "Invalid Method - No method with that name in this package"
                        4 -> "Authentication Failed - You do not have permissions to access the service"
                        5 -> "Invalid format - This service doesn't exist in that format"
                        6 -> "Invalid parameters - Your request is missing a required parameter"
                        7 -> "Invalid resource specified"
                        8 -> "Operation failed - Something else went wrong"
                        9 -> "Invalid session key - Please re-authenticate"
                        10 -> "Invalid API key - You must be granted a valid key by last.fm"
                        11 -> "Service Offline - This service is temporarily offline. Try again later."
                        13 -> "Invalid method signature supplied"
                        16 -> "There was a temporary error processing your request. Please try again"
                        26 -> "Suspended API key - Access for your account has been suspended, please contact Last.fm"
                        29 -> "Rate limit exceeded - Your IP has made too many requests in a short period"
                        else -> "unknown error"
                    }
                    Log.d("MyTag", response.code().toString() + "error")
                }
            }

            override fun onFailure(call: Call<Json2KotlinTopTags?>, t: Throwable) {
                Log.d("MyTag", t.message.toString() + "error")

            }
        })
    }

}