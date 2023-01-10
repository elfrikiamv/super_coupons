package com.elfrikiamv.super_coupons.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class CouponObservable : BaseObservable() {

    private var couponRepository: CouponRepository = CouponRepositoryImpl()

    //-------------> repository
    fun callCoupons() {
        couponRepository.callCouponsAPI()
    }
    //<------------- repository

    //-------------> viewmodel
    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponRepository.getCoupons()
    }
    //<------------- viewmodel
}