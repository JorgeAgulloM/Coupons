package com.example.coupons

import android.app.Application
import androidx.room.Room
import com.example.coupons.common.dataAccess.CouponDatabase

class CouponApplication: Application() {
    companion object{
        lateinit var dataBase: CouponDatabase
    }

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            this,
            CouponDatabase::class.java,
            "CouponDatabase")
            .build()

    }
}