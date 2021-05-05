package com.example.account.editaccountpage

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import com.example.account.R
import com.example.account.dataclass.Company
import com.example.account.dataclass.Stock
import com.example.account.mainpage.my_account_array
import kotlinx.android.synthetic.main.fragment_edit_stock.*

class Edit_stock_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_stock)

        var company_name = intent.getStringExtra("Company")
        var company: Company? = my_account_array.find { it.name == company_name }

        edit_complete_button.setOnClickListener {
            var company_name = input_stock_name.text.toString()
            var stock_count = input_stock_count.text.toString().toInt()
            var avg_price = input_stock_avg_price.text.toString().toDouble()
            company!!.own.add(Stock(company_name, stock_count, avg_price))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}