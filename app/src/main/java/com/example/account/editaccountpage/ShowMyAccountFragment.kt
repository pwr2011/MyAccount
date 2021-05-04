package com.example.account.editaccountpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.account.R
import com.example.account.dataclass.Company
import com.example.account.dataclass.Stock
import com.example.account.mainpage.my_account_array
import kotlinx.android.synthetic.main.fragment_show_my_account.*
import kotlinx.android.synthetic.main.object_table_row_stock.view.*

class Show_account_detail_fragment : Fragment() {

    lateinit var edit_stock_tab: Edit_stock_fragment
    lateinit var company_name: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        company_name = arguments?.getString("company_name").toString()
        var company: Company? = my_account_array.find { it.name == company_name }

        for (i in company!!.own) {
            var row: View =
                LayoutInflater.from(context).inflate(R.layout.object_table_row_stock, null, false)
            row.stock_name.setText(i.name)
            row.stock_count.setText(i.count.toString())
            row.stock_count.setText(i.avg_price.toString())

            display_stock_in_account.addView(row)
        }
        add_stock_button.setOnClickListener {
            println("send!")
            println(company)
            // fragment -> fragment info
            // https://yoon-dailylife.tistory.com/21
            // https://velog.io/@sysout-achieve/Android-Fragment%EA%B0%84-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EC%A0%84%EB%8B%AC-%EB%B0%A9%EB%B2%95%EB%93%A4
            //PassBundleFragment는 본인이 전달하고자 하는 Fragment class
            show_edit_stock_tab(company)
        }
    }

    fun show_edit_stock_tab(company: Company) {
        //https://yuuj.tistory.com/entry/AndroidKotlin-Fragment%EB%A1%9C-%ED%83%AD-%EA%B5%AC%EC%A1%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8-%EA%B5%90%EC%B2%B4
        edit_stock_tab = Edit_stock_fragment()
        replaceFragment(edit_stock_tab, company)
    }

    // send fragment parameter in object
    //https://stackoverflow.com/questions/47593205/how-to-pass-custom-object-via-intent-in-kotlin

    fun replaceFragment(tab: Fragment, company: Company) {
        // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

        val bundle = Bundle()
        bundle.putParcelable("Company", company)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.sub_content, tab.apply {
                arguments = bundle
            })
            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ?.commit()

//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.replace(R.id.sub_content, tab.apply {
//                arguments = Bundle().apply {
//                    putParcelable("Company", company)
//                }
//            })
//            ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            ?.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var result = arguments?.getString("key")
        println("oncreateview!")
        return inflater.inflate(R.layout.fragment_show_my_account, container, false)
    }
}