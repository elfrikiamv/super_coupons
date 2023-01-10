package com.elfrikiamv.super_coupons.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl : CouponRepository {

    private var coupons = MutableLiveData<List<Coupon>>()

    //-------------> logic of connecting to the API
    override fun callCouponsAPI() {

        //-------------> controller
        val couponsList: ArrayList<Coupon> = ArrayList<Coupon>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    val jsonObject = jsonElement.asJsonObject
                    val coupon = Coupon(jsonObject)
                    couponsList.add(coupon)
                }
                //-------------> view
                coupons.value = couponsList
                //<------------- view
            }
        })
        //<------------- controller
    }

    override fun getCoupons(): MutableLiveData<List<Coupon>> {
        return coupons
    }
    //<------------- logic of connecting to the API
}