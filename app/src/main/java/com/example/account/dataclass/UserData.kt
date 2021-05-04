package com.example.account.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Stock(var name : String, var count : Int, var avg_price : Double):Parcelable

@Parcelize
data class Company (var name: String,
                    var total: Double,
                    var own: ArrayList<Stock> ): Parcelable