package com.example.coupons

import android.app.Application
import androidx.room.Room

class CouponApplication: Application() {
    companion object{
        lateinit var dataBase: CouponDataBase
    }

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            this,
            CouponDataBase::class.java,
            "CouponDataBase")
            .build()

    }
}