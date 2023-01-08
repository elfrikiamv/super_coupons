package com.elfrikiamv.super_coupons.view

import com.elfrikiamv.super_coupons.model.Coupon

interface CouponView {
    //-----------------> view
    fun showCoupons(coupons: ArrayList<Coupon>?)
    //<---------------- view

    //-----------------> presenter
    fun getCoupons()
    //<---------------- presenter
}