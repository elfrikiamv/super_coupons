package com.elfrikiamv.super_coupons.model

import com.elfrikiamv.super_coupons.presenter.CouponPresenter

class CouponsInteractorImpl(couponPresenter: CouponPresenter): CouponsInteractor {

    private var couponRepository: CouponRepository = CouponRepositoryImpl(couponPresenter)
    //------------->
    override fun getCouponsAPI() {

        couponRepository.getCouponsAPI()
    }
    //<-------------
}