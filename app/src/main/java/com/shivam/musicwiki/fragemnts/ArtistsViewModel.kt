package com.shivam.musicwiki.fragemnts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivam.musicwiki.api.ApiClient
import com.shivam.musicwiki.api.ApiInterface
import com.shivam.musicwiki.models.albums.AlbumDataClass
import com.shivam.musicwiki.models.albums.Json2KotlinAlbums
import com.shivam.musicwiki.models.artists.ArtistsDataClass
import com.shivam.musicwiki.models.artists.Json2KotlinArtists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsViewModel(application: Application) : AndroidViewModel(application) {

    private val liveDataArtistList: MutableLiveData<MutableList<ArtistsDataClass>> = MutableLiveData()

    @JvmName("getGenreList")
    fun getArtists(): MutableLiveData<MutableList<ArtistsDataClass>> {
        return liveDataArtistList
    }

    fun loadArtists(tag:String) {
        val apiInterface: ApiInterface? = ApiClient.apiClient?.create(ApiInterface::class.java)
        val call: Call<Json2KotlinArtists?>? = apiInterface?.getTopArtists(
            "tag.gettopartists", tag, "21efd60691998d773ccc2693a9ddce78", "json"
        )
        call!!.enqueue(object : Callback<Json2KotlinArtists?> {
            override fun onResponse(
                call: Call<Json2KotlinArtists?>,
                response: Response<Json2KotlinArtists?>
            ) {
                if (response.isSuccessful && response.body()?.topartists != null) {

//                    Log.d("MyTag", "Successful1"+ response.body()?.toString())

                    viewModelScope.launch(Dispatchers.IO) {
                        liveDataArtistList.postValue(response.body()!!.topartists?.artist)
//
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

            override fun onFailure(call: Call<Json2KotlinArtists?>, t: Throwable) {
                Log.d("MyTag", t.message.toString() + "error")

            }

        })
    }


}