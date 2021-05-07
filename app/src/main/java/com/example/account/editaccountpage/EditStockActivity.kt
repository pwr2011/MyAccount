package com.example.account.editaccountpage

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.account.R
import com.example.account.dataclass.Company
import com.example.account.dataclass.Stock
import com.example.account.mainpage.my_account_array
import kotlinx.android.synthetic.main.fragment_edit_stock.*
import java.io.FileWriter
import java.io.IOException

class Edit_stock_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_stock)

        var company_name = intent.getStringExtra("Company")
        var company: Company? = my_account_array.find { it.name == company_name }

        edit_complete_button.setOnClickListener {
            var stock_name = input_stock_name.text.toString()
            var stock_count = input_stock_count.text.toString().toInt()
            var avg_price = input_stock_avg_price.text.toString().toDouble()
            var new_stock = Stock(stock_name,stock_count,avg_price)
            company!!.own.add(new_stock)

            data_refresh(new_stock, company_name!!)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    fun data_refresh(new_stock : Stock, company_name : String){
        var text = company_name+" "+new_stock.name+" "+new_stock.count.toString()+" "+new_stock.avg_price.toString()+"\n"

        try {
            val fw = FileWriter(filesDir.toString()+"/UserStockData.txt", true)
            fw.write(text)
            fw.close()

        } catch (e: IOException) {
            println("IO error happen")
            Toast.makeText(this@Edit_stock_activity, "파일 쓰기 오류!", Toast.LENGTH_SHORT).show()
        }
        var company: Company? = my_account_array.find { it.name == company_name }
        company!!.total += (new_stock.count*new_stock.avg_price).toDouble()
    }

}