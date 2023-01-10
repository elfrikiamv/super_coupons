package com.elfrikiamv.super_coupons.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elfrikiamv.super_coupons.R
import com.elfrikiamv.super_coupons.model.Coupon
import com.elfrikiamv.super_coupons.model.CouponObservable
import com.elfrikiamv.super_coupons.view.RecyclerCouponsAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CouponViewModel : ViewModel() {

    private var couponObservable: CouponObservable = CouponObservable()
    private var recyclerCouponsAdapter: RecyclerCouponsAdapter? = null
    var selected: MutableLiveData<Coupon> = MutableLiveData<Coupon>()

    fun callCoupons() {
        couponObservable.callCoupons()
    }

    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponObservable.getCoupons()
    }

    fun setCouponsInRecyclerAdapter(coupons: List<Coupon>) {
        recyclerCouponsAdapter?.setCouponsList(coupons)
        recyclerCouponsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerCouponsAdapter(): RecyclerCouponsAdapter? {
        recyclerCouponsAdapter = RecyclerCouponsAdapter(this, R.layout.card_coupon)
        return recyclerCouponsAdapter
    }

    fun getCouponAt(position: Int): Coupon? {
        val coupons: List<Coupon>? = couponObservable.getCoupons().value
        return coupons?.get(position)
    }

    fun getCouponSelected(): MutableLiveData<Coupon> {
        return selected
    }

    fun onItemClick(index: Int) {
        val coupon = getCouponAt(index)
        selected.value = coupon
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: CircleImageView, imageUrl: String?) {
            imageUrl?.let {
                if (it.isNotEmpty())
                    Picasso.get().load(it).into(imageView)
            }
        }
    }
}