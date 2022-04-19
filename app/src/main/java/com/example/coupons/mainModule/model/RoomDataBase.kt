package com.example.coupons.mainModule.model

import android.database.sqlite.SQLiteConstraintException
import android.provider.SyncStateContract
import com.example.coupons.CouponApplication
import com.example.coupons.common.dataAccess.CouponDao
import com.example.coupons.common.entities.CouponEntity
import com.example.coupons.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RoomDataBase {
    private val dao: CouponDao by lazy { CouponApplication.dataBase.couponDao() }

    suspend fun consultCouponByCode(code: String) = dao.consultCouponByCode(code)

    suspend fun saveCoupon(coupondEntity: CouponEntity) = withContext(Dispatchers.IO) {
        try {
            dao.addCoupon(coupondEntity)
        } catch (e: Exception) {
            (e as? SQLiteConstraintException)?.let { throw Exception(Constants.ERROR_EXIST) }
            throw Exception(e.message ?: Constants.ERROR_UNKNOW)
        }
    }
}