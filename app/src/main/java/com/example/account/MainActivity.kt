package com.example.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import com.example.account.navigation.SummaryFragment
import com.example.account.navigation.MyAccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        println("Switch entered!")
        when (item.itemId) {
            R.id.action_my_account -> {
                var my_account_fragment = MyAccountFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, my_account_fragment).commit()
                //Toast.makeText(this@MainActivity, "1st page!", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_summary -> {
                var summary_fragment = SummaryFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, summary_fragment).commit()
                return true
            }
//            R.id.action_extra->{
//                var my_account_fragment = MyAccountFragment()
//                supportFragmentManager.beginTransaction().replace(R.id.main_content,my_account_fragment).commit()
//                return true
//            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener(this)
        println("onCreate entered!")
    }
}