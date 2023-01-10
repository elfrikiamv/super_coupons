package com.elfrikiamv.super_coupons.model

import androidx.lifecycle.MutableLiveData

interface CouponRepository {

    fun getCoupons(): MutableLiveData<List<Coupon>>
    fun callCouponsAPI()
}