package com.example.account.editaccountpage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.account.R
import com.example.account.R.layout.object_table_row_account_bottom
import com.example.account.R.layout.object_table_row_account_top
import com.example.account.mainpage.my_account_array
import kotlinx.android.synthetic.main.fragment_add_account.*
import kotlinx.android.synthetic.main.object_table_row_account_bottom.view.*
import kotlinx.android.synthetic.main.object_table_row_account_top.view.*


class Show_all_account_fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =
            LayoutInflater.from(activity).inflate(R.layout.fragment_add_account, container, false)
        return view
    }

    // fragment -> another fragment로 이동
    // my_account_add_account.setOnClickListener{
    //            (activity as MainActivity).replaceFragment()
    //        }
    // fragment -> activity
    // onViewCreated는 onCreateView 이후에 더 띄울 내용을 구성하는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //https://stackoverflow.com/questions/18207470/adding-table-rows-dynamically-in-android
        //https://stackoverflow.com/questions/62986656/adding-rows-to-table-layout-in-kotlin
        //https://stackoverflow.com/questions/28071349/the-specified-child-already-has-a-parent-you-must-call-removeview-on-the-chil
        //(이 방법으로 해결) https://stackoverflow.com/questions/18979187/how-to-get-viewgroup-after-inflate-layout-in-android

        for (i in my_account_array) {
            var row_upper: View = LayoutInflater.from(activity).inflate(object_table_row_account_top, null , false)
            var row_bottom: View = LayoutInflater.from(activity).inflate(object_table_row_account_bottom, null , false)
            row_upper.account_name.setText(i.name)
            row_bottom.account_balance.setText((i.total).toString())

            row_upper.edit_stock_button.setOnClickListener{
                activity?.let {
                    val intent = Intent(it, Edit_account_activity::class.java)
                    intent.putExtra("company_name", row_upper.account_name.text.toString())
                    it.startActivityForResult(intent, 2)
                }
            }
            account_table.addView(row_upper)
            account_table.addView(row_bottom)
        }

        //https://yuuj.tistory.com/11
        my_account_add_account.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Add_account_activity::class.java)
                it.startActivityForResult(intent, 1)
            }
        }
    }
}