package com.example.account.editaccountpage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.account.R
import com.example.account.dataclass.Company
import com.example.account.dataclass.Stock
import com.example.account.mainpage.my_account_array
import kotlinx.android.synthetic.main.activity_edit_my_account.*
import kotlinx.android.synthetic.main.object_table_row_stock.view.*

class Edit_account_activity : AppCompatActivity() {

    lateinit var company_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_account)

        company_name = intent?.getStringExtra("company_name").toString()
        var company: Company? = my_account_array.find { it.name == company_name }

        for (i in company!!.own) {
            var row: View =
                LayoutInflater.from(this).inflate(R.layout.object_table_row_stock, null, false)
            row.stock_name.setText(i.name)
            row.stock_count.setText(i.count.toString())
            row.stock_avg_price.setText(i.avg_price.toString())

            display_stock_in_account.addView(row)
        }
        add_stock_button.setOnClickListener {
            var intent = Intent(this, Edit_stock_activity:: class.java)
            intent.putExtra("Company",company_name)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                1->{
                    println("갱신된 company result")
                    my_account_array.forEach{i->println(i)}
                    refresh_activity()
                }
            }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
        finish()
    }

    fun refresh_activity() {
        recreate()
    }
}