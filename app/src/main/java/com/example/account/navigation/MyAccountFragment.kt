package com.example.account.navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.account.*
import kotlinx.android.synthetic.main.fragment_my_account.*


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

        //https://yuuj.tistory.com/11
        my_account_add_account.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AddCountActivity::class.java)
                it.startActivityForResult(intent, 1)
            }
        }
    }
}