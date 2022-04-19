package com.example.coupons.mainModule.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coupons.R
import com.example.coupons.common.entities.CouponEntity
import com.example.coupons.common.utils.getMsgErrorByCode
import com.example.coupons.mainModule.model.MainRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    private val result = MutableLiveData<CouponEntity>()
    fun getresult() = result

    private val snackBarMsg = MutableLiveData<Int>()
    fun getSnackBarMsg() = snackBarMsg

    fun consultCouponByCode(code: String) {
        viewModelScope.launch {
            result.value = repository.consultCouponByCode(code)
        }
    }

    fun saveCoupon(couponEntity: CouponEntity){
        viewModelScope.launch {
            try {
                repository.saveCoupon(couponEntity)
                consultCouponByCode(couponEntity.code)
                snackBarMsg.value = R.string.main_save_success
            } catch (e: Exception) {
                snackBarMsg.value = getMsgErrorByCode(e.message)
            }
        }
    }
}