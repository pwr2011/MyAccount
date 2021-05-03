package com.example.account

data class Stock(var name : String, var count : Int, var avg_price : Double)

data class Company(
    var name: String,
    var total: Double,
    var own: ArrayList<Stock> )