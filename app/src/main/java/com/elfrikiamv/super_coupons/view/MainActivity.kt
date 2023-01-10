package com.elfrikiamv.super_coupons.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.elfrikiamv.super_coupons.R
import com.elfrikiamv.super_coupons.databinding.ActivityMainBinding
import com.elfrikiamv.super_coupons.model.Coupon
import com.elfrikiamv.super_coupons.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //-------------> view
        setupBindings(savedInstanceState)
        //<------------- view

    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        //-------------> view
        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        //<------------- view

        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        activityMainBinding.setModel(couponViewModel)
        setupListUpdate()
    }

    //-------------> callCoupons
    private fun setupListUpdate() {

        couponViewModel?.callCoupons()
        couponViewModel?.getCoupons()?.observe(this, Observer { coupons: List<Coupon> ->
            Log.i("Coupon", coupons.size.toString())
            couponViewModel?.setCouponsInRecyclerAdapter(coupons)
        })
        setupListClick()
    }
    //<------------- callCoupons

    //-------------> onItemClick
    private fun setupListClick() {
        couponViewModel?.getCouponSelected()?.observe(this,
            Observer { coupon: Coupon ->
                Log.i("CLICK", coupon.title)

            })
    }
    //<------------- onItemClick
}