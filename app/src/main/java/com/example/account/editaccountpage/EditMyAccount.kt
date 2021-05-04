package com.example.account.editaccountpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.account.R

class EditMyAccount : AppCompatActivity() {

    val manager = supportFragmentManager
    lateinit var show_my_account_tab: ShowMyAccountFragment
    lateinit var edit_my_account_tab: EditMyAccountFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_account)
        var company_name = intent?.getStringExtra("company_name").toString()

        show_account_tab(company_name)
    }

    fun show_account_tab(company_name: String) {
        //https://yuuj.tistory.com/entry/AndroidKotlin-Fragment%EB%A1%9C-%ED%83%AD-%EA%B5%AC%EC%A1%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0-%ED%94%84%EB%9E%98%EA%B7%B8%EB%A8%BC%ED%8A%B8-%EA%B5%90%EC%B2%B4
        show_my_account_tab =
            ShowMyAccountFragment()
        replaceFragment(show_my_account_tab,company_name)
    }

    fun show_edit_account_tab() {
        edit_my_account_tab =
            EditMyAccountFragment()
        replaceFragment(edit_my_account_tab)
    }

    //https://medium.com/hongbeomi-dev/fragment-%EC%9E%98-%EC%8D%A8%EB%B3%B4%EA%B8%B0-bundle-c2fd8fe96967
    //출처: https://mc10sw.tistory.com/16 [Make it possible]
    fun replaceFragment(tab: Fragment, company_name: String) {
        // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
        supportFragmentManager.beginTransaction()
            .replace(R.id.sub_content, tab.apply {
                arguments = Bundle().apply {
                    putString("company_name", company_name)
                }
            })
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun replaceFragment(tab: Fragment) {
        // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
        supportFragmentManager.beginTransaction()
            .replace(R.id.sub_content, tab)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}