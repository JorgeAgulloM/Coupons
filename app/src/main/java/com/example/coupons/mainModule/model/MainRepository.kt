package com.example.coupons.mainModule.model

import com.example.coupons.common.entities.CouponEntity
import com.example.coupons.common.utils.Constants
import com.example.coupons.common.utils.validateTxtCode
import java.lang.Exception

class MainRepository {
    private val roomDataBase = RoomDataBase()

    suspend fun consultCouponByCode(code: String) = roomDataBase.consultCouponByCode(code)

    suspend fun saveCoupon(couponEntity: CouponEntity) {
        if (validateTxtCode(couponEntity.code)) {
            roomDataBase.saveCoupon(couponEntity)
        } else {
            throw Exception(Constants.ERROR_LENGTH)
        }
    }
}