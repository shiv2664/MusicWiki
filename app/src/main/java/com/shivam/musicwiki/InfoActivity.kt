package com.shivam.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shivam.musicwiki.api.ApiClient
import com.shivam.musicwiki.api.ApiInterface
import com.shivam.musicwiki.databinding.ActivityInfoBinding
import com.shivam.musicwiki.databinding.ActivityMainBinding
import com.shivam.musicwiki.models.albums.Json2KotlinInfoAlbum
import com.shivam.musicwiki.models.genre.Tag
import com.shivam.musicwiki.viewmodels.InfoActivityViewModel
import com.shivam.musicwiki.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding
    private lateinit var mViewModel: InfoActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_info)
        val album= intent.getStringExtra("albumName")!!
        val artist= intent.getStringExtra("artistName")!!
        mViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
        getInstance(this.application))[InfoActivityViewModel::class.java]
        binding.mViewModel=mViewModel
//        mViewModel.loadAlbumInfo(artist,album)

        loadAlbumInfo(artist,album)


    }

    fun loadAlbumInfo(artist:String,album:String) {
        val apiInterface: ApiInterface? = ApiClient.apiClient?.create(ApiInterface::class.java)
        val call: Call<Json2KotlinInfoAlbum?>? = apiInterface?.getAlbumInfo(
            "album.getinfo","21efd60691998d773ccc2693a9ddce78",artist,album, "json"
        )
        call!!.enqueue(object : Callback<Json2KotlinInfoAlbum?> {
            override fun onResponse(
                call: Call<Json2KotlinInfoAlbum?>,
                response: Response<Json2KotlinInfoAlbum?>
            ) {
                if (response.isSuccessful && response.body()?.albumInfo!= null) {
//                    Log.d("MyTag", "Successful1"+ response.body()?.toString())

                    val coverImage= response.body()!!.albumInfo!!.image[3].text.toString()
                    Log.d("MyTag", "Successful1"+response.body()!!.albumInfo!!.image[3].text.toString())
                    var thumbnailImage: String? = null
                    CoroutineScope(Dispatchers.IO).launch {

                        thumbnailImage = mViewModel.coverImage.toString()
                        Log.d("MyTag",thumbnailImage+"image")
                        withContext(Dispatchers.Main) {
                            if (thumbnailImage != null) {
                                Glide.with(this@InfoActivity)
                                    .load(coverImage)
                                    .into(binding.img)
                            }

                            binding.title.text= response.body()!!.albumInfo?.name
                            binding.artistInfo.text=response.body()!!.albumInfo?.artist
                            binding.desc.text=response.body()!!.albumInfo?.wiki?.summary
                        }
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

            override fun onFailure(call: Call<Json2KotlinInfoAlbum?>, t: Throwable) {
                Log.d("MyTag", t.message.toString() + "error")

            }
        })
    }

}