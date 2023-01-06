package com.elfrikiamv.super_coupons

import android.os.Bundle

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elfrikiamv.super_coupons.model.ApiAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-------------> view
        val rvCoupons: RecyclerView = findViewById(R.id.rvCoupons)
        rvCoupons.layoutManager = LinearLayoutManager(this)
        val coupons = ArrayList<Coupon>()
        //<------------- view

        //-------------> controller
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
                rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
                //<------------- view
            }
        })
        //<------------- controller
    }
}