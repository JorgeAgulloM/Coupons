package com.example.coupons.common.utils

import org.junit.Assert.*
import org.junit.Test

class CouponUtilsKtTest{
    @Test
    fun `validate Text Code Success Test`() {
        val code = "WELCOME"
        assertTrue(validateTxtCode(code))
    }

    @Test
    fun `validate Text Code Empty Fail Test`() {
        val code = ""
        assertFalse(validateTxtCode(code))
    }
}