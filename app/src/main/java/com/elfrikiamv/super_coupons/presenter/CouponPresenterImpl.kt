package com.elfrikiamv.super_coupons.presenter

import com.elfrikiamv.super_coupons.model.Coupon
import com.elfrikiamv.super_coupons.model.CouponsInteractor
import com.elfrikiamv.super_coupons.model.CouponsInteractorImpl
import com.elfrikiamv.super_coupons.view.CouponView

class CouponPresenterImpl(var couponView: CouponView) : CouponPresenter {

    private var couponInteractor: CouponsInteractor = CouponsInteractorImpl(this)

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        couponView.showCoupons(coupons)
    }

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }
}
