package com.example.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import com.example.account.navigation.SummaryFragment
import com.example.account.navigation.MyAccountFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    var stock_list = ArrayList<Stock>()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_my_account -> {
                var my_account_fragment = MyAccountFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, my_account_fragment).commit()
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
        Load_file()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener(this)
    }

    fun Load_file(){
        try {
            val file = File(filesDir, "UserData.txt")
            if (file.exists()) {
                file.bufferedReader().useLines {
                    lines->lines.forEach { stock_list.add(it.) }
                }
            }
            else{
                file.createNewFile()
            }
        }
        catch(e:IOException){
            println("IO error happen")
            Toast.makeText(this@MainActivity, "유저 정보를 읽을 수 없습니다!", Toast.LENGTH_SHORT).show()
        }
    }
}