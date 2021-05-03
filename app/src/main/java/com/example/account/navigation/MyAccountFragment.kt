package com.example.account.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.account.*
import kotlinx.android.synthetic.main.account_table_bottom.*
import kotlinx.android.synthetic.main.account_table_bottom.view.*
import kotlinx.android.synthetic.main.account_table_top.*
import kotlinx.android.synthetic.main.account_table_top.view.*
import kotlinx.android.synthetic.main.fragment_my_account.*
import kotlinx.android.synthetic.main.fragment_my_account.view.*
import org.w3c.dom.Text


class MyAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =
            LayoutInflater.from(activity).inflate(R.layout.fragment_my_account, container, false)
        return view
    }

    // fragment -> another fragment로 이동
    // my_account_add_account.setOnClickListener{
    //            (activity as MainActivity).replaceFragment()
    //        }
    // fragment -> activity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //https://stackoverflow.com/questions/62986656/adding-rows-to-table-layout-in-kotlin
        for (i in my_account_array) {
            var account_table : TableLayout = this.requireActivity().account_table
            var row_upper: TableRow = TableRow(this.requireActivity())
            var row_bottom: TableRow = TableRow(this.requireActivity())
            //var row = TableRow(context)



            val account_name : TextView = TextView(requireActivity())
            account_name.setText(i.name)
            val total : TextView = TextView(requireActivity())
            total.setText((i.total).toString())
            row_upper.addView(account_name)
            row_bottom.addView(total)
            //여기까지는 ㄱㅊ
            account_table.addView(row_upper)
            println("add view")
            account_table.addView(row_bottom)
        }
//
//        //https://yuuj.tistory.com/11
//        my_account_add_account.setOnClickListener {
//            activity?.let {
//                val intent = Intent(it, AddCountActivity::class.java)
//                it.startActivityForResult(intent, 1)
//            }
//        }
    }
}