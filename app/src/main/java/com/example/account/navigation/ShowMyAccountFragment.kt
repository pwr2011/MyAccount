package com.example.account.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.account.R
import com.example.account.my_account_array
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_show_my_account.*
import kotlinx.android.synthetic.main.show_stock_info.*
import kotlinx.android.synthetic.main.show_stock_info.view.*

class ShowMyAccountFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var company_name = arguments?.getString("company_name")
        var company = my_account_array.find { it.name == company_name }
        for(i in company!!.own){
            var row: View =
                LayoutInflater.from(context).inflate(R.layout.show_stock_info, null, false)
            row.stock_name.setText(i.name)
            row.stock_count.setText(i.count.toString())
            row.stock_count.setText(i.avg_price.toString())
            my_account_display.addView(row)
            //클릭시 보이기
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_my_account, container, false)
    }
}