package com.shivam.musicwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.shivam.musicwiki.api.ApiClient
import com.shivam.musicwiki.api.ApiInterface
import com.shivam.musicwiki.databinding.ActivityArtistDetailsBinding
import com.shivam.musicwiki.models.artists.Json2KotlinArtistInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_artist_details)
        val artist= intent.getStringExtra("artistName")!!
        loadArtistInfo(artist)





    }


    fun loadArtistInfo(artist:String) {
        val apiInterface: ApiInterface? = ApiClient.apiClient?.create(ApiInterface::class.java)
        val call: Call<Json2KotlinArtistInfo?>? = apiInterface?.getArtistInfo(
            "artist.getinfo",artist,"21efd60691998d773ccc2693a9ddce78", "json"
        )
        call!!.enqueue(object : Callback<Json2KotlinArtistInfo?> {
            override fun onResponse(
                call: Call<Json2KotlinArtistInfo?>,
                response: Response<Json2KotlinArtistInfo?>
            ) {
                if (response.isSuccessful && response.body()?.artistDataClass!= null) {
//                    Log.d("MyTag", "Successful1"+ response.body()?.toString())

                    val coverImage = response.body()!!.artistDataClass!!.image[3].text.toString()
                    Log.d(
                        "MyTag",
                        "Successful1" + response.body()!!.artistDataClass!!.image[3].text.toString()
                    )
                    Glide.with(this@ArtistDetailsActivity)
                        .load(coverImage)
                        .into(binding.img)
                    binding.artist.text = response.body()!!.artistDataClass?.name
                    binding.playCount.text=response.body()!!.artistDataClass?.stats?.playcount+"\nPlaycounts"
                    binding.followers.text=response.body()!!.artistDataClass?.stats?.listeners+"\nFollowers"
                    binding.desc.text = response.body()!!.artistDataClass?.bio?.summary


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

            override fun onFailure(call: Call<Json2KotlinArtistInfo?>, t: Throwable) {
                Log.d("MyTag", t.message.toString() + "error")

            }
        })
    }


}