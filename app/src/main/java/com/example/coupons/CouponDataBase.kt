package com.example.coupons

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CouponEntity::class], version = 1)
abstract class CouponDataBase: RoomDatabase() {
    abstract fun couponDao(): CouponDao
}