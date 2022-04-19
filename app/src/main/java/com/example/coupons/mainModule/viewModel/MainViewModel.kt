package com.example.coupons.mainModule.viewModel

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

    val coupon = MutableLiveData(CouponEntity())

    private val hideKeyBoard = MutableLiveData<Boolean>()
    fun isHideKeyBoard() = hideKeyBoard

    private val snackBarMsg = MutableLiveData<Int>()
    fun getSnackBarMsg() = snackBarMsg

    fun consultCouponByCode() {
        coupon.value?.code?.let { code ->
            viewModelScope.launch {
                hideKeyBoard.value = true
                coupon.value = repository.consultCouponByCode(code)
                    ?: CouponEntity(
                        code = code,
                        isActive = false
                    )
            }
        }

    }

    fun saveCoupon(){
        viewModelScope.launch {
            coupon.value?.let { couponEntity ->
                hideKeyBoard.value = true
                try {
                    couponEntity.isActive = true
                    repository.saveCoupon(couponEntity)
                    consultCouponByCode()
                    snackBarMsg.value = R.string.main_save_success
                } catch (e: Exception) {
                    snackBarMsg.value = getMsgErrorByCode(e.message)
                }
            }

        }
    }
}