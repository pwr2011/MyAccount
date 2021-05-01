package com.example.account.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.account.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        my_account_add_account.setOnClickListener{

        }

    }
}