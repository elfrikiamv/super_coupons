package com.elfrikiamv.super_coupons.model

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

//-------------> model
interface ApiService {
    @GET("getOffers/")
    fun getCoupons(): Call<JsonObject>
}
//<------------- model