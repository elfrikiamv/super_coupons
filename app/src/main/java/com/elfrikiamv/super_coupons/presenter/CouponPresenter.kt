package com.elfrikiamv.super_coupons.presenter

import com.elfrikiamv.super_coupons.model.Coupon

interface CouponPresenter {

    //-----------------> view
    fun showCoupons(coupons: ArrayList<Coupon>?)
    //<---------------- view

    //-----------------> interacts with model
    fun getCoupons()
    //<---------------- interacts with model
}