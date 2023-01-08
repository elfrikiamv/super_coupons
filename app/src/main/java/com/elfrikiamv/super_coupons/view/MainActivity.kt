package com.elfrikiamv.super_coupons.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elfrikiamv.super_coupons.model.Coupon
import com.elfrikiamv.super_coupons.R
import com.elfrikiamv.super_coupons.presenter.CouponPresenter
import com.elfrikiamv.super_coupons.presenter.CouponPresenterImpl

class MainActivity : AppCompatActivity(), CouponView {

    private var couponPresenter: CouponPresenter? = null
    private var rvCoupons: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        couponPresenter = CouponPresenterImpl(this)

        //-------------> view
        rvCoupons = findViewById(R.id.rvCoupons)
        rvCoupons?.layoutManager = LinearLayoutManager(this)
        //<------------- view

        getCoupons()
    }

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        try {
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getCoupons() {

        couponPresenter?.getCoupons()
    }
}