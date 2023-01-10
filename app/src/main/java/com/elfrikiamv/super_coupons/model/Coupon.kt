package com.elfrikiamv.super_coupons.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Coupon(couponJson: JsonObject?) : Serializable {

    private lateinit var id: String
    lateinit var image: String
    lateinit var title: String

    //lateinit var descriptionShort: String
    lateinit var category: String
    lateinit var description: String
    lateinit var offer: String
    lateinit var website: String
    lateinit var endDate: String
    lateinit var url: String

    init {
        try {
            id = couponJson?.get(ID)?.asString ?: "00"
            image = couponJson?.get(IMAGE_URL)?.asString
                ?: "https://dummyimage.com/300x300/c77ec7/ffffff.jpg"
            title = couponJson?.get(TITLE)?.asString ?: "Offer"
            /*descriptionShort =
                chunkWords(couponJson?.get(DESCRIPTION_SHORT)?.asString ?: "The best Offer", ' ', 5)*/
            category = chunkWords(couponJson?.get(CATEGORY)?.asString ?: "All", ',', 1)
            description = couponJson?.get(DESCRIPTION)?.asString ?: "The best Offer"
            offer = couponJson?.get(OFFER)?.asString ?: "It's the only chance"
            website = couponJson?.get(WEBSITE)?.asString ?: "https://www.platzi.com"
            endDate = getFormatDate(
                couponJson?.get(END_DATE)?.asString ?: Calendar.getInstance().time.toString()
            )
            url = couponJson?.get(URL)?.asString ?: "https://www.platzi.com"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val ID = "lmd_id"
        private const val IMAGE_URL = "image_url"
        private const val TITLE = "title"

        //private val DESCRIPTION_SHORT = "offer_text"
        private const val CATEGORY = "categories"
        private const val DESCRIPTION = "description"
        private const val OFFER = "offer"
        private const val WEBSITE = "store"
        private const val END_DATE = "end_date"
        private const val URL = "url"
    }

    private fun getFormatDate(dateCoupon: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        return try {
            val parsedDateFormat = format.parse(dateCoupon)
            val cal = Calendar.getInstance()
            if (parsedDateFormat != null) {
                cal.time = parsedDateFormat
            }
            dateFormat.format(cal.time)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }


    private fun chunkWords(string: String, delimiter: Char, quantity: Int): String {
        val words = string.split(delimiter)
        var newString: String = ""

        if (words.size < quantity) {
            for (i in 0..words.size) {
                newString += words.get(i) + " "
            }
        } else {
            for (i in 0..quantity) {
                newString += words.get(i) + " "
            }
        }
        return newString
    }
}