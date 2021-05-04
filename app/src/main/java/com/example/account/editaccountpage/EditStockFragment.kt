package com.example.account.editaccountpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.account.R
import com.example.account.dataclass.Company
import com.example.account.dataclass.Stock
import kotlinx.android.synthetic.main.fragment_edit_stock.*

class Edit_stock_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_stock, container, false)
    }

    //이 함수는 장기적으로
    // argument에 따라 edit or add로 분기해야 함.
    // 함수 재사용
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getparcelable
        // (해결) https://stackoverflow.com/questions/38629145/bundle-returning-null-when-called-in-fragment
        //https://stackoverflow.com/questions/47593205/how-to-pass-custom-object-via-intent-in-kotlin
        val company = arguments?.getParcelable<Company>("Company")
        edit_complete_button.setOnClickListener {
            var company_name = input_stock_name.text.toString()
            var stock_count = input_stock_count.text.toString().toInt()
            var avg_price = input_stock_avg_price.text.toString().toDouble()
            company!!.own.add(Stock(company_name, stock_count, avg_price))

            activity?.supportFragmentManager?.popBackStack()
        }

    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//
//        val bundle = Bundle()
//        bundle.putString("key", "value")
//
//        Edit_stock_fragment().arguments = bundle
//        activity?.supportFragmentManager
//            ?.beginTransaction()
//            ?.replace(R.id.sub_content, Edit_stock_fragment())
//            ?.commit()
//
//
//        val result = "result"
//        setFragmentResult("requestKey", bundleOf("bundleKey" to result))
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container_bundle, PassBundleFragment())
//            .commit()
//    }


}