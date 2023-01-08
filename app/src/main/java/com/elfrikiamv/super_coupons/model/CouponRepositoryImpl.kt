package com.elfrikiamv.super_coupons.model

import android.util.Log
import com.elfrikiamv.super_coupons.presenter.CouponPresenter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {

    //-------------> logic of connecting to the API
    override fun getCouponsAPI() {

        //-------------> controller
        val coupons: ArrayList<Coupon> = ArrayList<Coupon>()
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
                    coupons.add(coupon)
                }
                //-------------> view
                couponPresenter.showCoupons(coupons)
                //<------------- view
            }
        })
        //<------------- controller
    }
    //<------------- logic of connecting to the API
}